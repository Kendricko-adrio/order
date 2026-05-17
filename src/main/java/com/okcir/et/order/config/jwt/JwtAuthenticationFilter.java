package com.okcir.et.order.config.jwt;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;
  private final JwtProperties jwtProperties;
  private final AntPathMatcher pathMatcher = new AntPathMatcher();

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String requestPath = request.getRequestURI();
    log.debug("[JWT-FILTER] Incoming request: method={}, path={}", request.getMethod(), requestPath);
    log.debug("[JWT-FILTER] Configured authRequiredPaths: {}", jwtProperties.getAuthRequiredPaths());

    boolean pathRequiresAuth = jwtProperties.getAuthRequiredPaths().stream()
        .peek(pattern -> log.trace("[JWT-FILTER] Checking pattern '{}' against path '{}'", pattern, requestPath))
        .anyMatch(pattern -> {
          boolean match;
          if (pattern.contains("*") || pattern.contains("?")) {
            match = pathMatcher.match(pattern, requestPath);
            log.trace("[JWT-FILTER] Ant pattern '{}' match '{}': {}", pattern, requestPath, match);
          } else {
            boolean startsWith = requestPath.startsWith(pattern);
            boolean boundaryOk = requestPath.length() == pattern.length()
                || pattern.endsWith("/")
                || requestPath.charAt(pattern.length()) == '/';
            match = startsWith && boundaryOk;
            log.trace("[JWT-FILTER] Prefix pattern '{}' -> startsWith={}, boundaryOk={}, result={}",
                pattern, startsWith, boundaryOk, match);
          }
          return match;
        });

    log.debug("[JWT-FILTER] pathRequiresAuth for '{}': {}", requestPath, pathRequiresAuth);

    if (!pathRequiresAuth) {
      log.debug("[JWT-FILTER] Path '{}' does NOT require auth. Proceeding.", requestPath);
      filterChain.doFilter(request, response);
      return;
    }

    log.debug("[JWT-FILTER] Path '{}' REQUIRES auth. Checking Authorization header...", requestPath);
    String authHeader = request.getHeader("Authorization");
    log.debug("[JWT-FILTER] Authorization header value: {}", authHeader);

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      log.warn("[JWT-FILTER] Missing or invalid Authorization header for path: {}", requestPath);
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      log.debug("[JWT-FILTER] Returning 401 UNAUTHORIZED");
      return;
    }

    String token = authHeader.substring(7);
    log.debug("[JWT-FILTER] Extracted token (first 20 chars): {}...", token.substring(0, Math.min(token.length(), 20)));

    try {
      UserJwt userJwt = jwtTokenProvider.parseToken(token);
      log.debug("[JWT-FILTER] Token parsed successfully. Subject: {}", userJwt.getUsername());

      UsernamePasswordAuthenticationToken authentication =
          new UsernamePasswordAuthenticationToken(userJwt, null, Collections.emptyList());
      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      log.debug("[JWT-FILTER] Authentication stored in SecurityContext. Proceeding with filter chain.");
      filterChain.doFilter(request, response);
    } catch (Exception e) {
      log.warn("[JWT-FILTER] JWT validation failed for path {}: {}", requestPath, e.getMessage());
      SecurityContextHolder.clearContext();
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      log.debug("[JWT-FILTER] Returning 401 UNAUTHORIZED after validation failure");
    }
  }
}

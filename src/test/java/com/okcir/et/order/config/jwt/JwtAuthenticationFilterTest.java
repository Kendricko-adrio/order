package com.okcir.et.order.config.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

public class JwtAuthenticationFilterTest {

  private JwtProperties jwtProperties;
  private JwtTokenProvider jwtTokenProvider;
  private JwtAuthenticationFilter filter;

  @BeforeEach
  void setUp() {
    jwtProperties = new JwtProperties();
    jwtProperties.setSecret("YourBase64EncodedSecretKeyThatIsAtLeast256BitsLong!!");
    jwtProperties.setIssuer("auth.yourdomain.com");
    jwtProperties.setAudience("api.yourdomain.com");
    jwtProperties.setAuthRequiredPaths(List.of("/api/"));

    jwtTokenProvider = new JwtTokenProvider(jwtProperties);
    filter = new JwtAuthenticationFilter(jwtTokenProvider, jwtProperties);
  }

  @Test
  void requestWithoutToken_shouldReturn401() throws ServletException, IOException {
    MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/rfq");
    MockHttpServletResponse response = new MockHttpServletResponse();
    FilterChain chain = mock(FilterChain.class);

    filter.doFilterInternal(request, response, chain);

    assertEquals(401, response.getStatus());
    verify(chain, never()).doFilter(any(), any());
  }

  @Test
  void requestWithInvalidToken_shouldReturn401() throws ServletException, IOException {
    MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/rfq");
    request.addHeader("Authorization", "Bearer invalidtoken");
    MockHttpServletResponse response = new MockHttpServletResponse();
    FilterChain chain = mock(FilterChain.class);

    filter.doFilterInternal(request, response, chain);

    assertEquals(401, response.getStatus());
    verify(chain, never()).doFilter(any(), any());
  }

  @Test
  void requestWithValidToken_shouldProceed() throws ServletException, IOException {
    String token = generateValidToken();

    MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/rfq");
    request.addHeader("Authorization", "Bearer " + token);
    MockHttpServletResponse response = new MockHttpServletResponse();
    FilterChain chain = mock(FilterChain.class);

    filter.doFilterInternal(request, response, chain);

    assertEquals(200, response.getStatus());
    verify(chain).doFilter(request, response);
    assertTrue(JwtContextUtil.getCurrentUser().isPresent());
  }

  @Test
  void publicPathWithoutToken_shouldProceed() throws ServletException, IOException {
    MockHttpServletRequest request = new MockHttpServletRequest("GET", "/health");
    MockHttpServletResponse response = new MockHttpServletResponse();
    FilterChain chain = mock(FilterChain.class);

    filter.doFilterInternal(request, response, chain);

    assertEquals(200, response.getStatus());
    verify(chain).doFilter(request, response);
  }

  private String generateValidToken() {
    Date now = new Date();
    Date expiration = new Date(now.getTime() + 3600 * 1000);

    return Jwts.builder()
        .issuer(jwtProperties.getIssuer())
        .subject("testuser")
        .audience().add(jwtProperties.getAudience()).and()
        .claim("groups", List.of("USER"))
        .claim("access_rights", List.of("READ"))
        .id(java.util.UUID.randomUUID().toString())
        .issuedAt(now)
        .expiration(expiration)
        .signWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8)))
        .compact();
  }
}

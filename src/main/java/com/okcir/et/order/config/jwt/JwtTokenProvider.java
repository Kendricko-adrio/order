package com.okcir.et.order.config.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  private final JwtProperties jwtProperties;

  public UserJwt parseToken(String token) {
    try {
      SecretKey key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));

      Claims claims = Jwts.parser()
          .verifyWith(key)
          .requireIssuer(jwtProperties.getIssuer())
          .requireAudience(jwtProperties.getAudience())
          .build()
          .parseSignedClaims(token)
          .getPayload();

      UserJwt userJwt = new UserJwt();
      userJwt.setUsername(claims.getSubject());
      userJwt.setGroups(getListClaim(claims, "groups"));
      userJwt.setAccessRights(getListClaim(claims, "access_rights"));
      userJwt.setId(claims.getId());
      userJwt.setIssuedAt(claims.getIssuedAt());
      userJwt.setExpiration(claims.getExpiration());

      return userJwt;
    } catch (ExpiredJwtException e) {
      log.warn("JWT token is expired: {}", e.getMessage());
      throw e;
    } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
      log.warn("Invalid JWT token: {}", e.getMessage());
      throw e;
    }
  }

  @SuppressWarnings("unchecked")
  private List<String> getListClaim(Claims claims, String key) {
    Object value = claims.get(key);
    if (value instanceof List<?>) {
      return (List<String>) value;
    }
    return null;
  }
}

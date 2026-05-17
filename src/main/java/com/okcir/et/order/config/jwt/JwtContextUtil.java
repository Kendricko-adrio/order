package com.okcir.et.order.config.jwt;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class JwtContextUtil {

  private JwtContextUtil() {
  }

  public static Optional<UserJwt> getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.getPrincipal() instanceof UserJwt) {
      return Optional.of((UserJwt) authentication.getPrincipal());
    }
    return Optional.empty();
  }
}

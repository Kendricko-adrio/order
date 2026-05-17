package com.okcir.et.order.config.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
  private String secret;
  private String issuer;
  private String audience;
  private List<String> authRequiredPaths = new ArrayList<>();
}

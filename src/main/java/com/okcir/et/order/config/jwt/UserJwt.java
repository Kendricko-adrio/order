package com.okcir.et.order.config.jwt;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class UserJwt {
  private String username;
  private List<String> groups;
  private List<String> accessRights;
  private String id;
  private Date issuedAt;
  private Date expiration;
}

package com.okcir.et.order.order.redis;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "order")
public class OrderRedis {

  @Id
  private UUID id;
  private Long dealerId;
  private Long clientId;
  private BigDecimal spotPrice;
  private BigDecimal clientPrice;
  private BigDecimal fwdPoints;
  private BigDecimal allInrate;
  private BigDecimal amount1;
  private String ccyPair;
}

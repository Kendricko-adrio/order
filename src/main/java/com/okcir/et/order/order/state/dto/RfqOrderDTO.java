package com.okcir.et.order.order.state.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RfqOrderDTO {

  private Long dealerId;
  private Long clientId;
  private BigDecimal spotPrice;
  private BigDecimal clientPrice;
  private BigDecimal fwdPoints;
  private BigDecimal allInrate;
  private BigDecimal amount1;
  private String ccyPair;
}

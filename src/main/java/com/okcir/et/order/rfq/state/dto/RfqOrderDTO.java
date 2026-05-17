package com.okcir.et.order.rfq.state.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RfqOrderDTO {

  private BigDecimal spotPrice;
  private BigDecimal clientPrice;
  private BigDecimal fwdPoints;
  private BigDecimal allInrate;
  private BigDecimal amount1;
  private String ccyPair;
  private Long accountId;
  private String sourceAccount;
  private String destinationAccount;
  private CustomWindow customWindow;
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CustomWindow {
    private String identityTypeLabel;
    private String identityNumber;
    private String dealPurposeId;
    private String documentCodeId;
    private String documentDescription;
    private String npwp;
  }
}

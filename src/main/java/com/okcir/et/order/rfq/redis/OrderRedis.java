package com.okcir.et.order.rfq.redis;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "rfq:order")
public class OrderRedis {

  @Id
  private UUID id;

  private String orderStatus;
  private String identityTypeLabel;
  private String identityNumber;
  private BigDecimal amount1;
  private BigDecimal amount2;
  private BigDecimal fxRate;
  private String transactionType;
  private String dealPurposeId;
  private String customerName;
  private Long branchCode;
  private String currencyPair;
  private String documentCodeId;
  private String documentDescription;
  private String npwp;
  private String counterpartyShortName;
  private String typology;
  private String userShortName;
  private String tradeTime;
  private String tradeDate;
  private String maturityDate;
  private String disclaimer;
  private String specialTransaction;
  private String documentFlag;
  private String sourceAccount;
  private String destinationAccount;
  private String cin;
  private String settlementOption;
  private BigDecimal equivalentMarginAmountIdr;
  private BigDecimal equivalentMarginCurrency;
  private String customerPic;
  private String mrrType;
  private String riskCurrency;
  private BigDecimal riskAmount;
  private String eventReason;
  private String fixingParent;
  private String dealStatus;
  private BigDecimal refSpot;
  private BigDecimal netSpot;
  private BigDecimal forward;
  private BigDecimal clientSwapNearLeg;
  private BigDecimal internalSwapNearLeg;
  private BigDecimal clientSwapFarLeg;
  private BigDecimal internalSwapFarLeg;
  private String captureDate;
  private BigDecimal amount1Leg2;
  private BigDecimal amount2Leg2;
  private String transactionCode;
  private String clientId;
  private String traderId;
  private String comments;
  private Integer capturedAmount;
  private String valueDate;
  private String settlementType;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @TimeToLive
  private Long ttlInSeconds;
}

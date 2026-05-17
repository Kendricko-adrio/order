package com.okcir.et.order.rfq.jpa.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "deals_rfq_events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealsRfqEvent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "order_id")
  private UUID orderId;

  @Column(name = "order_status")
  private String orderStatus;

  @Column(name = "identity_type_label")
  private String identityTypeLabel;

  @Column(name = "identity_number")
  private String identityNumber;

  @Column(name = "amount_1", precision = 18, scale = 8)
  private BigDecimal amount1;

  @Column(name = "amount_2", precision = 18, scale = 8)
  private BigDecimal amount2;

  @Column(name = "fx_rate", precision = 18, scale = 8)
  private BigDecimal fxRate;

  @Column(name = "transaction_type")
  private String transactionType;

  @Column(name = "deal_purpose_id")
  private String dealPurposeId;

  @Column(name = "customer_name")
  private String customerName;

  @Column(name = "branch_code")
  private Long branchCode;

  @Column(name = "currency_pair")
  private String currencyPair;

  @Column(name = "document_code_id")
  private String documentCodeId;

  @Column(name = "document_description")
  private String documentDescription;

  @Column(name = "npwp")
  private String npwp;

  @Column(name = "counterparty_short_name")
  private String counterpartyShortName;

  @Column(name = "typology")
  private String typology;

  @Column(name = "user_short_name")
  private String userShortName;

  @Column(name = "trade_time")
  private String tradeTime;

  @Column(name = "trade_date")
  private String tradeDate;

  @Column(name = "maturity_date")
  private String maturityDate;

  @Column(name = "disclaimer")
  private String disclaimer;

  @Column(name = "special_transaction")
  private String specialTransaction;

  @Column(name = "document_flag")
  private String documentFlag;

  @Column(name = "source_account")
  private String sourceAccount;

  @Column(name = "destination_account")
  private String destinationAccount;

  @Column(name = "cin")
  private String cin;

  @Column(name = "settlement_option")
  private String settlementOption;

  @Column(name = "equivalent_margin_amount_idr", precision = 18, scale = 8)
  private BigDecimal equivalentMarginAmountIdr;

  @Column(name = "equivalent_margin_currency", precision = 18, scale = 8)
  private BigDecimal equivalentMarginCurrency;

  @Column(name = "customer_pic")
  private String customerPic;

  @Column(name = "mrr_type")
  private String mrrType;

  @Column(name = "risk_currency")
  private String riskCurrency;

  @Column(name = "risk_amount", precision = 18, scale = 8)
  private BigDecimal riskAmount;

  @Column(name = "event_reason")
  private String eventReason;

  @Column(name = "fixing_parent")
  private String fixingParent;

  @Column(name = "deal_status")
  private String dealStatus;

  @Column(name = "ref_spot", precision = 18, scale = 8)
  private BigDecimal refSpot;

  @Column(name = "net_spot", precision = 18, scale = 8)
  private BigDecimal netSpot;

  @Column(name = "forward", precision = 18, scale = 8)
  private BigDecimal forward;

  @Column(name = "client_swap_near_leg", precision = 18, scale = 8)
  private BigDecimal clientSwapNearLeg;

  @Column(name = "internal_swap_near_leg", precision = 18, scale = 8)
  private BigDecimal internalSwapNearLeg;

  @Column(name = "client_swap_far_leg", precision = 18, scale = 8)
  private BigDecimal clientSwapFarLeg;

  @Column(name = "internal_swap_far_leg", precision = 18, scale = 8)
  private BigDecimal internalSwapFarLeg;

  @Column(name = "capture_date")
  private String captureDate;

  @Column(name = "amount_1_leg_2", precision = 18, scale = 8)
  private BigDecimal amount1Leg2;

  @Column(name = "amount_2_leg_2", precision = 18, scale = 8)
  private BigDecimal amount2Leg2;

  @Column(name = "transaction_code")
  private String transactionCode;

  @Column(name = "client_id")
  private String clientId;

  @Column(name = "trader_id")
  private String traderId;

  @Column(name = "comments")
  private String comments;

  @Column(name = "captured_amount")
  private Integer capturedAmount;

  @Column(name = "value_date")
  private String valueDate;

  @Column(name = "settlement_type")
  private String settlementType;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;
}

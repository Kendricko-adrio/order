package com.okcir.et.order.rfq.state.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RfqStatus {

  CREATED("RFQ has been created and is awaiting validation"),

  CLAIMED("RFQ has been claimed by a dealer"),
  RATE_SUBMIT("RFQ has been submitted"),

  ACCEPTED("RFQ has been accepted"),
  REJECTED("RFQ has been rejected");

  private final String description;
}
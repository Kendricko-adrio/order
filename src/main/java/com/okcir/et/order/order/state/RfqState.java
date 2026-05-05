package com.okcir.et.order.order.state;

import java.math.BigDecimal;

import com.okcir.et.order.order.state.dto.RfqOrderDTO;
import com.okcir.et.order.order.state.dto.RfqStatus;

public interface RfqState {
  void validate(RfqOrderDTO rfq);

  // Definisi aksi sesuai flow kamu
  void pickUp(RfqOrderDTO rfq, String dealerId);

  void processCreditCheckResult(RfqOrderDTO rfq, boolean isSuccess);

  void submitRate(RfqOrderDTO rfq, BigDecimal newRate);

  void decide(RfqOrderDTO rfq, boolean isAccepted);

  RfqStatus getStatus();
}

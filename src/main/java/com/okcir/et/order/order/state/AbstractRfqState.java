package com.okcir.et.order.order.state;

import java.math.BigDecimal;

import com.okcir.et.order.order.state.dto.RfqOrderDTO;
import com.okcir.et.order.order.state.dto.RfqStatus;

public abstract class AbstractRfqState implements RfqState {

  @Override
  public void validate(RfqOrderDTO rfq) {
    throw new UnsupportedOperationException("Unimplemented method 'validate'");
  }

  @Override
  public void pickUp(RfqOrderDTO rfq, String dealerId) {
    throw new UnsupportedOperationException("Unimplemented method 'pickUp'");
  }

  @Override
  public void processCreditCheckResult(RfqOrderDTO rfq, boolean isSuccess) {
    throw new UnsupportedOperationException("Unimplemented method 'processCreditCheckResult'");
  }

  @Override
  public void submitRate(RfqOrderDTO rfq, BigDecimal newRate) {
    throw new UnsupportedOperationException("Unimplemented method 'submitRate'");
  }

  @Override
  public void decide(RfqOrderDTO rfq, boolean isAccepted) {
    throw new UnsupportedOperationException("Unimplemented method 'decide'");
  }

  @Override
  public RfqStatus getStatus() {
    throw new UnsupportedOperationException("Unimplemented method 'getStatus'");
  }
}

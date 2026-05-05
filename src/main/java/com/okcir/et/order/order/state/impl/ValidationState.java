package com.okcir.et.order.order.state.impl;

import org.springframework.stereotype.Component;

import com.okcir.et.order.order.state.AbstractRfqState;
import com.okcir.et.order.order.state.dto.RfqOrderDTO;
import com.okcir.et.order.order.state.dto.RfqStatus;

@Component
public class ValidationState extends AbstractRfqState {

  @Override
  public void validate(RfqOrderDTO rfq) {
    // TODO Auto-generated method stub
  }

  @Override
  public RfqStatus getStatus() {
    return RfqStatus.CREATED;
  }
}

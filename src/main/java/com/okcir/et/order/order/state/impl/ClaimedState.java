package com.okcir.et.order.order.state.impl;

import org.springframework.stereotype.Component;

import com.okcir.et.order.order.state.AbstractRfqState;
import com.okcir.et.order.order.state.dto.RfqOrderDTO;
import com.okcir.et.order.order.state.dto.RfqStatus;

@Component
public class ClaimedState extends AbstractRfqState {

  @Override
  public void pickUp(RfqOrderDTO rfq, String dealerId) {
    // TODO Auto-generated method stub
    super.pickUp(rfq, dealerId);
  }

  @Override
  public RfqStatus getStatus() {
    return RfqStatus.CLAIMED;
  }

}

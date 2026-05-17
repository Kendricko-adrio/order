package com.okcir.et.order.rfq.state;

import com.okcir.et.order.rfq.state.dto.RfqOrderDTO;
import com.okcir.et.order.rfq.state.dto.RfqStatus;

public interface RfqState {
  void created(RfqOrderDTO rfq);
  void claimed(String orderId);
  void accepted(RfqOrderDTO rfq);
  void rejected(RfqOrderDTO rfq);
  void rateSubmit(RfqOrderDTO rfq);
  RfqStatus getStatus();
}

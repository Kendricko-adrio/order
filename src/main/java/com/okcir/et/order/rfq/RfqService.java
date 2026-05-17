package com.okcir.et.order.rfq;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.okcir.et.order.rfq.redis.OrderRedis;
import com.okcir.et.order.rfq.state.RfqState;
import com.okcir.et.order.rfq.state.RfqStateFactory;
import com.okcir.et.order.rfq.state.dto.RfqOrderDTO;
import com.okcir.et.order.rfq.state.dto.RfqStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

  private final RfqStateFactory rFactory;

  public OrderRedis createOrder(RfqOrderDTO order) {
    RfqState state = rFactory.getState(RfqStatus.CREATED);
    state.created(order);
    return null;
  }

  public Object claimOrder(String orderId) {
    RfqState state = rFactory.getState(RfqStatus.CLAIMED);
    state.claimed(orderId);
    return null;
  }

}

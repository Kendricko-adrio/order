package com.okcir.et.order.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.okcir.et.order.order.mapper.OrderMapper;
import com.okcir.et.order.order.redis.OrderRedis;
import com.okcir.et.order.order.redis.OrderRedisService;
import com.okcir.et.order.order.state.RfqState;
import com.okcir.et.order.order.state.RfqStateFactory;
import com.okcir.et.order.order.state.dto.RfqOrderDTO;
import com.okcir.et.order.order.state.dto.RfqStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

  private final RfqStateFactory rFactory;
  private final OrderRedisService orderRedisService;
  private final OrderMapper orderMapper;

  public OrderRedis createOrder(RfqOrderDTO order) {
    RfqState state = rFactory.getState(RfqStatus.CREATED);
    state.validate(order);
    return orderRedisService.createOrder(orderMapper.toOrderRedis(order));
  }

}

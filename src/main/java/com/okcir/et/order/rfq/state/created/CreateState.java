package com.okcir.et.order.order.state.created;

import com.okcir.et.order.order.mapper.OrderMapper;
import com.okcir.et.order.order.redis.OrderRedisService;
import com.okcir.et.order.trader.notify.ActiveTraderPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import com.okcir.et.order.order.state.dto.RfqOrderDTO;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateState extends AbstractCreateRFQ {

  private final OrderRedisService orderRedisService;
  private final OrderMapper orderMapper;
  private final ActiveTraderPublisher activeTraderPublisher;

  @Override
  public void created(RfqOrderDTO rfq) {
    log.info("Create RFQ state in redis");
    orderRedisService.createOrder(orderMapper.toOrderRedis(rfq));
    log.info("Notify active trader");
    activeTraderPublisher.notifyActiveTrader(rfq);
    log.info("inside created");
  }

}

package com.okcir.et.order.rfq.state.created;

import com.okcir.et.order.config.jwt.JwtContextUtil;
import com.okcir.et.order.rfq.jpa.entity.DealsRfqEvent;
import com.okcir.et.order.rfq.jpa.service.DealsRfqEventService;
import com.okcir.et.order.rfq.mapper.OrderMapper;
import com.okcir.et.order.rfq.redis.OrderRedis;
import com.okcir.et.order.rfq.redis.OrderRedisService;
import com.okcir.et.order.rfq.state.RfqState;
import com.okcir.et.order.rfq.state.dto.RfqStatus;
import com.okcir.et.order.trader.notify.ActiveTraderPublisher;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import com.okcir.et.order.rfq.state.dto.RfqOrderDTO;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CreateState extends AbstractCreateRFQ {

  private final OrderRedisService orderRedisService;
  private final DealsRfqEventService dealsRfqEventService;
  private final OrderMapper orderMapper;
  private final ActiveTraderPublisher activeTraderPublisher;

  @Override
  public void created(RfqOrderDTO rfq) {
    log.info("Mapping RfqOrderDTO to DealsRfqEvent entity");
    DealsRfqEvent entity = orderMapper.toDealsRfqEvent(rfq);
    entity.setClientId(JwtContextUtil.getCurrentUser().get().getUsername());
    entity.setOrderStatus(RfqStatus.CREATED.name());
    log.info("Saving DealsRfqEvent entity to database");
    DealsRfqEvent savedEntity = dealsRfqEventService.save(entity);

    log.info("Mapping saved entity to OrderRedis");
    OrderRedis orderRedis = orderMapper.toOrderRedis(savedEntity);

    log.info("Saving OrderRedis to Redis");
    orderRedisService.createOrder(orderRedis);

    log.info("Notify active trader");
    activeTraderPublisher.notifyActiveTrader(rfq);
    log.info("RFQ creation workflow completed successfully");
  }

}

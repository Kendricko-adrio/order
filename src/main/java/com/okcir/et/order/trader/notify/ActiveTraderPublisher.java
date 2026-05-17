package com.okcir.et.order.trader.notify;

import com.okcir.et.order.kafka.AbstractKafkaPublisher;
import com.okcir.et.order.rfq.state.dto.RfqOrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ActiveTraderPublisher extends AbstractKafkaPublisher<Object> {

    public ActiveTraderPublisher(KafkaTemplate kafkaTemplate) {
        super(kafkaTemplate);
    }

    public void notifyActiveTrader(RfqOrderDTO order) {
        log.info("notifyActiveTrader");
    }
}

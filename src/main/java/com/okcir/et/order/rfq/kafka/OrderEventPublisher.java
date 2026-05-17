//package com.okcir.et.order.rfq.kafka;
//
//import java.util.Map;
//
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//import com.okcir.et.order.kafka.AbstractKafkaPublisher;
//import com.okcir.et.order.kafka.KafkaHeaderConstants;
//import com.okcir.et.order.rfq.redis.OrderRedis;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * Example concrete Kafka publisher that demonstrates how to extend
// * {@link AbstractKafkaPublisher}.
// *
// * <p>
// * Publishes {@link OrderRedis} events to the {@code order-events} topic
// * with the order's UUID as the String key.
// */
//@Slf4j
//@Component
//public class OrderEventPublisher extends AbstractKafkaPublisher<OrderRedis> {
//
//  private static final String ORDER_EVENTS_TOPIC = "order-events";
//
//  public OrderEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
//    super(kafkaTemplate);
//  }
//
//  /**
//   * Sends an {@code ORDER_CREATED} event asynchronously.
//   *
//   * @param order the created order to publish
//   */
//  public void sendOrderCreatedEvent(OrderRedis order) {
//    String key = order.getId().toString();
//
//    Map<String, String> headers = Map.of(
//        KafkaHeaderConstants.EVENT_TYPE, "ORDER_CREATED",
//        KafkaHeaderConstants.SOURCE_SERVICE, "order-service");
//
//    publish(ORDER_EVENTS_TOPIC, key, order, headers)
//        .whenComplete((result, ex) -> {
//          if (ex != null) {
//            log.error("Failed to send ORDER_CREATED event for orderId [{}]", key, ex);
//          }
//        });
//  }
//}

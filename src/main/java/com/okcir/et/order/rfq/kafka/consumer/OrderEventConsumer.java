//package com.okcir.et.order.rfq.kafka.consumer;
//
//import java.util.Map;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//import com.okcir.et.order.kafka.AbstractKafkaConsumer;
//import com.okcir.et.order.kafka.KafkaHeaderConstants;
//import com.okcir.et.order.rfq.redis.OrderRedis;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * Example concrete Kafka consumer that demonstrates how to extend
// * {@link AbstractKafkaConsumer}.
// *
// * <p>
// * Listens to the {@code order-events} topic and processes
// * {@link OrderRedis} messages synchronously and in order per partition.
// */
//@Slf4j
//@Component
//public class OrderEventConsumer extends AbstractKafkaConsumer<OrderRedis> {
//
//  /**
//   * Entry point for the Kafka listener. Delegates to the base
//   * {@link #consume(ConsumerRecord)} template method.
//   *
//   * @param record the consumed record
//   */
//  @KafkaListener(
//      topics = "order-events",
//      groupId = "order-event-consumer-group",
//      containerFactory = "kafkaListenerContainerFactory")
//  public void listen(ConsumerRecord<String, OrderRedis> record) {
//    consume(record);
//  }
//
//  /**
//   * Synchronously handles an incoming {@link OrderRedis} event.
//   *
//   * <p>
//   * The event type is read from the {@code event-type} header so the
//   * consumer can route logic accordingly.
//   *
//   * @param key     the record key (order UUID as String)
//   * @param value   the deserialized {@link OrderRedis} payload
//   * @param headers all custom headers attached to the record
//   */
//  @Override
//  protected void handleMessage(String key, OrderRedis value, Map<String, String> headers) {
//    String eventType = headers.get(KafkaHeaderConstants.EVENT_TYPE);
//    log.info(
//        "Processing ORDER event type [{}] for orderId [{}]",
//        eventType,
//        key);
//
//    // Synchronous business logic goes here
//    // Example: update database, trigger downstream workflow, etc.
//  }
//}

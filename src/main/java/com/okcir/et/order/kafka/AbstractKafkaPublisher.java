package com.okcir.et.order.kafka;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Abstract base class for all asynchronous Kafka publishers.
 *
 * <p>
 * The key is always a {@link String}. The value is a generic Java object that
 * is automatically serialized to JSON by the configured
 * {@link org.springframework.kafka.support.serializer.JsonSerializer}.
 *
 * <p>
 * Publishing is fully asynchronous via
 * {@link CompletableFuture}{@code <SendResult>}. The returned future completes
 * exceptionally if the send fails, allowing callers (or a global async error
 * handler) to react to the error.
 *
 * @param <V> the type of the value payload
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractKafkaPublisher<V> {

  protected final KafkaTemplate<String, Object> kafkaTemplate;

  /**
   * Publishes a message to the specified Kafka topic asynchronously.
   *
   * @param topic   the target Kafka topic
   * @param key     the record key (always a String)
   * @param value   the record value (will be serialized to JSON)
   * @param headers optional custom headers; may be {@code null}
   * @return a {@link CompletableFuture} that completes with the
   *         {@link SendResult} or exceptionally on failure
   */
  public CompletableFuture<SendResult<String, Object>> publish(
      String topic,
      String key,
      V value,
      Map<String, String> headers) {

    ProducerRecord<String, Object> record = new ProducerRecord<>(topic, key, value);

    if (headers != null) {
      headers.forEach((headerKey, headerValue) -> record.headers()
          .add(headerKey, headerValue.getBytes(StandardCharsets.UTF_8)));
    }

    CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(record);

    return future.whenComplete((result, ex) -> {
      if (ex != null) {
        log.error("Failed to publish message to topic [{}] with key [{}]", topic, key, ex);
      } else {
        RecordMetadata metadata = result.getRecordMetadata();
        log.info(
            "Successfully published message to topic [{}] partition [{}] offset [{}] with key [{}]",
            metadata.topic(),
            metadata.partition(),
            metadata.offset(),
            key);
      }
    });
  }

  /**
   * Convenience overload that publishes without custom headers.
   *
   * @param topic the target Kafka topic
   * @param key   the record key
   * @param value the record value
   * @return a {@link CompletableFuture} that completes with the
   *         {@link SendResult} or exceptionally on failure
   */
  public CompletableFuture<SendResult<String, Object>> publish(
      String topic,
      String key,
      V value) {

    return publish(topic, key, value, Map.of());
  }
}

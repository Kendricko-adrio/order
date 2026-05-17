package com.okcir.et.order.kafka;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import lombok.extern.slf4j.Slf4j;

/**
 * Abstract base class for all synchronous Kafka consumers.
 *
 * <p>
 * The key is always a {@link String}. The value is a generic Java object that
 * is automatically deserialized from JSON by the configured
 * {@link org.springframework.kafka.support.serializer.JsonDeserializer}.
 *
 * <p>
 * Processing is <strong>synchronous</strong>: {@link #handleMessage} is invoked
 * on the Kafka listener thread and must complete before the next record in the
 * same partition is delivered. This guarantees in-order, one-by-one
 * processing.
 *
 * <p>
 * On exception the error is logged and <strong>re-thrown</strong> so that
 * Spring Kafka's container-level error handler (retry, dead-letter queue, etc.)
 * can take over.
 *
 * @param <V> the type of the value payload
 */
@Slf4j
public abstract class AbstractKafkaConsumer<V> {

  /**
   * Template method that concrete listeners should call from their
   * {@link org.springframework.kafka.annotation.KafkaListener} method.
   *
   * <p>
   * Performs the following steps:
   * <ol>
   * <li>Logs receipt of the record (topic, partition, offset, key)</li>
   * <li>Extracts custom headers into a {@link Map}</li>
   * <li>Delegates to {@link #handleMessage(String, Object, Map)}</li>
   * <li>On failure: logs and re-throws the exception</li>
   * </ol>
   *
   * @param record the consumed Kafka record
   */
  protected void consume(ConsumerRecord<String, V> record) {
    try {
      log.info(
          "Received message from topic [{}] partition [{}] offset [{}] with key [{}]",
          record.topic(),
          record.partition(),
          record.offset(),
          record.key());

      Map<String, String> headers = extractHeaders(record);

      handleMessage(record.key(), record.value(), headers);

    } catch (Exception ex) {
      log.error(
          "Error processing message from topic [{}] partition [{}] offset [{}] with key [{}]",
          record.topic(),
          record.partition(),
          record.offset(),
          record.key(),
          ex);
      throw ex;
    }
  }

  /**
   * Handles a successfully deserialized message.
   *
   * <p>
   * Concrete subclasses must implement their business logic here. The call is
   * executed synchronously on the Kafka listener thread.
   *
   * @param key     the record key (always a String)
   * @param value   the deserialized record value
   * @param headers all custom headers attached to the record; never
   *                {@code null}
   */
  protected abstract void handleMessage(String key, V value, Map<String, String> headers);

  private Map<String, String> extractHeaders(ConsumerRecord<String, V> record) {
    Map<String, String> headers = new HashMap<>();
    if (record.headers() != null) {
      record.headers().forEach(header -> {
        if (header.value() != null) {
          headers.put(header.key(), new String(header.value(), StandardCharsets.UTF_8));
        } else {
          headers.put(header.key(), null);
        }
      });
    }
    return headers;
  }
}

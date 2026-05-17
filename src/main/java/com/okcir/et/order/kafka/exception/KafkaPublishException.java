package com.okcir.et.order.kafka.exception;

/**
 * Runtime exception thrown when a Kafka publish operation fails.
 */
public class KafkaPublishException extends RuntimeException {

  public KafkaPublishException(String message, Throwable cause) {
    super(message, cause);
  }

  public KafkaPublishException(String message) {
    super(message);
  }
}

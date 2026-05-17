package com.okcir.et.order.kafka;

/**
 * Common Kafka header key constants used across publishers.
 */
public final class KafkaHeaderConstants {

  private KafkaHeaderConstants() {
    // utility class
  }

  /** Event type header, e.g. {@code ORDER_CREATED}. */
  public static final String EVENT_TYPE = "event-type";

  /** Correlation ID for distributed tracing. */
  public static final String CORRELATION_ID = "correlation-id";

  /** Timestamp of the event (ISO-8601 or epoch millis). */
  public static final String TIMESTAMP = "timestamp";

  /** Name of the service that produced the event. */
  public static final String SOURCE_SERVICE = "source-service";
}

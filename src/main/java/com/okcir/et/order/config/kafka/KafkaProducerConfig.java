package com.okcir.et.order.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;

/**
 * Kafka producer configuration.
 *
 * <p>
 * Configures a {@link ProducerFactory} with {@link StringSerializer} for keys
 * and {@link JacksonJsonSerializer} for values. Uses {@code @Value} properties so it
 * does not depend on Spring Boot's {@code KafkaProperties} auto-configuration
 * class.
 */
@Configuration
@EnableKafka
public class KafkaProducerConfig {

  @Value("${spring.kafka.bootstrap-servers:localhost:9092}")
  private String bootstrapServers;

  @Value("${spring.kafka.producer.acks:1}")
  private String acks;

  @Value("${spring.kafka.producer.retries:3}")
  private Integer retries;

  @Value("${spring.kafka.producer.batch-size:16384}")
  private Integer batchSize;

  @Value("${spring.kafka.producer.buffer-memory:33554432}")
  private Long bufferMemory;

  @Value("${spring.kafka.producer.compression-type:none}")
  private String compressionType;

  /**
   * Creates a {@link ProducerFactory} bean configured with String keys and JSON
   * values.
   *
   * @return configured producer factory
   */
  @Bean
  public ProducerFactory<String, Object> producerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.ACKS_CONFIG, acks);
    props.put(ProducerConfig.RETRIES_CONFIG, retries);
    props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
    props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
    props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, compressionType);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JacksonJsonSerializer.class);

    return new DefaultKafkaProducerFactory<>(props);
  }

  /**
   * Creates a {@link KafkaTemplate} bean backed by the configured
   * {@link ProducerFactory}.
   *
   * @return configured Kafka template
   */
  @Bean
  public KafkaTemplate<String, Object> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }
}

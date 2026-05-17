package com.okcir.et.order.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;

/**
 * Kafka consumer configuration.
 *
 * <p>
 * Configures a {@link ConsumerFactory} with {@link StringDeserializer} for keys
 * and {@link ErrorHandlingDeserializer} wrapping {@link JacksonJsonDeserializer} for
 * values. Uses {@code @Value} properties so it does not depend on Spring Boot's
 * {@code KafkaProperties} auto-configuration class.
 */
@Configuration
public class KafkaConsumerConfig {

  @Value("${spring.kafka.bootstrap-servers:localhost:9092}")
  private String bootstrapServers;

  @Value("${spring.kafka.consumer.group-id:default-group}")
  private String groupId;

  @Value("${spring.kafka.consumer.auto-offset-reset:earliest}")
  private String autoOffsetReset;

  @Value("${spring.kafka.consumer.enable-auto-commit:true}")
  private Boolean enableAutoCommit;

  /**
   * Creates a {@link ConsumerFactory} bean configured with String keys and JSON
   * values.
   *
   * <p>
   * The JSON deserializer is set to trust all packages under
   * {@code com.okcir.et.order} so any application DTO or entity can be
   * deserialized automatically using the type headers added by the producer's
   * JSON serializer.
   *
   * @return configured consumer factory
   */
  @Bean
  public ConsumerFactory<String, Object> consumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
    props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JacksonJsonDeserializer.class.getName());
    props.put(JacksonJsonDeserializer.TRUSTED_PACKAGES, "com.okcir.et.order");

    return new DefaultKafkaConsumerFactory<>(props);
  }

  /**
   * Creates a {@link ConcurrentKafkaListenerContainerFactory} bean backed by the
   * configured {@link ConsumerFactory}.
   *
   * <p>
   * Listeners referencing this factory (via
   * {@code containerFactory = "kafkaListenerContainerFactory"}) will process
   * messages synchronously and in order within each partition.
   *
   * @return configured listener container factory
   */
  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, Object> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
}

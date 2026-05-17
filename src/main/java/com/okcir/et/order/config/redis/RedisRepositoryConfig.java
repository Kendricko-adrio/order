package com.okcir.et.order.config.redis;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.redis.core.convert.RedisCustomConversions;

@Configuration
public class RedisRepositoryConfig {

    /**
     * Converter: BigDecimal -> byte[] (Writing to Redis).
     * Must convert directly to byte[] so Spring Data Redis doesn't need to chain converters.
     */
    @WritingConverter
    public enum BigDecimalToBytesConverter implements Converter<BigDecimal, byte[]> {
        INSTANCE;

        @Override
        public byte[] convert(BigDecimal source) {
            return source == null ? null : source.toPlainString().getBytes(StandardCharsets.UTF_8);
        }
    }

    /**
     * Converter: byte[] -> BigDecimal (Reading from Redis).
     */
    @ReadingConverter
    public enum BytesToBigDecimalConverter implements Converter<byte[], BigDecimal> {
        INSTANCE;

        @Override
        public BigDecimal convert(byte[] source) {
            return source == null || source.length == 0 ? null : new BigDecimal(new String(source, StandardCharsets.UTF_8));
        }
    }

    @Bean
    public RedisCustomConversions redisCustomConversions() {
        return new RedisCustomConversions(Arrays.asList(
            BigDecimalToBytesConverter.INSTANCE,
            BytesToBigDecimalConverter.INSTANCE
        ));
    }
}
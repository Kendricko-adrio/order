package com.okcir.et.order.config.redis;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.redis.core.convert.RedisCustomConversions;

@Configuration
public class RedisRepositoryConfig {

    // 1. Converter: BigDecimal -> String (Writing to Redis)
    @WritingConverter
    public enum BigDecimalToStringConverter implements Converter<BigDecimal, String> {
        INSTANCE;

        @Override
        public String convert(BigDecimal source) {
            return source == null ? null : source.toPlainString();
            // .toPlainString() prevents scientific notation like 1E+2
        }
    }

    // 2. Converter: String -> BigDecimal (Reading from Redis)
    @ReadingConverter
    public enum StringToBigDecimalConverter implements Converter<byte[], BigDecimal> {
        INSTANCE;

        @Override
        public BigDecimal convert(byte[] source) {
            return source == null || source.length == 0 ? null : new BigDecimal(new String(source));
        }
    }

    // 3. Register the converters into Spring Data Redis
    @Bean
    public RedisCustomConversions redisCustomConversions() {
        return new RedisCustomConversions(Arrays.asList(BigDecimalToStringConverter.INSTANCE, StringToBigDecimalConverter.INSTANCE));
    }
}
package com.okcir.et.order.trader;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "session:trader")
public class TraderRedis {
    @Id
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}

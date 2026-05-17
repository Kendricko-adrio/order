package com.okcir.et.order.trader;

import com.okcir.et.order.rfq.redis.OrderRedis;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Profile("dev")
@RequestMapping("api/trader/dev")
public class TraderController {

    public final TraderRedisRepository traderRedisRepository;
    @PostMapping
    public TraderRedis save(@RequestBody TraderRedis traderRedis ) {
        return traderRedisRepository.save(traderRedis);
    }
}

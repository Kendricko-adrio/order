package com.okcir.et.order.trader;

import com.okcir.et.order.rfq.redis.OrderRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TraderRedisRepository extends CrudRepository<TraderRedis, String> {
}

package com.okcir.et.order.order.redis;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRedisRepository extends CrudRepository<OrderRedis, UUID> {

}
package com.okcir.et.order.rfq.redis;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderRedisService {

  private final OrderRedisRepository repository;

  public OrderRedis getOrderById(UUID id) {
    return repository.findById(id).orElse(null);
  }

  public OrderRedis createOrder(OrderRedis order) {
    return repository.save(order);
  }

  public OrderRedis updateOrder(OrderRedis order) {
    return repository.save(order);
  }

  public void deleteOrder(UUID id) {
    repository.deleteById(id);
  }
}

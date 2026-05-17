package com.okcir.et.order.rfq.jpa.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.okcir.et.order.rfq.jpa.entity.DealsRfqEvent;
import com.okcir.et.order.rfq.jpa.repository.DealsRfqEventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DealsRfqEventService {

  private final DealsRfqEventRepository repository;

  public DealsRfqEvent save(DealsRfqEvent entity) {
    return repository.save(entity);
  }

  public Optional<DealsRfqEvent> getRfqByOrderId(UUID orderId) {
    return repository.findByOrderId(orderId);
  }
}

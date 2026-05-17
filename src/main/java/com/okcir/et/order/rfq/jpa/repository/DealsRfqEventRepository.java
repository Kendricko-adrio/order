package com.okcir.et.order.rfq.jpa.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.okcir.et.order.rfq.jpa.entity.DealsRfqEvent;

@Repository
public interface DealsRfqEventRepository extends JpaRepository<DealsRfqEvent, Long> {

  Optional<DealsRfqEvent> findByOrderId(UUID orderId);
}

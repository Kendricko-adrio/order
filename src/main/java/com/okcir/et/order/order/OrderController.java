package com.okcir.et.order.order;

import org.springframework.web.bind.annotation.RestController;

import com.okcir.et.order.order.state.dto.RfqOrderDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;

  @GetMapping
  public Object getOrder() {
    return "Order";
  }

  @PostMapping
  public Object createOrder(@RequestBody RfqOrderDTO orderDTO) {
    return orderService.createOrder(orderDTO);
  }
}
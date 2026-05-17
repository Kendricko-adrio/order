package com.okcir.et.order.order;

import org.springframework.web.bind.annotation.*;

import com.okcir.et.order.order.state.dto.RfqOrderDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;


  @GetMapping
  public Object getOrder() {
    return "Order";
  }

  @PostMapping()
  public Object createOrder(@RequestBody RfqOrderDTO orderDTO) {
    return orderService.createOrder(orderDTO);
  }

  @PostMapping("/{order-id}/claim")
  public Object claimOrder(@PathVariable("order-id") String orderId) {
    return orderService.claimOrder(orderId);
  }

}
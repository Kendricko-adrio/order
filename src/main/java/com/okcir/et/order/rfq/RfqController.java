package com.okcir.et.order.rfq;

import org.springframework.web.bind.annotation.*;

import com.okcir.et.order.rfq.state.dto.RfqOrderDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/rfq")
@RequiredArgsConstructor
public class RfqController {
  private final RfqService orderService;


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
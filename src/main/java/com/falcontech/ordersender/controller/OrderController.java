package com.falcontech.ordersender.controller;

import com.falcontech.ordersender.model.web.Order;
import com.falcontech.ordersender.model.web.OrderSummary;
import com.falcontech.ordersender.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("order/")
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<OrderSummary> postOrder(@Valid @RequestBody Mono<Order> newOrder) {
    return orderService.submitToQue(newOrder);
  }
}

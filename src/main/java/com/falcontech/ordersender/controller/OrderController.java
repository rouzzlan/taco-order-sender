package com.falcontech.ordersender.controller;

import com.falcontech.ordersender.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("order/")
public class OrderController {

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Order> postOrder(@Valid @RequestBody Mono<Order> newOrder) {
    return newOrder;
  }
}

package com.falcontech.ordersender.service;

import com.falcontech.ordersender.model.web.Order;
import com.falcontech.ordersender.model.web.OrderSummary;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class OrderService {
  public Mono<OrderSummary> submitToQue(Mono<Order> orderMono) {
    return orderMono
        .map(
            order -> new com.falcontech.ordersender.model.que.Order(order).orderSummary())
        .map(orderSummary -> orderSummary);
  }
}

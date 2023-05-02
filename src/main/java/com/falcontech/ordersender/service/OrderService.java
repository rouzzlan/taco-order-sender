package com.falcontech.ordersender.service;

import com.falcontech.ordersender.model.web.Order;
import com.falcontech.ordersender.model.web.OrderSummary;
import com.falcontech.ordersender.service.queue.OrderQueueService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrderService {
  private final OrderQueueService orderQueueService;

  public OrderService(OrderQueueService orderQueueService) {
    this.orderQueueService = orderQueueService;
  }

  public Mono<OrderSummary> sendOrder(Mono<Order> orderMono) {
    return orderQueueService
        .createOrder(orderMono)
        .map(com.falcontech.ordersender.model.queue.Order::orderSummary);
  }
}

package com.falcontech.ordersender.service;

import com.falcontech.ordersender.model.web.Order;
import com.falcontech.ordersender.model.web.OrderSummary;
import com.falcontech.ordersender.service.messaging.OrderMessagingService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrderService {
  private final OrderMessagingService orderMessagingService;

  public OrderService(OrderMessagingService orderMessagingService) {
    this.orderMessagingService = orderMessagingService;
  }

  public Mono<OrderSummary> sendOrder(Mono<Order> orderMono) {
    return orderMono
        .map(
            order -> {
              com.falcontech.ordersender.model.queue.Order _order = new com.falcontech.ordersender.model.queue.Order(order);
              orderMessagingService.sendOrder(_order);
              return _order.orderSummary();
            })
        .map(orderSummary -> orderSummary);
  }
  
}

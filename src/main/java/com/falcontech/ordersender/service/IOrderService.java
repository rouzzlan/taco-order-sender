package com.falcontech.ordersender.service;

import com.falcontech.ordersender.model.web.Order;
import com.falcontech.ordersender.model.web.OrderSummary;
import reactor.core.publisher.Mono;

public interface IOrderService {
    public Mono<OrderSummary> sendOrder(Mono<Order> orderMono);
}

package com.falcontech.ordersender.service;

import com.falcontech.ordersender.model.web.Order;
import com.falcontech.ordersender.model.web.OrderSummary;
import java.util.UUID;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Profile("rest-dev")
public class RestTestOrderService implements IOrderService{

    @Override
    public Mono<OrderSummary> sendOrder(Mono<Order> orderMono) {
        return orderMono.map(order -> {
            return new OrderSummary(UUID.randomUUID(), order.name());
        });
    }
}

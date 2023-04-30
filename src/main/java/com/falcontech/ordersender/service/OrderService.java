package com.falcontech.ordersender.service;

import com.falcontech.ordersender.model.web.Order;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class OrderService {
    public Mono<Order> submitToQue(Mono<Order> orderMono) {
        return orderMono;
    }
}

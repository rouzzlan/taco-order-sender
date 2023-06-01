package com.falcontech.ordersender.controller.handler;

import com.falcontech.ordersender.model.web.Order;
import com.falcontech.ordersender.model.web.OrderSummary;
import com.falcontech.ordersender.service.IOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class OrderHandler {

  private final IOrderService service;

  public OrderHandler(IOrderService service) {
    this.service = service;
  }

  /**
   * Recieve POST request from the router to save an Order object into the MongoDB
   *
   * @param request ServerRequest
   * @return ServerResponse with the Order object in the body
   */
  public Mono<ServerResponse> createOrder(ServerRequest request) {
    // 1. Extract JSON object from Server Request.
    Mono<Order> dto = request.bodyToMono(Order.class);

    // 2. Send request to service.
    Mono<OrderSummary> result = service.sendOrder(dto);

    // 3. Return server response and the order object in the body.
    return ServerResponse.status(HttpStatus.CREATED)
        .contentType(MediaType.APPLICATION_JSON)
        .body(result, OrderSummary.class);
  }
}

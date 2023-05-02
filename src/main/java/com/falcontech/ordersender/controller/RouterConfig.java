package com.falcontech.ordersender.controller;

import com.falcontech.ordersender.controller.handler.OrderHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {
  private static final String ENDPOINT_PATH = "/order";

  @Bean
  public RouterFunction<ServerResponse> routerFunction(OrderHandler handler) {
    return RouterFunctions.route(RequestPredicates.POST(ENDPOINT_PATH), handler::createOrder);
  }
}

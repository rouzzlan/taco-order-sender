package com.falcontech.ordersender.model.queue;

import com.falcontech.ordersender.model.web.OrderSummary;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Order {
  private final UUID uuid = UUID.randomUUID();
  private final String name;
  private final String email;
  private final CCard cCard;
  private final Address address;

  public Order(com.falcontech.ordersender.model.web.Order order) {
    this.email = order.email();
    this.name = order.name();
    this.cCard = new CCard(order.cCard());
    this.address = new Address(order.address());
  }

  public OrderSummary orderSummary() {
    return new OrderSummary(uuid, name);
  }
}

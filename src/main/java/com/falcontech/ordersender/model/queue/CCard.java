package com.falcontech.ordersender.model.queue;

import lombok.Getter;

@Getter
public class CCard {
  private final String number;
  private final String expiration;
  private final Integer cvv;
  private final String owner;

  public CCard(com.falcontech.ordersender.model.web.CCard cCard) {
    this.number = cCard.number();
    this.expiration = cCard.expiration();
    this.cvv = cCard.cvv();
    this.owner = cCard.owner();
  }
}

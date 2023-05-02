package com.falcontech.ordersender.model.queue;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class CCard implements Serializable {
  private final String ccNumber;
  private final String ccExpiration;
  private final Integer cvv;

  public CCard(com.falcontech.ordersender.model.web.CCard cCard) {
    this.ccNumber = cCard.ccNumber();
    this.ccExpiration = cCard.ccExpiration();
    this.cvv = cCard.cvv();
  }
}

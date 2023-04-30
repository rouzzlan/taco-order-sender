package com.falcontech.ordersender.model.que;

import lombok.Getter;

@Getter
public class CCard {
  private final String ccNumber;
  private final String ccExpiration;
  private final Integer cvv;

  public CCard(com.falcontech.ordersender.model.web.CCard cCard) {
    this.ccNumber = cCard.ccNumber();
    this.ccExpiration = cCard.ccExpiration();
    this.cvv = cCard.cvv();
  }
}

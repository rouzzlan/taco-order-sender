package com.falcontech.ordersender.model.queue;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class Address implements Serializable {
  private final String street;
  private final String city;
  private final String state;
  private final String zip;

  public Address(com.falcontech.ordersender.model.web.Address address) {
    this.street = address.street();
    this.city = address.city();
    this.zip = address.zip();
    this.state = address.state();
  }
}

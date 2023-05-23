package com.falcontech.ordersender.model.queue;

import lombok.Getter;

@Getter
public class Address {
  private final String street;
  private final String city;
  private final String state;
  private final String zip;
  private final String country;

  public Address(com.falcontech.ordersender.model.web.Address address) {
    this.street = address.street();
    this.city = address.city();
    this.zip = address.zip();
    this.state = address.state();
    this.country = address.country();
  }
}

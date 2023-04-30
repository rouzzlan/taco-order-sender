package com.falcontech.ordersender.model;

import javax.validation.Valid;
import javax.validation.constraints.Email;

public record Order(
    String name,
    @Email(message = "not valid email") String email,
    @Valid CCard cCard,
    @Valid Address address) {}

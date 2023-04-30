package com.falcontech.ordersender.model.web;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record Order(
    @NotBlank String name,
    @Email(message = "not valid email") String email,
    @Valid CCard cCard,
    @Valid Address address) {}

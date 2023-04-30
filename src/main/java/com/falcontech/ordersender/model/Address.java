package com.falcontech.ordersender.model;

import javax.validation.constraints.NotBlank;

public record Address(@NotBlank String street,
                      @NotBlank String city,
                      @NotBlank String state,
                      @NotBlank String  zip) {}

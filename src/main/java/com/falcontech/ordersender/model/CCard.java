package com.falcontech.ordersender.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record CCard(
    @NotBlank
        @Pattern(
            regexp =
                "^(?:4[0-9]{12}(?:[0-9]{3})?|(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12})$",
            message = "not valid credit card number")
        String ccNumber,
    @Pattern(regexp = "^((0[1-9]|1[012])([- /.])(\\d\\d))$", message = "not valid expiration date")
        String ccExpiration,
    @Min(1) @Max(999) Integer cvv) {}

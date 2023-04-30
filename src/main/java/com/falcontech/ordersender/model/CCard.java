package com.falcontech.ordersender.model;

public record CCard(Integer ccNumber,
                    String ccExpiration,
                    Integer cvv) {}

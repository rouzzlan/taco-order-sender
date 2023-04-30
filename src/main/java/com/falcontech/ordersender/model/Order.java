package com.falcontech.ordersender.model;

public record Order(String name,
                    String email,
                    CCard cCard,
                    Address address) {}

package com.falcontech.ordersender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.falcontech.ordersender.config.properties")
public class OrderSenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderSenderApplication.class, args);
    }

}

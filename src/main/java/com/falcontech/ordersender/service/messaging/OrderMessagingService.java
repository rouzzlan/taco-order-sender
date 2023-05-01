package com.falcontech.ordersender.service.messaging;

import com.falcontech.ordersender.model.queue.Order;

public interface OrderMessagingService {

    void sendOrder(Order order);

}

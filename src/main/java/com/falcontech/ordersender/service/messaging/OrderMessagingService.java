package com.falcontech.ordersender.service.messaging;

import com.falcontech.ordersender.model.que.Order;

public interface OrderMessagingService {

    void sendOrder(Order order);

}

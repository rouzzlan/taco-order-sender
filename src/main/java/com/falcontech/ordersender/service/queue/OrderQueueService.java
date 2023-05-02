package com.falcontech.ordersender.service.queue;

import com.falcontech.ordersender.model.queue.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.QueueSpecification;
import reactor.rabbitmq.Sender;

@Service
public class OrderQueueService {

  final Sender sender;

  OrderQueueService(Sender sender) {
    this.sender = sender;
  }

  // Name of our Queue
  private static final String QUEUE = "orderqueue";
  // slf4j logger

  /**
   * Send a new Order to the message queue.
   *
   * @param dto Order DTO
   * @return Mono Order
   */
  public Mono<Order> createOrder(Mono<com.falcontech.ordersender.model.web.Order> dto) {

    return dto.flatMap(
        orderDto -> {

          // Validations ...

          // Map OrderDTO to Order object
          Order order = mapperOrderDTOToEntity(orderDto);
          ObjectMapper mapper = new ObjectMapper();
          String json;
          try {
            // Serialize object to json
            json = mapper.writeValueAsString(order);
            System.out.println(json);
            // Serialize json to bytes
            byte[] orderSerialized = SerializationUtils.serialize(json);
            // Outbound Message that will be sent by the Sender
            Flux<OutboundMessage> outbound =
                Flux.just(new OutboundMessage("", QUEUE, orderSerialized));

            // Declare the queue then send the flux of messages.
            sender
                .declareQueue(QueueSpecification.queue(QUEUE))
                .thenMany(sender.sendWithPublishConfirms(outbound))
                .subscribe(
                    m -> {
                      System.out.println("Message sent");
                    });
          } catch (JsonProcessingException e1) {
            e1.printStackTrace();
          }

          // Return posted object to the client.
          return Mono.just(order);
        });
  }

  /**
   * Mapper for OrderDTO to Entity. It will call calculate total cost of the order object.
   *
   * @param dto Order DTO
   * @return Order from OrderDTO
   */
  public Order mapperOrderDTOToEntity(com.falcontech.ordersender.model.web.Order dto) {
    return new Order(dto);
  }
}

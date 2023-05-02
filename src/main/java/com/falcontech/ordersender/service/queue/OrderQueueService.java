package com.falcontech.ordersender.service.queue;

import com.falcontech.ordersender.config.properties.QueueProps;
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
  private final QueueProps queueProps;

  OrderQueueService(Sender sender, QueueProps queueProps) {
    this.sender = sender;
      this.queueProps = queueProps;
  }

  // Name of our Queu
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
                Flux.just(new OutboundMessage("", queueProps.getQueueName(), orderSerialized));

            // Declare the queue then send the flux of messages.
            sender
                .declareQueue(QueueSpecification.queue(queueProps.getQueueName()))
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

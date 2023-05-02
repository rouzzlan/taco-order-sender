package com.falcontech.ordersender.service.queue;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.rabbitmq.RabbitFlux;
import reactor.rabbitmq.Sender;
import reactor.rabbitmq.SenderOptions;

@Configuration
public class RabbitConfig {

  @Bean
  Mono<Connection> connectionMono() {
    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setHost("docker-pool.localdomain");
    connectionFactory.setPort(5673);
    connectionFactory.setUsername("user");
    connectionFactory.setPassword("pass");
    connectionFactory.useNio();
    return Mono.fromCallable(() -> connectionFactory.newConnection("reactor-rabbit")).cache();
  }

  @Bean
  public SenderOptions senderOptions(Mono<Connection> connectionMono) {
    return new SenderOptions()
        .connectionMono(connectionMono)
        .resourceManagementScheduler(Schedulers.boundedElastic());
  }

  @Bean
  public Sender sender(SenderOptions senderOptions) {
    return RabbitFlux.createSender(senderOptions);
  }
}

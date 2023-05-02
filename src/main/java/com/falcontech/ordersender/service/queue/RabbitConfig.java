package com.falcontech.ordersender.service.queue;

import com.falcontech.ordersender.config.properties.QueueProps;
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
  private final QueueProps queueProps;

  public RabbitConfig(QueueProps queueProps) {
    this.queueProps = queueProps;
  }

  @Bean
  Mono<Connection> connectionMono() {
    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setHost(queueProps.getHost());
    connectionFactory.setPort(queueProps.getPort());
    connectionFactory.setUsername(queueProps.getUser());
    connectionFactory.setPassword(queueProps.getPasswd());
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

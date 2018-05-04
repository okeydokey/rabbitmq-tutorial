package com.example.rabbitmq.tutorials.tut1;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tut1","hello-world"})
@Configuration
public class Tut1Config {

  @Bean
  public CachingConnectionFactory connectionFactory(
          @Value("${rabbitmq.host:localhost}") String host,
          @Value("${rabbitmq.port:5672}") Integer port,
          @Value("${rabbitmq.username:guest}") String userName,
          @Value("${rabbitmq.password:guest}") String password) {

    CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host, port);
    cachingConnectionFactory.setUsername(userName);
    cachingConnectionFactory.setPassword(password);
    return cachingConnectionFactory;
  }

  @Bean
  public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
    RabbitTemplate template = new RabbitTemplate(connectionFactory);
    return template;
  }
  @Bean
  public Queue hello() {
    return new Queue("hello");
  }

  @Profile("receiver")
  @Bean
  public Tut1Receiver receiver() {
    return new Tut1Receiver();
  }

  @Profile("sender")
  @Bean
  public Tut1Sender sender() {
    return new Tut1Sender();
  }
}
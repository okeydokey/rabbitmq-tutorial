package com.example.rabbitmq.tutorials.fileio;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Profile({"fileio"})
@Configuration
public class FileIOConfig {

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

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
    template.setMessageConverter(jsonMessageConverter());
    return template;
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  public Executor taskScheduler() {
    return Executors.newScheduledThreadPool(10);
  }

  @Bean
  public DirectExchange direct() {
    return new DirectExchange("tut.direct");
  }

  @Profile("receiver")
  private static class ReceiverConfig {

    @Bean
    public Queue webAuditQueue() {
      return new Queue(ApplicationType.WEB.name());
    }

    @Bean
    public Queue admAuditQueue() {
      return new Queue(ApplicationType.ADM.name());
    }

    @Bean
    public Queue notificationAuditQueue() {
      return new Queue(ApplicationType.NOTIFICATION.name());
    }

    @Bean
    public Queue pointAuditQueue() {
      return new Queue(ApplicationType.POINT.name());
    }

    @Bean
    public Queue userAuditQueue() {
      return new Queue(ApplicationType.USER.name());
    }

    @Bean
    public Queue payAuditQueue() {
      return new Queue(ApplicationType.PAY.name());
    }

    @Bean
    public Binding bindingWeb(DirectExchange direct,
                             Queue webAuditQueue) {
      return BindingBuilder.bind(webAuditQueue)
              .to(direct)
              .with(ApplicationType.WEB);
    }

    @Bean
    public Binding bindingAmd(DirectExchange direct,
                             Queue admAuditQueue) {
      return BindingBuilder.bind(admAuditQueue)
              .to(direct)
              .with(ApplicationType.ADM);
    }

    @Bean
    public Binding bindingNotification(DirectExchange direct,
                             Queue notificationAuditQueue) {
      return BindingBuilder.bind(notificationAuditQueue)
              .to(direct)
              .with(ApplicationType.NOTIFICATION);
    }

    @Bean
    public Binding bindingPoint(DirectExchange direct,
                             Queue pointAuditQueue) {
      return BindingBuilder.bind(pointAuditQueue)
              .to(direct)
              .with(ApplicationType.POINT);
    }

    @Bean
    public Binding bindingPay(DirectExchange direct,
                             Queue userAuditQueue) {
      return BindingBuilder.bind(userAuditQueue)
              .to(direct)
              .with(ApplicationType.USER);
    }

    @Bean
    public Binding bindingUser(DirectExchange direct,
                            Queue payAuditQueue) {
      return BindingBuilder.bind(payAuditQueue)
              .to(direct)
              .with(ApplicationType.PAY);
    }

    @Bean
    public FileIOReceiver receiver() {
      return new FileIOReceiver();
    }
  }

  @Profile("sender")
  @Bean
  public FileIOSender sender() {
    return new FileIOSender();
  }

}
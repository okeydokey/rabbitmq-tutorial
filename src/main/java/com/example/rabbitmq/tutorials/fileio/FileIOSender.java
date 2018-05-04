package com.example.rabbitmq.tutorials.fileio;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class FileIOSender {

  @Autowired
  private RabbitTemplate template;

  @Autowired
  private DirectExchange direct;

  private int index;

  private int count;

  private final ApplicationType[] keys = {ApplicationType.WEB,
          ApplicationType.ADM, ApplicationType.NOTIFICATION,
          ApplicationType.POINT, ApplicationType.USER, ApplicationType.PAY};

  @Scheduled(fixedDelay = 1, initialDelay = 500)
  public void send() {

    if (++this.index == keys.length) {
      this.index = 0;
    }
    ApplicationType key = keys[this.index];

    AuditLog log = AuditLog.builder().action("modify user info .." + ++this.count)
            .applicationType(key)
            .eventType(EventType.DATA_ACCESS)
            .happened(new Date())
            .serverName(key.name())
            .threadName(Thread.currentThread().getName())
            .userId(1).build();

    template.convertAndSend(direct.getName(), key.name(), log);
//    System.out.println(" [x] Sent '" + log + "'");
  }

  @Scheduled(fixedDelay = 1, initialDelay = 500)
  public void send2() {

    if (++this.index == keys.length) {
      this.index = 0;
    }
    ApplicationType key = keys[this.index];

    AuditLog log = AuditLog.builder().action("modify user info .." + ++this.count)
            .applicationType(key)
            .eventType(EventType.DATA_ACCESS)
            .happened(new Date())
            .serverName(key.name())
            .threadName(Thread.currentThread().getName())
            .userId(1).build();

    template.convertAndSend(direct.getName(), key.name(), log);
//    System.out.println(" [x] Sent '" + log + "'");
  }

  @Scheduled(fixedDelay = 1, initialDelay = 500)
  public void send3() {

    if (++this.index == keys.length) {
      this.index = 0;
    }
    ApplicationType key = keys[this.index];

    AuditLog log = AuditLog.builder().action("modify user info .." + ++this.count)
            .applicationType(key)
            .eventType(EventType.DATA_ACCESS)
            .happened(new Date())
            .serverName(key.name())
            .threadName(Thread.currentThread().getName())
            .userId(1).build();

    template.convertAndSend(direct.getName(), key.name(), log);
//    System.out.println(" [x] Sent '" + log + "'");
  }

  @Scheduled(fixedDelay = 1, initialDelay = 500)
  public void send4() {

    if (++this.index == keys.length) {
      this.index = 0;
    }
    ApplicationType key = keys[this.index];

    AuditLog log = AuditLog.builder().action("modify user info .." + ++this.count)
            .applicationType(key)
            .eventType(EventType.DATA_ACCESS)
            .happened(new Date())
            .serverName(key.name())
            .threadName(Thread.currentThread().getName())
            .userId(1).build();

    template.convertAndSend(direct.getName(), key.name(), log);
//    System.out.println(" [x] Sent '" + log + "'");
  }

  @Scheduled(fixedDelay = 1, initialDelay = 500)
  public void send5() {

    if (++this.index == keys.length) {
      this.index = 0;
    }
    ApplicationType key = keys[this.index];

    AuditLog log = AuditLog.builder().action("modify user info .." + ++this.count)
            .applicationType(key)
            .eventType(EventType.DATA_ACCESS)
            .happened(new Date())
            .serverName(key.name())
            .threadName(Thread.currentThread().getName())
            .userId(1).build();

    template.convertAndSend(direct.getName(), key.name(), log);
//    System.out.println(" [x] Sent '" + log + "'");
  }

  @Scheduled(fixedDelay = 1, initialDelay = 500)
  public void send6() {

    if (++this.index == keys.length) {
      this.index = 0;
    }
    ApplicationType key = keys[this.index];

    AuditLog log = AuditLog.builder().action("modify user info .." + ++this.count)
            .applicationType(key)
            .eventType(EventType.DATA_ACCESS)
            .happened(new Date())
            .serverName(key.name())
            .threadName(Thread.currentThread().getName())
            .userId(1).build();

    template.convertAndSend(direct.getName(), key.name(), log);
//    System.out.println(" [x] Sent '" + log + "'");
  }

  @Scheduled(fixedDelay = 1, initialDelay = 500)
  public void send7() {

    if (++this.index == keys.length) {
      this.index = 0;
    }
    ApplicationType key = keys[this.index];

    AuditLog log = AuditLog.builder().action("modify user info .." + ++this.count)
            .applicationType(key)
            .eventType(EventType.DATA_ACCESS)
            .happened(new Date())
            .serverName(key.name())
            .threadName(Thread.currentThread().getName())
            .userId(1).build();

    template.convertAndSend(direct.getName(), key.name(), log);
//    System.out.println(" [x] Sent '" + log + "'");
  }

  @Scheduled(fixedDelay = 1, initialDelay = 500)
  public void send8() {

    if (++this.index == keys.length) {
      this.index = 0;
    }
    ApplicationType key = keys[this.index];

    AuditLog log = AuditLog.builder().action("modify user info .." + ++this.count)
            .applicationType(key)
            .eventType(EventType.DATA_ACCESS)
            .happened(new Date())
            .serverName(key.name())
            .threadName(Thread.currentThread().getName())
            .userId(1).build();

    template.convertAndSend(direct.getName(), key.name(), log);
//    System.out.println(" [x] Sent '" + log + "'");
  }
}

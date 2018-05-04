package com.example.rabbitmq.tutorials.fileio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Date;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileIOReceiver {

  @Autowired
  private ObjectMapper objectMapper;

  @RabbitListener(queues = "#{webAuditQueue.name}")
  public void receive1(AuditLog in) throws InterruptedException, JsonProcessingException {
    receive(in, ApplicationType.WEB.name());
  }

  @RabbitListener(queues = "#{admAuditQueue.name}")
  public void receive2(AuditLog in) throws InterruptedException, JsonProcessingException {
    receive(in, ApplicationType.ADM.name());
  }

  @RabbitListener(queues = "#{notificationAuditQueue.name}")
  public void receive3(AuditLog in) throws InterruptedException, JsonProcessingException {
    receive(in, ApplicationType.NOTIFICATION.name());
  }

  @RabbitListener(queues = "#{pointAuditQueue.name}")
  public void receive4(AuditLog in) throws InterruptedException, JsonProcessingException {
    receive(in, ApplicationType.POINT.name());
  }

  @RabbitListener(queues = "#{userAuditQueue.name}")
  public void receive5(AuditLog in) throws InterruptedException, JsonProcessingException {
    receive(in, ApplicationType.USER.name());
  }

  @RabbitListener(queues = "#{payAuditQueue.name}")
  public void receive6(AuditLog in) throws InterruptedException, JsonProcessingException {
    receive(in, ApplicationType.PAY.name());
  }

  public void receive(AuditLog in, String receiver) throws
          InterruptedException, JsonProcessingException {
    StopWatch watch = new StopWatch();
    watch.start();
    System.out.println("instance " + receiver + " [x] Received '"
            + objectMapper.writeValueAsString(in) + "'");
    doWork(in, receiver);
    watch.stop();
    System.out.println("instance " + receiver + " [x] Done in "
            + watch.getTotalTimeSeconds() + "s");
  }

  private void doWork(AuditLog in, String receiver) throws InterruptedException, JsonProcessingException {
//    Thread.sleep(100);
    in.setRegistered(new Date());

    String auditLogString = objectMapper.writeValueAsString(in) + System.lineSeparator();

    System.out.println(auditLogString);
    try {
      Files.write(FileSystems.getDefault().getPath(".", receiver.toLowerCase())
              , (auditLogString).getBytes(UTF_8)
              , StandardOpenOption.CREATE, StandardOpenOption.APPEND);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
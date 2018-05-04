package com.example.rabbitmq.tutorials.fileio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuditLog {
  private ApplicationType applicationType;
  private String serverName;
  private String threadName;
  private int userId;
  private EventType eventType;
  private String action;
  private Date happened;    // application server date
  private Date registered;  // audit server date

  @Builder
  public AuditLog(ApplicationType applicationType, String serverName, String threadName, int userId, EventType eventType, String action, Date happened, Date registered) {
    this.applicationType = applicationType;
    this.serverName = serverName;
    this.threadName = threadName;
    this.userId = userId;
    this.eventType = eventType;
    this.action = action;
    this.happened = happened;
    this.registered = registered;
  }
}

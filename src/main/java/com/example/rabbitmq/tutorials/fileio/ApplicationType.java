package com.example.rabbitmq.tutorials.fileio;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ApplicationType {
  WEB
  , ADM
  , NOTIFICATION
  , POINT
  , USER
  , PAY;

  @JsonValue
  public String getValue() {
    return this.name();
  }

  @JsonCreator
  public static ApplicationType getType(String name) {
    for(ApplicationType type: ApplicationType.values()) {
      if(type.getValue().equals(name)) {
        return type;
      }
    }
    throw new RuntimeException("Not available applicationType value : " + name);
  }
}

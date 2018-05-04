package com.example.rabbitmq.tutorials.fileio;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EventType {
  ACTIVITY,
  DATA_ACCESS;

  @JsonValue
  public String getValue() {
    return this.name();
  }

  @JsonCreator
  public static EventType getType(String name) {
    for(EventType type: EventType.values()) {
      if(type.getValue().equals(name)) {
        return type;
      }
    }
    throw new RuntimeException("Not available eventType value : " + name);
  }
}

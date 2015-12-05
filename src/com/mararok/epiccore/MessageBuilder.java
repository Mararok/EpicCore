/**
 * EpicCore
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.mararok.epiccore;

import java.util.ArrayList;
import java.util.List;

public class MessageBuilder {
  private List<String> messages;

  public MessageBuilder() {
    messages = new ArrayList<String>();
  }

  public static MessageBuilder message() {
    return new MessageBuilder();
  }

  public MessageBuilder line(String message) {
    messages.add(message);
    return this;
  }

  public String[] toArray() {
    return messages.toArray(new String[messages.size()]);
  }
}

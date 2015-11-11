/**
 * EpicWar
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.gmail.mararok.epiccore.command;

/**
 * Contains info about command
 */
public class CommandMetadata {
  public String name;
  public String displayName = "";
  public String description = "";
  public String usage = "";
  public String[] permissions;
  public int requiredArgumentAmount = 0;

  public CommandMetadata() {
  }

  public CommandMetadata(CommandMetadata metadata) {
    name = metadata.name;
    displayName = metadata.displayName;
    description = metadata.description;
    usage = metadata.usage;
    permissions = metadata.permissions;
    requiredArgumentAmount = metadata.requiredArgumentAmount;
  }

  public static Builder command(String name) {
    return new Builder(name);
  }

  public static class Builder {
    private CommandMetadata metadata;

    public Builder(String commandName) {
      metadata = new CommandMetadata();
      metadata.name = commandName;
    }

    public Builder displayName(String displayName) {
      metadata.displayName = displayName;
      return this;
    }

    public Builder description(String description) {
      metadata.description = description;
      return this;
    }

    public Builder usage(String usage) {
      metadata.usage = usage;
      return this;
    }

    public Builder permissions(String[] permissions) {
      metadata.permissions = permissions;
      return this;
    }

    public Builder requiredArgumentAmount(int requiredArgumentAmount) {
      metadata.requiredArgumentAmount = requiredArgumentAmount;
      return this;
    }

    public CommandMetadata create() {
      return new CommandMetadata(metadata);
    }

  }
}

/**
 * EpicCore
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.gmail.mararok.epiccore.command;

public class CommandMetadataBuilder {
  private CommandMetadata metadata;

  public CommandMetadataBuilder(String commandName) {
    metadata = new CommandMetadata();
    metadata.name = commandName;
  }

  public static CommandMetadataBuilder command(String name) {
    return new CommandMetadataBuilder(name);
  }

  public CommandMetadataBuilder displayName(String displayName) {
    metadata.displayName = displayName;
    return this;
  }

  public CommandMetadataBuilder description(String description) {
    metadata.description = description;
    return this;
  }

  public CommandMetadataBuilder usage(String usage) {
    metadata.usage = usage;
    return this;
  }

  public CommandMetadataBuilder permissions(String[] permissions) {
    metadata.permissions = permissions;
    return this;
  }

  public CommandMetadataBuilder requiredArgumentAmount(int requiredArgumentAmount) {
    metadata.requiredArgumentAmount = requiredArgumentAmount;
    return this;
  }

  public CommandMetadata build() {
    return new CommandMetadata(metadata);
  }

}

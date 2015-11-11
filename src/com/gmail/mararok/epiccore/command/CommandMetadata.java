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
}

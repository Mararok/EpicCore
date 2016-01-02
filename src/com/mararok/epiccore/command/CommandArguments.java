/**
 * EpicWar
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.mararok.epiccore.command;

import java.util.Arrays;

import org.bukkit.plugin.java.JavaPlugin;

import com.mararok.epiccore.misc.StringUtils;

/**
 * Simple command arguments wrapper.
 * Supports subcommands in args, type checking methods and required args amount check
 */

public class CommandArguments<P extends JavaPlugin> {
  private String[] rawArguments;
  private int amount;

  public CommandArguments(String[] rawArguments) {
    this.rawArguments = rawArguments;
    this.amount = (rawArguments != null) ? rawArguments.length : 0;
  }

  public boolean isExists(int index) {
    return getAmount() > index;
  }

  public boolean hasAny() {
    return amount != 0;
  }

  public String get(int index) {
    return rawArguments[index];
  }

  public boolean isNumber(int index) {
    return isExists(index) && StringUtils.isNumber(get(index));
  }

  public double asDouble(int index) throws NumberFormatException {
    return Double.parseDouble(get(index));
  }

  public float asFloat(int index) throws NumberFormatException {
    return Float.parseFloat(get(index));
  }

  public long asLong(int index) throws NumberFormatException {
    return Long.parseLong(get(index));
  }

  public int asInt(int index) throws NumberFormatException {
    return Integer.parseInt(get(index));
  }

  public short asShort(int index) throws NumberFormatException {
    return Short.parseShort(get(index));
  }

  public byte asByte(int index) throws NumberFormatException {
    return Byte.parseByte(get(index));
  }

  public boolean isBoolean(int index) {
    return isExists(index) && StringUtils.isBoolean(get(index));
  }

  public boolean asBoolean(int index) {
    return Boolean.parseBoolean(get(index));
  }

  public char asChar(int index) {
    return get(index).charAt(0);
  }

  /**
   * @see #join(int, int, String)
   */
  public String join(int startIndex, String separator) throws IndexOutOfBoundsException {
    return join(startIndex, getAmount() - startIndex, separator);
  }

  /**
   * Joins many arguments in to one
   * 
   * @throws IndexOutOfBoundsException when startIndex is < 0 or >= argmuments amount
   */
  public String join(int startIndex, int length, String separator) throws IndexOutOfBoundsException {
    int lastIndex = startIndex + (length - 1);
    if (startIndex < 0 || lastIndex >= getAmount()) {
      throw new IndexOutOfBoundsException();
    }

    String argument = "";
    for (int i = startIndex; i < lastIndex; ++i) {
      argument += get(i) + separator;
    }
    argument += get(lastIndex);
    return argument;
  }

  public boolean hasRequired(PluginCommand<P> command) {
    return (getAmount() >= command.getRequiredArgumentAmount());
  }

  public CommandArguments<P> getArgumentsForChild(ChildPluginCommand<P> command) {
    if (getAmount() >= command.getDepthLevel()) {
      return new CommandArguments<P>(Arrays.copyOfRange(rawArguments, command.getDepthLevel(), getAmount()));
    } else {
      return new CommandArguments<P>(null);
    }
  }

  public int getAmount() {
    return amount;
  }

}

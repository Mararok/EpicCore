/**
 * EpicWar
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.mararok.epiccore.command;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Container for subcommands
 */
public abstract class ParentPluginCommand<P extends JavaPlugin> extends ChildPluginCommand<P> {
  private Collection<ChildPluginCommand<P>> children;

  public ParentPluginCommand(P plugin) {
    super(plugin);
    children = new ArrayList<ChildPluginCommand<P>>();
  }

  protected void addCommand(ChildPluginCommand<P> command) {
    command.setParent(this, children.size());
    children.add(command);
  }

  /**
   * Sends subcommands descriptions list, whenm executed without arguments
   */
  @Override
  protected boolean onCommand(CommandSender sender, CommandArguments<P> arguments) throws Exception {
    if (arguments.isExists(0)) {
      if (!execSubCommand(sender, arguments)) {
        sendUsage(sender);
      }
    } else {
      sendDescription(sender);
    }

    return true;
  }

  private boolean execSubCommand(CommandSender sender, CommandArguments<P> arguments) throws Exception {
    String subCommandName = arguments.get(0).toLowerCase();
    ChildPluginCommand<P> childCommand = getCommandByName(subCommandName);
    if (childCommand != null) {
      childCommand.onCommand(sender, arguments.getArgumentsForChild(childCommand));
      return true;
    } else {
      return false;
    }
  }

  protected ChildPluginCommand<P> getCommandByName(String name) {
    for (ChildPluginCommand<P> child : children) {
      if (child.getName().equals(name)) {
        return child;
      }
    }

    return null;
  }

  @Override
  protected void sendDescription(CommandSender sender) {
    String[] subCommandsInfo = new String[children.size() + 1];
    subCommandsInfo[0] = ChatColor.YELLOW + "" + ChatColor.BOLD + getDescription();
    int i = 1;
    for (PluginCommand<P> sub : children) {
      subCommandsInfo[i] = " " + sub.getName() + ChatColor.RESET + " - " + sub.getDescription();
      ++i;
    }
    sender.sendMessage(subCommandsInfo);
  }

}

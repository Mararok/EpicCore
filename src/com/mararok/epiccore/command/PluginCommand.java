/**
 * EpicWar
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.mararok.epiccore.command;

import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Base class for plugin command
 */
public abstract class PluginCommand<P extends JavaPlugin> implements CommandExecutor {
  private CommandMetadata metadata;
  private P plugin;

  public PluginCommand(P plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    CommandArguments<P> arguments = new CommandArguments<P>(args);
    try {
      if (!onCommand(sender, arguments)) {
        sendUsage(sender);
      }

      return true;
    } catch (Exception e) {
      getPlugin().getLogger().log(Level.SEVERE, "exception in command: " + getName() + " with arguments: " + arguments + " and sender: " + sender.getName(), e);
      sender.sendMessage("INTERNAL ERROR");
      return false;
    }
  }

  /**
   * Default onCommand handler
   */
  protected boolean onCommand(CommandSender sender, CommandArguments<P> arguments) throws Exception {
    if (arguments.hasRequired(this)) {
      if (sender instanceof Player && !onCommandAsPlayer((Player) sender, arguments)) {
        sendUsage(sender);
      }
    } else {
      sendUsage(sender);
    }

    return true;
  }

  /**
   * Executed when command sender is Player instance
   */
  protected boolean onCommandAsPlayer(Player sender, CommandArguments<P> arguments) throws Exception {
    return true;
  }

  protected void sendDescription(CommandSender sender) {
    sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + getName() + ChatColor.RESET + " - " + getDescription());
  }

  protected void sendUsage(CommandSender sender) {
    sender.sendMessage(getUsage());
  }

  public String getName() {
    return metadata.name;
  }

  public String getDescription() {
    return metadata.description;
  }

  public String getUsage() {
    return metadata.usage;
  }

  public String getPermission() {
    return metadata.permission;
  }

  /**
   * Checks is command sender has specified permission for use command
   * If command don't define any permission always return true
   */
  public boolean hasPermission(CommandSender sender) {
    return (getPermission() != null) ? sender.hasPermission(getPermission()) : true;
  }

  public int getRequiredArgumentAmount() {
    return metadata.requiredArguments;
  }

  protected void setMetadata(CommandMetadata metadata) {
    this.metadata = metadata;
  }

  protected void setMetadata(CommandMetadata.Builder metadata) {
    this.metadata = metadata.create();
  }

  public P getPlugin() {
    return plugin;
  }
}

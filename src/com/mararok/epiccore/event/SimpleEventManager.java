/**
 * EpicCore
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.mararok.epiccore.event;

import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleEventManager<P extends JavaPlugin, E extends Event> implements EventManager<P, E> {
  private P plugin;

  public SimpleEventManager(P plugin) {
    this.plugin = plugin;
  }

  @Override
  public void dispatchEvent(E event) {
    plugin.getServer().getPluginManager().callEvent(event);
  }

  @Override
  public P getPlugin() {
    return plugin;
  }
}

/**
 * EpicCore
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.mararok.epiccore.event;

import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

public class LoggableEventManager<P extends JavaPlugin, E extends Event> implements EventManager<P, E> {
  private EventManager<P, E> eventManager;

  public LoggableEventManager(EventManager<P, E> eventManager) {
    this.eventManager = eventManager;
  }

  @Override
  public void dispatchEvent(E event) {
    getPlugin().getLogger().info("[EVENT] " + event.getEventName());
    this.eventManager.dispatchEvent(event);
  }

  @Override
  public P getPlugin() {
    return eventManager.getPlugin();
  }

}

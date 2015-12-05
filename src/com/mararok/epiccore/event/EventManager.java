/**
 * EpicWar
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.mararok.epiccore.event;

import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

public interface EventManager<P extends JavaPlugin, E extends Event> {
  public void dispatchEvent(E event);

  public P getPlugin();
}

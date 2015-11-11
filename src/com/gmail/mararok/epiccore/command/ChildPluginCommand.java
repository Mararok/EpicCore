package com.gmail.mararok.epiccore.command;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Class for subcommands support
 */
public abstract class ChildPluginCommand<P extends JavaPlugin> extends PluginCommand<P> {
  private ParentPluginCommand<P> parent;
  private int index = -1;

  public ChildPluginCommand(P plugin) {
    super(plugin);
  }

  public ParentPluginCommand<P> getParent() {
    return parent;
  }

  public boolean hasParent() {
    return parent != null;
  }

  public int getIndex() {
    return index;
  }

  /**
   * @return how depth in command string that is subcommand
   */
  public int getDepthLevel() {
    return hasParent() ? parent.getDepthLevel() + 1 : 0;
  }

  public void setParent(ParentPluginCommand<P> newParent, int newIndex) {
    parent = newParent;
    index = newIndex;
  }
}

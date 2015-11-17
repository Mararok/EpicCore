/**
 * EpicWar
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.mararok.epiccore.entity;

/**
 * Base class for all entities with identity
 */
public abstract class Entity {
  private int id;

  public Entity(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

}

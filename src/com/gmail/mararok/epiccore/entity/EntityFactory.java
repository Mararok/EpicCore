/**
 * EpicWar
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.gmail.mararok.epiccore.entity;

/**
 * Base interface for all entity factories
 * 
 * @param <E>
 *          Entity class type
 * @param <ED>
 *          Entity data class type
 */
public interface EntityFactory<E extends Entity, ED> {
  public E create(ED entityData) throws Exception;
}

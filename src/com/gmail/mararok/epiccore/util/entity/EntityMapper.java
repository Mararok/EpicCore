/**
 * EpicWar
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.gmail.mararok.epiccore.util.entity;

import java.util.Collection;

public interface EntityMapper<E extends ObservedEntity, ED> {
  public E findById(int id) throws Exception;

  public Collection<E> findAll() throws Exception;

  public E insert(ED entityData) throws Exception;

  public void update(E entity) throws Exception;

  public void delete(E entity) throws Exception;
}

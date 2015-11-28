/**
 * EpicWar
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.mararok.epiccore.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import com.mararok.epiccore.database.DMQL;
import com.mararok.epiccore.entity.ObservedEntity.PropertyEntry;

/**
 * SQL databases entity mapper
 */
public abstract class EntityDatabaseMapper<E extends ObservedEntity, ED> implements EntityMapper<E, ED> {
  private DMQL queries;
  private String tableName;

  private EntityFactory<E, ED> factory;

  public EntityDatabaseMapper(DMQL queries, String tableName, EntityFactory<E, ED> factory) {
    this.queries = queries;
    this.tableName = tableName;

    this.factory = factory;
  }

  @Override
  public E findById(int id) throws Exception {
    return findOne("id=" + id);
  }

  public E findOne(String where) throws Exception {
    PreparedStatement query = queries.select.getCompiledQuery();
    query.setString(1, "*");
    query.setString(2, getTableName());
    query.setString(3, where);
    ResultSet resultSet = query.executeQuery();
    return resultSet.first() ? create(resultSet) : null;
  }

  @Override
  public Collection<E> findAll() throws Exception {
    return find("1");
  }

  public Collection<E> find(String where) throws Exception {
    PreparedStatement query = queries.select.getCompiledQuery();
    query.setString(1, "*");
    query.setString(2, getTableName());
    query.setString(3, where);
    ResultSet resultSet = query.executeQuery();

    Collection<E> collection = new LinkedList<E>();
    while (resultSet.next()) {
      collection.add(create(resultSet));
    }

    return collection;
  }

  /**
   * @param columns
   *          comma separated names of table columns
   * @param values
   *          comma separated values list
   */
  protected int insert(String columns, String values) throws Exception {
    PreparedStatement query = queries.insert.getCompiledQuery();
    query.setString(1, getTableName());
    query.setString(2, columns);
    query.setString(3, values);
    query.executeUpdate();
    ResultSet result = query.getGeneratedKeys();
    if (result.next()) {
      return result.getInt(1);
    }

    return 0;
  }

  @Override
  public void update(E entity) throws Exception {
    if (entity.hasAnyChangedProperties()) {
      PropertyEntry[] properties = entity.getChangedProperties();
      String setString = "";
      for (PropertyEntry property : properties) {
        setString += property.name + "=" + property.value;
      }

      PreparedStatement query = queries.update.getCompiledQuery();
      query.setString(1, getTableName());
      query.setString(2, setString);
      query.setString(3, "id=" + entity.getId());
      query.executeUpdate();
    }
  }

  @Override
  public void delete(E entity) throws Exception {
    PreparedStatement query = queries.delete.getCompiledQuery();
    query.setString(1, getTableName());
    query.setString(2, "id=" + entity.getId());
    query.executeUpdate();
  }

  protected E create(ResultSet resultSet) throws Exception {
    ED entityData = createData(resultSet);
    return factory.create(entityData);
  }

  protected abstract ED createData(ResultSet resultSet) throws SQLException;

  protected DMQL getQueries() {
    return queries;
  }

  protected String getTableName() {
    return tableName;
  }

  protected EntityFactory<E, ED> getFactory() {
    return factory;
  }

  protected static String columns(String... columns) {
    return String.join(",", columns);
  }

  protected static String values(String... values) {
    return String.join(",", values);
  }
}

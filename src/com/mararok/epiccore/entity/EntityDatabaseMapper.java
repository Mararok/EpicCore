/**
 * EpicWar
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.mararok.epiccore.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import com.mararok.epiccore.database.DMQL;
import com.mararok.epiccore.entity.ObservedEntity.PropertyEntry;
import com.mararok.epiccore.misc.StringUtils;

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
    ResultSet resultSet = queries.select("*", getTableName(), where);
    return resultSet.first() ? create(resultSet) : null;
  }

  @Override
  public Collection<E> findAll() throws Exception {
    return find(null);
  }

  public Collection<E> find(String where) throws Exception {
    ResultSet resultSet = queries.select("*", getTableName(), where);
    Collection<E> collection = new LinkedList<E>();
    while (resultSet.next()) {
      collection.add(create(resultSet));
    }

    return collection;

  }

  /**
   * @param columns Comma separated names of table columns
   * @param values Comma separated values list
   */
  protected int insert(String columns, String values) throws Exception {
    return queries.insert(getTableName(), columns, values);
  }

  @Override
  public void update(E entity) throws Exception {
    if (entity.hasAnyChangedProperties()) {
      PropertyEntry[] properties = entity.getChangedProperties();
      String setString = "";
      for (PropertyEntry property : properties) {
        setString += property.name + "=" + property.value;
      }

      queries.update(getTableName(), setString, "id=" + entity.getId());
    }
  }

  @Override
  public void delete(E entity) throws Exception {
    queries.delete(getTableName(), "id=" + entity.getId());
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
    return StringUtils.join(columns, ",");
  }

  protected static String values(Object... values) {
    Object[] convertedValues = new Object[values.length];
    int i = 0;
    for (Object value : values) {
      convertedValues[i++] = (value instanceof String) ? encloseValueInQuotes((String) value) : value;
    }
    return "(" + StringUtils.join(convertedValues, ",") + ")";
  }

  protected static String encloseValueInQuotes(String value) {
    return "'" + value + "'";
  }
}

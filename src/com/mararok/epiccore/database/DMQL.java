/**
 * EpicWar
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.mararok.epiccore.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Helper for simple sql queries(insert,update,delete,select)
 */
public class DMQL {
  private DatabaseConnection connection;

  public DMQL(DatabaseConnection connection) {
    this.connection = connection;
  }

  public int insert(String tableName, String columns, String values) throws SQLException {
    String sql = "INSERT " + tableName + "(" + columns + ") VALUES(" + values + ")";
    try (Statement query = connection.query()) {
      query.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
      ResultSet result = query.getGeneratedKeys();
      return (result.next()) ? result.getInt(1) : 0;
    } catch (SQLException e) {
      throw new SQLException(sql, e);
    }
  }

  public ResultSet select(String columns, String from, String where) throws SQLException {
    String sql = "SELECT " + columns + " FROM " + from + ((where != null) ? " WHERE " + where : "");
    try {
      return connection.query().executeQuery(sql);
    } catch (SQLException e) {
      throw new SQLException(sql, e);
    }

  }

  public int update(String tableName, String set, String where) throws SQLException {
    String sql = "UPDATE " + tableName + " SET " + set + " WHERE " + where;
    try {
      return connection.exec(sql);
    } catch (SQLException e) {
      throw new SQLException(sql, e);
    }
  }

  public int delete(String from, String where) throws SQLException {
    String sql = "DELETE " + from + " WHERE " + where;
    try {
      return connection.exec(sql);
    } catch (SQLException e) {
      throw new SQLException(sql, e);
    }
  }

}

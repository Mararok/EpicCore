/**
 * EpicWar
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.mararok.epiccore.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionImpl implements DatabaseConnection {
  private Connection connection;
  private QueriesCache statementCache;

  private DatabaseConnectionConfig config;

  public DatabaseConnectionImpl(Connection connection, DatabaseConnectionConfig config) {
    this.connection = connection;
    this.statementCache = new QueriesCache(32, this);
    this.config = config;
  }

  @Override
  public int exec(String sql) throws SQLException {
    Statement st = query();
    int rowCount = st.executeUpdate(sql);
    st.close();
    return rowCount;
  }

  @Override
  public Statement query() throws SQLException {
    return connection.createStatement();
  }

  @Override
  public PreparedStatement prepareQuery(String sql) throws SQLException {
    return connection.prepareStatement(sql);
  }

  @Override
  public CachedQuery prepareCachedQuery(String sql) throws SQLException {
    return statementCache.add(sql);
  }

  @Override
  public CachedQuery getCachedQuery(int index) throws SQLException {
    return statementCache.get(index);
  }

  @Override
  public void clearCache() throws SQLException {
    statementCache.clear();
  }

  @Override
  public void beginTransaction() throws SQLException {
    connection.setAutoCommit(false);
  }

  @Override
  public void endTransaction() throws SQLException {
    connection.setAutoCommit(true);
  }

  @Override
  public void commit() throws SQLException {
    connection.commit();
  }

  @Override
  public void rollback() throws SQLException {
    connection.rollback();
  }

  @Override
  public DatabaseConnectionConfig getConfig() {
    return config;
  }

}

/**
 * EpicWar
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.mararok.epiccore.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Intarface for connection to sql database
 */
public interface DatabaseConnection {

  public int exec(String sql) throws SQLException;

  public Statement query() throws SQLException;

  public PreparedStatement prepareQuery(String sql) throws SQLException;

  public CachedQuery prepareCachedQuery(String sql) throws SQLException;

  public CachedQuery getCachedQuery(int index) throws SQLException;

  public void clearCache() throws SQLException;

  public void beginTransaction() throws SQLException;

  public void endTransaction() throws SQLException;

  public void commit() throws SQLException;

  public void rollback() throws SQLException;

  DatabaseConnectionConfig getConfig();
}

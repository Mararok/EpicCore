/**
 * EpicWar
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.mararok.epiccore.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionFactory {
  private static final String SQLite_DRIVER = "org.sqlite.JDBC";
  private static final String MySQL_DRIVER = "com.mysql.jdbc.Driver";

  public static DatabaseConnection newConnection(DatabaseConnectionConfig config) throws SQLException {
    return new DatabaseConnectionImpl(createJDBCConnection(config), config);
  }

  private static Connection createJDBCConnection(DatabaseConnectionConfig config) throws SQLException {
    String connectionURL;
    String engineDriverName;

    if (config.engine.equalsIgnoreCase("SQLite")) {
      connectionURL = createSQLiteConnectionURL(config);
      engineDriverName = SQLite_DRIVER;
    } else if (config.engine.equalsIgnoreCase("MySql")) {
      connectionURL = createMySQLConnectionURL(config);
      engineDriverName = MySQL_DRIVER;
    } else {
      throw new SQLException("Not supported database engine: " + config.engine);
    }

    try {
      Class.forName(engineDriverName);
      return DriverManager.getConnection(connectionURL);
    } catch (ClassNotFoundException e) {
      throw new SQLException("Driver " + engineDriverName + " not found");
    } catch (Exception e) {
      throw new SQLException("Can't connect to database, url: " + connectionURL, e);
    }

  }

  private static String createSQLiteConnectionURL(DatabaseConnectionConfig config) {
    return String.format("jdbc:sqlite:%s.db", config.name);
  }

  private static String createMySQLConnectionURL(DatabaseConnectionConfig config) {
    return String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s", config.host, config.port, config.name, config.user, config.password);
  }
}

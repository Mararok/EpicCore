/**
 * EpicWar
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.mararok.epiccore.database;

import java.sql.SQLException;

/**
 * Helper for simple sql queries(insert,update,delete,select)
 */
public class DMQL {
  public CachedQuery insert;
  public CachedQuery update;
  public CachedQuery delete;
  public CachedQuery select;

  public DatabaseConnection connection;

  public DMQL(DatabaseConnection connection) {
    this.connection = connection;
  }

  public void initQueries() throws SQLException {
    insert = connection.prepareCachedQuery("INSERT INTO ?(?) VALUES(?);");
    insert.compile();
    update = connection.prepareCachedQuery("UPDATE ? SET ? WHERE ?;");
    update.compile();
    delete = connection.prepareCachedQuery("DELETE ? WHERE ?;");
    delete.compile();
    select = connection.prepareCachedQuery("SELECT ? FROM ? WHERE ?;");
    select.compile();
  }
}

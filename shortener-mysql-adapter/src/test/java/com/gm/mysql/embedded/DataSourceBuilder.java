package com.gm.mysql.embedded;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;

public class DataSourceBuilder
{
  private String user;
  private String serverName;
  private int port;
  private String dbName;

  public DataSourceBuilder()
  {
    this.user = "root";
    serverName = "localhost";
    port = 13306;
    dbName = "test";

  }
  public DataSourceBuilder withPort(int port){
    this.port = port;
    return this;
  }

  public DataSourceBuilder withDbName(String DbName){
    dbName = DbName;
    return this;
  }


  public DataSource build(){
    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setUser(this.user);
    dataSource.setServerName(serverName);
    dataSource.setPort(port);

    String url = "jdbc:mysql://"+ serverName +":"+port+"/" + dbName + "?" + "createDatabaseIfNotExist=true";
    url += "&zeroDateTime-Behavior=convertToNull";
    url += "&characterEncoding=UTF-8";
    url += "&characterSetResults=UTF-8";
    url += "&allowMultiQueries=true";
    dataSource.setUrl(url);
    return dataSource;
  }
}

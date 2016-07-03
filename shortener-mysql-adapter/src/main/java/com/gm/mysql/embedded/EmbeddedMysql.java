package com.gm.mysql.embedded;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.mysql.management.MysqldResource;
import com.mysql.management.MysqldResourceI;

import javax.sql.DataSource;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EmbeddedMysql
{
  private final int port;
  private final String dbName;
  private MysqldResource mysqldResource;

  public EmbeddedMysql(String dbName, int port)
  {
    this.port = port;
    this.dbName = dbName;
  }

  public void start()
  {
    mysqldResource = new MysqldResource(new File("/tmp", dbName));
    Map<String, String> databaseOptions = new HashMap<String, String>();
    databaseOptions.put(MysqldResourceI.PORT, Integer.toString(port));
    mysqldResource.start("embedded-mysqld-thread-" + System.currentTimeMillis(), databaseOptions);
    if(!mysqldResource.isRunning())
    {
      throw new RuntimeException("MySQL did not start.");
    }
  }

//  @TODO change DB initialization
  public void initDB(String sqlPath)
  {
    MysqlDataSource  dataSource = new MysqlDataSource();
    dataSource.setUser("root");
    dataSource.setServerName("localhost");
    dataSource.setPort(port);
    String url = "jdbc:mysql://localhost:" + port + "/" + dbName + "?" + "createDatabaseIfNotExist=true";
    url += "&zeroDateTime-Behavior=convertToNull";
    url += "&characterEncoding=UTF-8";
    url += "&characterSetResults=UTF-8";
    url += "&allowMultiQueries=true";
    dataSource.setUrl(url);

    String populateSQL = new EmbeddedMysqlFileSystem().getInitSql(sqlPath);

    execute(populateSQL, dataSource);
  }

  public void shutDown()
  {
    if(mysqldResource != null)
    {
      mysqldResource.shutdown();
      if(!mysqldResource.isRunning())
      {
        new EmbeddedMysqlFileSystem().cleanFileSystem(mysqldResource);
      }
    }
  }

  public List<Map<String, String>> query(String sql, DataSource dataSource)
  {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    List<Map<String, String>> result = new ArrayList<>();
    try
    {
      connection = dataSource.getConnection();

      statement = connection.prepareStatement(sql);
      resultSet = statement.executeQuery();
      ResultSetMetaData metaData = resultSet.getMetaData();
      while(resultSet.next())
      {
        Map<String, String> row = new HashMap<>();
        for(int i = 1; i <= metaData.getColumnCount(); i++)
        {
          row.put(metaData.getColumnName(i), resultSet.getString(i));
        }
        result.add(row);
      }
      return result;
    }
    catch(SQLException e)
    {
      throw new RuntimeException(e);
    }
    finally
    {
      closeResultSet(resultSet);
      closeStatement(statement);
      closeConnection(connection);
    }
  }

  public void execute(String populateSQL, DataSource dataSource)
  {
    Connection connection = null;
    PreparedStatement statement = null;
    try
    {
      connection = dataSource.getConnection();

      statement = connection.prepareStatement(populateSQL);
      statement.execute();
    }
    catch(SQLException e)
    {
      e.printStackTrace();
    }
    finally
    {
      closeStatement(statement);
      closeConnection(connection);
    }
  }

  private void closeResultSet(ResultSet resultSet)
  {
    if(resultSet != null)
      try
      {
        resultSet.close();
      }
      catch(SQLException e)
      {
        throw new RuntimeException(e);
      }
  }

  private void closeConnection(Connection connection)
  {
    if(connection != null)
      try
      {
        connection.close();
      }
      catch(SQLException e)
      {
        throw new RuntimeException(e);
      }
  }

  private void closeStatement(PreparedStatement statement)
  {
    if(statement != null)
    {
      try
      {
        statement.close();
      }
      catch(SQLException e)
      {
        throw new RuntimeException(e);
      }
    }
  }


}

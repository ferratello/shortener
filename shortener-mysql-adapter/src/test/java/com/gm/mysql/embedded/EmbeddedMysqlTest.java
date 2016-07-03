package com.gm.mysql.embedded;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;

public class EmbeddedMysqlTest
{

  private EmbeddedMysql underTest = new EmbeddedMysql("test", 12345);

  @Before
  public void setUp()
  {
    underTest.start();
  }

  @After
  public void tearDown()
  {
    underTest.shutDown();
  }

  @Test
  public void serverStart() throws SQLException
  {
    DataSource dataSource = new DataSourceBuilder().withPort(12345).withDbName("test").build();
    assertThat(underTest.query("SELECT 1;", dataSource), containsInAnyOrder(new HashMap<String, String>()
    {{
      put("1", "1");
    }}));
    dataSource.getConnection().close();
  }

  @Test
  public void initDatabase()
  {
    underTest.initDB("init-db.sql");

    DataSource dataSource = new DataSourceBuilder().withPort(12345).withDbName("test").build();

    assertThat(underTest.query("SHOW DATABASES;", dataSource), hasItem(new HashMap<String, String>()
    {{
      put("SCHEMA_NAME", "test");
    }}));
    assertThat(underTest.query("SELECT * FROM SIMPLE_TABLE;", dataSource), hasItem(new HashMap<String, String>()
    {{
      put("FIELD1", "Lorem Ipsum dolor sit amen");
    }}));
  }
}
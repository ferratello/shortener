package com.gm.shortener.dao;

import com.gm.mysql.embedded.DataSourceBuilder;
import com.gm.mysql.embedded.EmbeddedMysql;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MysqlURLDaoTest
{
  private static final String DB_NAME = "shortener";
  private static final int PORT = 13306;
  private static EmbeddedMysql embeddedMysql = new EmbeddedMysql(DB_NAME, PORT);

  @BeforeClass
  public static void setUpClass()
  {
    embeddedMysql.start();
    embeddedMysql.initDB("init-shoertener-db.sql");
  }

  @AfterClass
  public static void tearDownClass() throws IOException
  {
    embeddedMysql.shutDown();
  }

  @Test
  public void insertAndGet()
  {
    DataSource dataSource = new DataSourceBuilder().withDbName(DB_NAME).withPort(PORT).build();

    MysqlURLDao underTest = new MysqlURLDao(dataSource);
    UrlRecord input = new UrlRecord("short", "original");
    underTest.insert(input);
    assertThat(underTest.getFromOriginalUrl("original"), is(equalTo(input)));
    assertThat(underTest.getFromShortUrl("short"), is(equalTo(input)));
  }

}
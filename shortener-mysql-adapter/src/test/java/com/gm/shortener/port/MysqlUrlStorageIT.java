package com.gm.shortener.port;

import com.gm.mysql.embedded.DataSourceBuilder;
import com.gm.mysql.embedded.EmbeddedMysql;
import com.gm.shortener.dao.MysqlURLDao;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MysqlUrlStorageIT extends URLRepositoryContractTest
{
  private static final String DB_NAME = "shortener";
  private static final int PORT = 13306;
  private static EmbeddedMysql embeddedMysql = new EmbeddedMysql(DB_NAME, PORT);
  private DataSource dataSource;


  @BeforeClass
  public static void setUpClass()
  {
    embeddedMysql.start();
  }

  @AfterClass
  public static void tearDownClass() throws IOException
  {
    embeddedMysql.shutDown();
  }


  @Override
  protected URLStorage createUrlStorage()
  {
    return new MysqlUrlStorage(new MysqlURLDao(dataSource));
  }

  @Override
  protected String getOriginalUrlFromPersistence()
  {
    List<Map<String, String>> result = embeddedMysql.query("SELECT ORIGINAL_URL FROM URLS WHERE SHORT_URL= \"" + TEST_SHORT_URL + "\"",
                                                          dataSource);
    return result.get(0).get("ORIGINAL_URL");
  }

  @Override
  protected void innerSetup()
  {
    embeddedMysql.initDB("init-shoertener-db.sql");
    dataSource = new DataSourceBuilder().withDbName(DB_NAME).withPort(PORT).build();
  }


}

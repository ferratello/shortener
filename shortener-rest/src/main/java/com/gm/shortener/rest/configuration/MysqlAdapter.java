package com.gm.shortener.rest.configuration;

import com.gm.mysql.embedded.EmbeddedMysql;
import com.gm.shortener.dao.MysqlURLDao;
import com.gm.shortener.port.MysqlUrlStorage;
import com.gm.shortener.port.URLStorage;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MysqlAdapter
{
  private static final Logger logger = LoggerFactory.getLogger(MysqlAdapter.class);
  private static final String DB_NAME = "shortener";
  private static final int PORT = 13306;

  @Autowired
  private EmbeddedMysql embeddedMysql;

  public MysqlAdapter()
  {
    logger.info(this.getClass().getName() + " configured");
  }

  @Bean(destroyMethod = "shutDown")
  public EmbeddedMysql embeddedMysql()
  {
    return new EmbeddedMysql(DB_NAME, PORT);
  }

  @Bean
  public URLStorage mysqlUrlStorage()
  {
    return new MysqlUrlStorage(new MysqlURLDao(dataSource()));
  }

  private DataSource dataSource()
  {
    embeddedMysql.start();
    embeddedMysql.initDB("init-prod-db.sql");

    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setUser("root");
    dataSource.setServerName("localhost");
    dataSource.setPort(PORT);
    String url = "jdbc:mysql://localhost:" + PORT + "/" + DB_NAME;
    url += "?zeroDateTime-Behavior=convertToNull";
    url += "&characterEncoding=UTF-8";
    url += "&characterSetResults=UTF-8";
    url += "&allowMultiQueries=true";
    dataSource.setUrl(url);

    return dataSource;
  }

}

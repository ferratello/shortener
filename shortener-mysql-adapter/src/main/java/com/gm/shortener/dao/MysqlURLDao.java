package com.gm.shortener.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;

public class MysqlURLDao extends JdbcDaoSupport
{
  private static final String SQL_FIND_BY_ORIGINAL = "SELECT * FROM URLS WHERE ORIGINAL_URL = ?";
  private static final String SQL_FIND_BY_SHORT = "SELECT * FROM URLS WHERE SHORT_URL = ?";
  private static final String SQL_INSERT = "INSERT INTO URLS (ORIGINAL_URL, SHORT_URL) VALUES (?,?)";

  public MysqlURLDao(DataSource dataSource)
  {
    super.setDataSource(dataSource);
  }


  public UrlRecord getFromOriginalUrl(String originalUrl)
  {
    return (UrlRecord) getJdbcTemplate().queryForObject(SQL_FIND_BY_ORIGINAL,
                                                  new String[]{originalUrl},
                                                  new BeanPropertyRowMapper(UrlRecord.class));

  }

  public UrlRecord getFromShortUrl(String shortUrl)
  {
    return (UrlRecord) getJdbcTemplate().queryForObject(SQL_FIND_BY_SHORT,
                                                  new String[]{shortUrl},
                                                  new BeanPropertyRowMapper(UrlRecord.class));
  }

  public void insert(UrlRecord input)
  {
    getJdbcTemplate().update(SQL_INSERT,
                             new Object[]{input.getOriginalUrl(), input.getShortUrl()});
  }
}

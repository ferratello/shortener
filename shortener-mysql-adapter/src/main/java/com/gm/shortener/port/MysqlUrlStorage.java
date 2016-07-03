package com.gm.shortener.port;

import com.gm.shortener.dao.MysqlURLDao;
import com.gm.shortener.dao.UrlRecord;
import com.gm.shortener.storage.MissingUrlException;
import com.gm.shortener.storage.Url;
import org.springframework.dao.EmptyResultDataAccessException;

public class MysqlUrlStorage implements URLStorage
{
  private final MysqlURLDao dao;

  public MysqlUrlStorage(MysqlURLDao mysqlURLDao)
  {
    this.dao = mysqlURLDao;
  }

  @Override
  public Url fromOriginalUrl(String originalUrl)
  {
    try
    {
    return newUrl(dao.getFromOriginalUrl(originalUrl));
    }
    catch(EmptyResultDataAccessException ex)
    {
      throw new MissingUrlException(originalUrl);
    }
  }

  @Override
  public Url fromShortUrl(String shortUrl)
  {
    try{
      return newUrl(dao.getFromShortUrl(shortUrl));
    }
    catch(EmptyResultDataAccessException ex)
    {
      throw new MissingUrlException(shortUrl);
    }
  }

  @Override
  public void persist(Url url)
  {
    dao.insert(toUrlRecord(url));
  }

  private UrlRecord toUrlRecord(Url url)
  {
    return new UrlRecord(url.shortUrl, url.originalUrl);
  }

  private Url newUrl(UrlRecord urlRecord)
  {
    return new Url(urlRecord.getShortUrl(), urlRecord.getOriginalUrl());
  }

}

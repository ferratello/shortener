package com.gm.shortener.dao;

import java.util.Objects;

public class UrlRecord
{

  private String originalUrl;
  private String shortUrl;

  public UrlRecord()
  {
  }

  public UrlRecord(String shortUrl, String originalUrl)
  {
    this.shortUrl = shortUrl;
    this.originalUrl = originalUrl;
  }

  public void setOriginalUrl(String originalUrl)
  {
    this.originalUrl = originalUrl;
  }

  public void setShortUrl(String shortUrl)
  {
    this.shortUrl = shortUrl;
  }

  public String getOriginalUrl()
  {
    return originalUrl;
  }

  public String getShortUrl()
  {
    return shortUrl;
  }

  @Override
  public boolean equals(Object o)
  {
    if(this == o) return true;
    if(o == null || getClass() != o.getClass()) return false;
    UrlRecord record = (UrlRecord) o;
    return Objects.equals(originalUrl, record.originalUrl) &&
        Objects.equals(shortUrl, record.shortUrl);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(originalUrl, shortUrl);
  }

  @Override
  public String toString()
  {
    return "UrlRecord{" +
        "originalUrl='" + originalUrl + '\'' +
        ", shortUrl='" + shortUrl + '\'' +
        '}';
  }
}

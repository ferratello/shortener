package com.gm.shortener.storage;

public class Url
{
  public final String originalUrl;
  public final String shortUrl;

  public Url(String shortUrl, String originalUrl)
  {
    this.shortUrl = shortUrl;
    this.originalUrl = originalUrl;
  }
}

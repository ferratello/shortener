package com.gm.shortener.port;

import com.gm.shortener.storage.Url;

public interface URLStorage
{
  Url fromOriginalUrl(String originalUrl);

  Url fromShortUrl(String shortUrl);

  void persist(Url url);
}

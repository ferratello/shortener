package com.gm.shortener.storage;

import com.gm.shortener.port.URLStorage;

import java.util.Map;

public class InMemoryURLStorage implements URLStorage
{
  private final Map<String, String> urlMap;

  public InMemoryURLStorage(Map<String, String> urlMap)
  {
    this.urlMap = urlMap;
  }

  public Url fromOriginalUrl(String originalUrl)
  {
    for(Map.Entry<String, String> entry : urlMap.entrySet())
    {
      if(entry.getValue().equals(originalUrl))
        return new Url(entry.getKey(), originalUrl);
    }
    throw new MissingUrlException(originalUrl);
  }

  public Url fromShortUrl(String shortUrl)
  {
    if(!urlMap.containsKey(shortUrl))
      throw new MissingUrlException(shortUrl);

    return new Url(shortUrl, urlMap.get(shortUrl));
  }

  public void persist(Url url)
  {
    urlMap.put(url.shortUrl, url.originalUrl);
  }

}

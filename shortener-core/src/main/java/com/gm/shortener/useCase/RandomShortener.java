package com.gm.shortener.useCase;

import com.gm.shortener.url.UrlComposer;
import com.gm.shortener.path.RandomURLPath;
import com.gm.shortener.path.URLPath;
import com.gm.shortener.port.URLStorage;
import com.gm.shortener.storage.Url;

public class RandomShortener extends Shortener
{
  private static final int URL_PATH_LENGTH = 4;
  private final URLPath randomURLPath = new RandomURLPath(URL_PATH_LENGTH);

  private final UrlComposer urlComposer = new UrlComposer("http://short.com/");

  public RandomShortener(URLStorage urlRepository)
  {
    super(urlRepository);
  }

  public String getShort(String originalUrl)
  {
    return retrieveOrInsertUrl(new ShortenerRequest(originalUrl)).shortUrl;
  }

  @Override
  protected Url createNewShortUrl(ShortenerRequest shortenerRequest)
  {
    return new Url(urlComposer.shorten(randomURLPath), shortenerRequest.originalUrl);
  }
}

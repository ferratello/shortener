package com.gm.shortener.useCase;

import com.gm.shortener.UrlComposer;
import com.gm.shortener.path.SeoURLPath;
import com.gm.shortener.port.URLStorage;
import com.gm.shortener.storage.Url;

public class SeoShortener extends Shortener
{
  private final UrlComposer urlComposer = new UrlComposer("http://short.com/");

  public SeoShortener(URLStorage urlRepository)
  {
    super(urlRepository);
  }

  public String getShort(String originalUrl, String seoKeyword)
  {
    return retrieveOrInsertUrl(new ShortenerRequest(originalUrl, seoKeyword)).shortUrl;
  }

  protected Url createNewShortUrl(ShortenerRequest shortenerRequest)
  {
    return new Url(urlComposer.shorten(new SeoURLPath(shortenerRequest.seoKeyword)), shortenerRequest.originalUrl);
  }
}

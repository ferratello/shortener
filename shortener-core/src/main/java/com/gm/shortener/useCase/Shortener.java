package com.gm.shortener.useCase;

import com.gm.shortener.port.URLStorage;
import com.gm.shortener.storage.MissingUrlException;
import com.gm.shortener.storage.Url;

abstract class Shortener
{
  private final URLStorage urlRepository;

  Shortener(URLStorage urlRepository)
  {
    this.urlRepository = urlRepository;
  }

  public String getOriginal(String shortUrl)
  {
    return urlRepository.fromShortUrl(shortUrl).originalUrl;
  }

  protected abstract Url createNewShortUrl(ShortenerRequest shortenerRequest);

  static class ShortenerRequest
  {
    final String originalUrl;
    final String seoKeyword;

    ShortenerRequest(String originalUrl, String seoKeyword)
    {
      this.originalUrl = originalUrl;
      this.seoKeyword = seoKeyword;
    }

    ShortenerRequest(String originalUrl)
    {
      this.originalUrl = originalUrl;
      this.seoKeyword = null;
    }
  }

  Url retrieveOrInsertUrl(ShortenerRequest shortenerRequest)
  {
    Url returnUrl;
    try
    {
      returnUrl = urlRepository.fromOriginalUrl(shortenerRequest.originalUrl);
    }
    catch(MissingUrlException muex)
    {
      returnUrl = createNewShortUrl(shortenerRequest);
      urlRepository.persist(returnUrl);
    }
    return returnUrl;
  }
}
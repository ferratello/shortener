package com.gm.shortener.port;

import com.gm.shortener.storage.InMemoryURLStorage;
import com.gm.shortener.storage.Url;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public abstract class URLRepositoryContractTest
{
  private URLStorage urlRepository;

  @Before
  public void setUp()
  {
    innerSetup();
    urlRepository = createUrlStorage();
  }

  @Test
  public void shortUrlPresent()
  {
    assertThat(urlRepository.fromShortUrl("http/short.com/Abc1").originalUrl, is("http://www.my.url.com/my_long_path"));
  }

  @Test
  public void originalUrlPresent()
  {
    assertThat(urlRepository.fromOriginalUrl("http://www.my.url.com/my_long_path").shortUrl, is("http/short.com/Abc1"));
  }

  @Test(expected = InMemoryURLStorage.MissingUrlException.class)
  public void originalUrlNotPresent()
  {
    urlRepository.fromOriginalUrl("Not.pesent.url");
  }

  @Test(expected = InMemoryURLStorage.MissingUrlException.class)
  public void shortUrlNotPresent()
  {
    urlRepository.fromShortUrl("Not.pesent.url");
  }

  @Test
  public void saveUrl()
  {
    Url url = new Url("http://short.com/aBc1", "http://my.long.url/path");

    urlRepository.persist(url);

    assertThat(getUrlFromPersistence(), is(equalTo("http://my.long.url/path")));
  }

  protected abstract URLStorage createUrlStorage();

  protected abstract String getUrlFromPersistence();

  protected abstract void innerSetup();

}

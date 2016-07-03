package com.gm.shortener.port;

import com.gm.shortener.storage.MissingUrlException;
import com.gm.shortener.storage.Url;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public abstract class URLRepositoryContractTest
{
  protected static final String TEST_SHORT_URL = "http://short.com/aBc1";
  private URLStorage urlStorage;

  @Before
  public void setUp()
  {
    innerSetup();
    urlStorage = createUrlStorage();
  }

  @Test
  public void shortUrlPresent()
  {
    assertThat(urlStorage.fromShortUrl("http/short.com/Abc1").originalUrl, is("http://www.my.url.com/my_long_path"));
  }

  @Test
  public void originalUrlPresent()
  {
    assertThat(urlStorage.fromOriginalUrl("http://www.my.url.com/my_long_path").shortUrl, is("http/short.com/Abc1"));
  }

  @Test(expected = MissingUrlException.class)
  public void originalUrlNotPresent()
  {
    urlStorage.fromOriginalUrl("Not.pesent.url");
  }

  @Test(expected = MissingUrlException.class)
  public void shortUrlNotPresent()
  {
    urlStorage.fromShortUrl("Not.pesent.url");
  }

  @Test
  public void saveUrl()
  {
    Url url = new Url(TEST_SHORT_URL, "http://my.long.url/path");

    urlStorage.persist(url);

    assertThat(getOriginalUrlFromPersistence(), is(equalTo("http://my.long.url/path")));
  }

  protected abstract URLStorage createUrlStorage();

  protected abstract String getOriginalUrlFromPersistence();

  protected abstract void innerSetup();

}

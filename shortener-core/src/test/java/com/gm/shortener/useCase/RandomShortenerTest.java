package com.gm.shortener.useCase;

import com.gm.shortener.port.URLStorage;
import com.gm.shortener.storage.Url;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RandomShortenerTest
{
  @Rule
  public final JUnitRuleMockery mockery = new JUnitRuleMockery();

  private final URLStorage urlRepository = mockery.mock(URLStorage.class);

  private final RandomShortener underTest = new RandomShortener(urlRepository);

  @Test
  public void retrieveShortUrlFromRepository()
  {
    mockery.checking(new Expectations()
    {{
      oneOf(urlRepository).fromOriginalUrl(with(any(String.class)));
      will(returnValue(new Url("short_url", "http://www.my.url.com/my_path")));
    }});

    assertThat(underTest.getShort("http://www.my.url.com/my_path"), is(equalTo("short_url")));
  }

  @Test
  public void retrieveOriginalUrlFromRepository()
  {
    mockery.checking(new Expectations()
    {{
      oneOf(urlRepository).fromShortUrl(with(any(String.class)));
      will(returnValue(new Url("short_url", "http://www.my.url.com/my_path")));
    }});

    assertThat(underTest.getOriginal("short_url"), is(equalTo("http://www.my.url.com/my_path")));
  }
}
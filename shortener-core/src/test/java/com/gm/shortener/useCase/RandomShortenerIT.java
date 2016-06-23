package com.gm.shortener.useCase;

import com.gm.shortener.storage.InMemoryURLStorage;
import com.gm.shortener.port.URLStorage;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class RandomShortenerIT
{
  private final URLStorage urlStorage = new InMemoryURLStorage(new HashMap<String, String>());
  private final RandomShortener underTest = new RandomShortener(urlStorage);

  @Test
  public void randomShortUrl()
  {
    assertThat(underTest.getShort("anyUrl"), containsString("http://short.com/"));
  }

  @Test
  public void givenUrlRetrieveSameShortened()
  {
    String shortened = underTest.getShort("anyUrl");
    assertThat(underTest.getShort("anyUrl"), is(equalTo(shortened)));
  }

  @Test
  public void getOriginal()
  {
    String shortUrl = underTest.getShort("http://original.com/my_path");
    assertThat(underTest.getOriginal(shortUrl), is(equalTo("http://original.com/my_path")));
  }
}
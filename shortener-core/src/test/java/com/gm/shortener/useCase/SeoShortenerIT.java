package com.gm.shortener.useCase;

import com.gm.shortener.storage.InMemoryURLStorage;
import com.gm.shortener.port.URLStorage;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SeoShortenerIT
{

  private final URLStorage urlStorage = new InMemoryURLStorage(new HashMap<String, String>());
  private final SeoShortener underTest = new SeoShortener(urlStorage);

  @Test
  public void seoKeyword()
  {
    assertThat(underTest.getShort("http://www.my.url.com/somepath/service", "MY_SEO_KEY"),
               is("http://short.com/MY_SEO_KEY"));
  }

  @Test
  public void getOriginal()
  {
    String shortUrl = underTest.getShort("http://original.com/my_path", "MY_KEY");
    assertThat(underTest.getOriginal(shortUrl), is(equalTo("http://original.com/my_path")));
  }

}
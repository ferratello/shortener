package com.gm.shortener;

import com.gm.shortener.path.URLPath;
import org.jmock.Expectations;
import org.junit.Rule;
import org.junit.Test;
import org.jmock.integration.junit4.JUnitRuleMockery;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class UrlComposerTest
{
  @Rule
  public final JUnitRuleMockery mockery = new JUnitRuleMockery();

  private final URLPath urlPath = mockery.mock(URLPath.class);

  @Test
  public void generateUrlWithSeoKeyword()
  {
    UrlComposer underTest = new UrlComposer("http://short.com/");
    mockery.checking(new Expectations() {{
      oneOf(urlPath).generate();will(returnValue("LOREM_IPSUM"));
    }});
    assertThat(underTest.shorten(urlPath),is("http://short.com/LOREM_IPSUM"));
  }
}

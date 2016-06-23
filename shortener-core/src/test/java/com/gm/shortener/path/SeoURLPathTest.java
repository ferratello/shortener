package com.gm.shortener.path;

import com.gm.shortener.path.SeoURLPath;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SeoURLPathTest
{
  @Rule
  public final ExpectedException expectedEx = ExpectedException.none();

  @Test
  public void generateSeoKeywordAsIs()
  {
    SeoURLPath underTest = new SeoURLPath("MY_SEO_KEY");
    assertThat(underTest.generate(), is("MY_SEO_KEY"));
  }

  @Test
  public void seoKeywordEmpty()
  {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage(containsString("Seo keyword cannot be empty or null"));

    new SeoURLPath("");
  }

  @Test
  public void seoKeywordMaxLength()
  {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage(containsString("Max length (20 char)"));

    new SeoURLPath("MY_SEO_KEY_WITH_LENGTH_GREATER_THAN_20_CHARACTERS");
  }
}
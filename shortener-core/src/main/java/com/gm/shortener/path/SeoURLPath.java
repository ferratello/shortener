package com.gm.shortener.path;

public class SeoURLPath implements URLPath
{
  private final String keyword;

  public SeoURLPath(String seoKeyword)
  {
    if(seoKeyword == null || seoKeyword.isEmpty())
      throw new IllegalArgumentException("Seo keyword cannot be empty or null");

    if(seoKeyword.length() > 20)
      throw new IllegalArgumentException("Max length (20 char) of SEO keyword exceeded: try with a getShort keyword");

    this.keyword = seoKeyword;
  }

  public String generate()
  {
    return keyword;
  }
}

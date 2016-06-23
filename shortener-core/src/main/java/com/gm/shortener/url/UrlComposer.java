package com.gm.shortener.url;

import com.gm.shortener.path.URLPath;

public class UrlComposer
{
  private final String shortDomain;

  public UrlComposer(String shortDomain)
  {
    this.shortDomain = shortDomain;
  }

  public String shorten(URLPath key)
  {
    return shortDomain +key.generate();
  }

}

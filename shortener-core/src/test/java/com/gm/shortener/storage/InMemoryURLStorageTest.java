package com.gm.shortener.storage;

import com.gm.shortener.port.URLRepositoryContractTest;
import com.gm.shortener.port.URLStorage;

import java.util.HashMap;
import java.util.Map;

public class InMemoryURLStorageTest extends URLRepositoryContractTest
{
  private Map<String, String> urlMap;

  protected URLStorage createUrlStorage()
  {
    return new InMemoryURLStorage(urlMap);
  }

  protected String getUrlFromPersistence()
  {
    return urlMap.get("http://short.com/aBc1");
  }

  protected void innerSetup()
  {
    urlMap = new HashMap<String, String>()
    {{
      put("http/short.com/Abc1", "http://www.my.url.com/my_long_path");
    }};
  }

}
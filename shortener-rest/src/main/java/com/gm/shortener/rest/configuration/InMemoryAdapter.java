package com.gm.shortener.rest.configuration;

import com.gm.shortener.port.URLStorage;
import com.gm.shortener.storage.InMemoryURLStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class InMemoryAdapter
{
  private static final Logger logger = LoggerFactory.getLogger(InMemoryAdapter.class);

  public InMemoryAdapter()
  {
    logger.info(this.getClass().getName() + " configured");
  }

  @Bean
  public URLStorage inMemoryUrlStorage()
  {
    return new InMemoryURLStorage(new HashMap<>());
  }
}

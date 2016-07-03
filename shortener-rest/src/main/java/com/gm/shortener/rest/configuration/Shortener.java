package com.gm.shortener.rest.configuration;

import com.gm.shortener.port.URLStorage;
import com.gm.shortener.useCase.RandomShortener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Shortener
{

  @Autowired
  private URLStorage mysqlUrlStorage;

  @Bean
  public RandomShortener randomShortener()
  {
    return new RandomShortener(mysqlUrlStorage);
  }

}


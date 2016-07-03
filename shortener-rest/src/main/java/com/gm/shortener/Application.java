package com.gm.shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages =
    {"com.gm.shortener.rest.configuration",
     "com.gm.shortener.rest.controller"})
public class Application
{
  public static void main(String[] args)
  {
    SpringApplication.run(Application.class, args);
  }
}

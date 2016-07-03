package com.gm.shortener.rest.controller;

import com.gm.shortener.storage.MissingUrlException;
import com.gm.shortener.useCase.RandomShortener;
import org.apache.tomcat.util.buf.UDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class RandomShortenerController
{
  private static final String ENDPOINT_SHORTENER_GENERATE_RANDOM = "/generate";
  private static final String ENDPOINT_SHORTENER_RETRIEVE_RANDOM = "/get";
  private static Logger logger = LoggerFactory.getLogger(RandomShortenerController.class);
  private final RandomShortener randomShortener;

  @Autowired
  public RandomShortenerController(RandomShortener randomShortener)
  {
    this.randomShortener = randomShortener;
  }

  @RequestMapping(value = ENDPOINT_SHORTENER_GENERATE_RANDOM, method = {GET}, produces = {"text/plain"})
  public ResponseEntity<String> generate(@RequestParam("url") String originalUrl)
  {
    try
    {
      return ResponseEntity.ok(randomShortener.getShort(UDecoder.URLDecode(originalUrl)));
    }
    catch(MissingUrlException ex)
    {
      logger.info("Error generating short url{}", ex.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @RequestMapping(value = ENDPOINT_SHORTENER_RETRIEVE_RANDOM, method = {GET}, produces = {"text/plain"})
  public ResponseEntity<String> get(@RequestParam("url") String shortUrl)
  {
    try
    {
      return ResponseEntity.ok(randomShortener.getOriginal(UDecoder.URLDecode(shortUrl)));
    }
    catch(MissingUrlException ex)
    {
      logger.info("Url not found {}", ex.getMessage());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}

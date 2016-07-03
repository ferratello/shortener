package com.gm.shortener.rest.controller;

import com.gm.shortener.storage.InMemoryURLStorage;
import com.gm.shortener.useCase.RandomShortener;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URLEncoder;
import java.util.HashMap;

import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class RandomShortenerControllerIT
{
  @Rule
  public JUnitRuleMockery mockery = new JUnitRuleMockery();

  private RandomShortenerController randomShortenerController = new RandomShortenerController(new RandomShortener(new InMemoryURLStorage(
      new HashMap<>())));
  private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(randomShortenerController)
                                           .build();

  @Test
  public void generateRandomUrlAndRetrieveOriginal() throws Exception
  {
    String encodedParam = URLEncoder.encode("http://my.url.com/my_long_url", "UTF-8");
    encodedParam = encodedParam.replace(".", "%2E");
    MvcResult result = mockMvc.perform(get("/generate?url=" + encodedParam)
                                           .contentType(TEXT_PLAIN))
                              .andDo(print())
                              .andExpect(status().isOk())
                              .andExpect(content().contentType(TEXT_PLAIN))
                              .andReturn();
    String shortUrl = result.getResponse().getContentAsString();

    encodedParam = URLEncoder.encode(shortUrl, "UTF-8");
    encodedParam = encodedParam.replace(".", "%2E");
    mockMvc.perform(get("/get/?url=" + encodedParam)
                        .contentType(TEXT_PLAIN))
           .andDo(print())
           .andExpect(status().isOk())
           .andExpect(content().contentType(TEXT_PLAIN))
           .andExpect(content().string("http://my.url.com/my_long_url"))
           .andReturn();
  }

}
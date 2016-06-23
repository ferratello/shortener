package com.gm.shortener.path;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class RandomURLPathTest
{
  @Test
  public void generateRandomString()
  {
    RandomURLPath underTest = new RandomURLPath(5);
    String first =  underTest.generate();
    assertThat(underTest.generate(), is(not(equalTo(first))));
  }

  @Test
  public void generatedPathLength()
  {
    RandomURLPath underTest = new RandomURLPath(4);
    assertThat(underTest.generate().length(), is(4));
  }

}
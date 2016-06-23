package com.gm.shortener.path;

import java.security.SecureRandom;
import java.util.Random;

public class RandomURLPath implements URLPath
{
  private final String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  private final int urlPathLength;

  public RandomURLPath(int urlPathLength)
  {
    this.urlPathLength = urlPathLength;
  }

  public String generate()
  {
    return generateString(urlPathLength);
  }

  private String generateString( int length)
  {
    Random rng = new SecureRandom();
    char[] text = new char[length];
    for (int i = 0; i < length; i++)
    {
      text[i] = characters.charAt(rng.nextInt(characters.length()));
    }
    return new String(text);
  }
}

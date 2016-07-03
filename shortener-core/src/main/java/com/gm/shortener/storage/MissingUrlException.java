package com.gm.shortener.storage;

public class MissingUrlException extends RuntimeException
{
  public MissingUrlException(String message)
  {
    super(message + " not present");
  }
}

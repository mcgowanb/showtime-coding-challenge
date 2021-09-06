package com.showtime.analytics.codingchallenge.common.exception;

import lombok.Getter;

import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {

  @Getter
  private final String userMessage;

  @Getter
  private final HttpStatus statusCode;

  @Getter
  private final Level logLevel;

  public ApplicationException(final String message, final String userMessage, final HttpStatus statusCode, final Level logLevel) {
    super(message);
    this.userMessage = userMessage;
    this.statusCode = statusCode;
    this.logLevel = logLevel;
  }
  
  public static class InvalidShortUrlException extends ApplicationException {

    public InvalidShortUrlException(final String message, final String userMessage, final HttpStatus statusCode, final Level logLevel) {
      super(message, userMessage, statusCode, logLevel);
    }
  }
}

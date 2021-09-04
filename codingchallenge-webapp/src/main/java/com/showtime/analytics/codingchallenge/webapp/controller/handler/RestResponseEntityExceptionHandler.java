package com.showtime.analytics.codingchallenge.webapp.controller.handler;

import static com.showtime.analytics.codingchallenge.common.constant.ApplicationConstants.GENERIC_UNKNOWN_ERROR_MESSAGE;
import static com.showtime.analytics.codingchallenge.common.constant.ApplicationConstants.GENERIC_UNKNOWN_ERROR_TRY_AGAIN_MESSAGE;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import lombok.extern.log4j.Log4j2;

import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.showtime.analytics.codingchallenge.common.error.ErrorResponse;
import com.showtime.analytics.codingchallenge.common.error.ErrorSeverity;
import com.showtime.analytics.codingchallenge.common.exception.ApplicationException;

@Log4j2
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> runTimeExceptionHandler(final RuntimeException e) {
    return respond(e, GENERIC_UNKNOWN_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(HttpClientErrorException.class)
  public ResponseEntity<ErrorResponse> httpClientErrorExceptionHandler(final HttpClientErrorException e) {
    return respond(e, e.getStatusText(), e.getStatusCode());
  }

  @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
  public ResponseEntity<ErrorResponse> unauthorizedExceptionHandler(final HttpClientErrorException.Unauthorized e) {
    return respond(e, GENERIC_UNKNOWN_ERROR_MESSAGE, e.getStatusCode(), Level.WARN);
  }

  @ExceptionHandler(HttpServerErrorException.class)
  public ResponseEntity<ErrorResponse> httpServerErrorExceptionHandler(final HttpServerErrorException e) {
    return respond(e, GENERIC_UNKNOWN_ERROR_MESSAGE, e.getStatusCode());
  }

  @ExceptionHandler(TimeoutException.class)
  public ResponseEntity<ErrorResponse> timeoutExceptionHandler(final Exception e) {
    return respond(e, GENERIC_UNKNOWN_ERROR_TRY_AGAIN_MESSAGE, HttpStatus.REQUEST_TIMEOUT);
  }

  @ExceptionHandler({ IOException.class, NullPointerException.class })
  public ResponseEntity<ErrorResponse> internalServerErrorExceptionHandler(final Exception e) {
    return respond(e, GENERIC_UNKNOWN_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ApplicationException.class)
  public ResponseEntity<ErrorResponse> urlHandlingException(final ApplicationException e) {
    return respond(e, e.getUserMessage(), e.getStatusCode(), e.getLogLevel());
  }

  private ResponseEntity<ErrorResponse> respond(final Exception exception, final String userMessage, final HttpStatus httpStatus) {
    return respond(exception, userMessage, httpStatus, Level.ERROR);
  }

  private ResponseEntity<ErrorResponse> respond(final Exception exception, final String userMessage, final HttpStatus httpStatus, final Level level) {
    log.atLevel(level)
        .log("userMessage:[{}], httpStatus=[{}], exception=[{}]", userMessage, httpStatus, exception);
    return new ResponseEntity<>(ErrorResponse.builder()
        .userMessage(userMessage)
        .severity(ErrorSeverity.ERROR)
        .build(),
        httpStatus);
  }

}

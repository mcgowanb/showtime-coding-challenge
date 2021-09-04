package com.showtime.analytics.codingchallenge.common.error;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import lombok.Builder;
import lombok.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@Value
@Builder(builderClassName = "Builder")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = ErrorResponse.Builder.class)
public class ErrorResponse {

  /**
   * Error message tailored to an end user / customer.
   */
  String userMessage;

  /**
   * Severity of error.
   */
  ErrorSeverity severity;
  
  @JsonPOJOBuilder(withPrefix = EMPTY)
  public static class Builder {
  }

}
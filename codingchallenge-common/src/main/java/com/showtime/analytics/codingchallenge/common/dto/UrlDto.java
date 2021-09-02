package com.showtime.analytics.codingchallenge.common.dto;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Value;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@Value
@Builder(builderClassName = "Builder")
@JsonDeserialize(builder = UrlDto.Builder.class)
public class UrlDto {

  @NotNull
  String fqdn;

  String shortenedUrl;

  @JsonPOJOBuilder(withPrefix = StringUtils.EMPTY)
  public static class Builder {
  }
}

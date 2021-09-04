package com.showtime.analytics.codingchallenge.service.mapper;

import java.util.function.Function;

import com.showtime.analytics.codingchallenge.common.dto.UrlDto;
import com.showtime.analytics.codingchallenge.service.entity.UrlEntity;

public class Mappers {

  public static Function<UrlDto, UrlEntity> urlToEntity = (urlDto) -> UrlEntity.builder().fqdn(urlDto.getFqdn())
      .build();
}

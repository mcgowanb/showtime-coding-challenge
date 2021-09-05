package com.showtime.analytics.codingchallenge.service.impl;

import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;

import com.showtime.analytics.codingchallenge.service.UrlValidationService;
import com.showtime.analytics.codingchallenge.service.entity.UrlEntity;

@Log4j2
@Service
public class UrlValidationServiceImpl implements UrlValidationService {

  @Override
  public Boolean urlIsValid(final UrlEntity urlEntity) {
    return urlIsValid(urlEntity.getFqdn());
  }

  @Override
  public Boolean urlIsValid(final String fqdn) {
    log.info("Validating url: {}", fqdn);
    return true;
  }
}

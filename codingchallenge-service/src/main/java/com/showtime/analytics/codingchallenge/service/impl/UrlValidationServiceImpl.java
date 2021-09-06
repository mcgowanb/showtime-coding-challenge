package com.showtime.analytics.codingchallenge.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;

import com.showtime.analytics.codingchallenge.service.UrlValidationService;
import com.showtime.analytics.codingchallenge.service.client.WebClient;
import com.showtime.analytics.codingchallenge.service.entity.UrlEntity;

@Log4j2
@Service
@RequiredArgsConstructor
public class UrlValidationServiceImpl implements UrlValidationService {

  private final WebClient externalWebClient;

  @Override
  public Boolean urlIsValid(final UrlEntity urlEntity) {
    return urlIsValid(urlEntity.getFqdn());
  }

  @Override
  public boolean urlIsValid(final String fqdn) {
    log.info("Validating url: {}", fqdn);
    final boolean result = externalWebClient.validateUrl(fqdn);
    log.info("{} is valid: {}", fqdn, result);
    return result;
  }
}

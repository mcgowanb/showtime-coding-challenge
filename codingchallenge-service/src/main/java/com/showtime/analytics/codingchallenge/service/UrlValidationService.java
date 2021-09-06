package com.showtime.analytics.codingchallenge.service;

import com.showtime.analytics.codingchallenge.service.entity.UrlEntity;

public interface UrlValidationService {
  Boolean urlIsValid(UrlEntity urlEntity);

  boolean urlIsValid(String fqdn);
}

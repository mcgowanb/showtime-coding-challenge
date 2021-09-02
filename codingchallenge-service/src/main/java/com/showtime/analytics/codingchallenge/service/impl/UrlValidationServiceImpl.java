package com.showtime.analytics.codingchallenge.service.impl;

import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;

import com.showtime.analytics.codingchallenge.service.UrlValidationService;

@Service
@Log4j2
public class UrlValidationServiceImpl implements UrlValidationService {

  @Override
  public void validateDataCollection() {
    log.info("Validating db stuff");
  }
}

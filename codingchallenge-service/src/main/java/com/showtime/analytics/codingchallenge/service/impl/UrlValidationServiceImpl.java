package com.showtime.analytics.codingchallenge.service.impl;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;

import com.showtime.analytics.codingchallenge.service.UrlValidationService;

@Service
@Log4j2
public class UrlValidationServiceImpl implements UrlValidationService {

  @Override
  @SneakyThrows
  public void validateDataCollection() {
    log.info("Validating db stuff, sleeping");
    Thread.sleep(3000);
    log.info("Sleeping over");

  }
}

package com.showtime.analytics.codingchallenge.service.impl;

import lombok.extern.log4j.Log4j2;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.showtime.analytics.codingchallenge.service.CacheService;

@Log4j2
@Service
public class CacheServiceImpl implements CacheService {

  @Override
  @CacheEvict(value = "urls", key = "#shortUrl")
  public void evictFromCache(final String shortUrl) {
    log.info("Evicting {} from the cache", shortUrl);
  }
}

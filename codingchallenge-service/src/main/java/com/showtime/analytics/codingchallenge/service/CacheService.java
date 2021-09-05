package com.showtime.analytics.codingchallenge.service;

import org.springframework.cache.annotation.CacheEvict;

public interface CacheService {
  @CacheEvict(value = "urls", key = "#shortUrl")
  void evictFromCache(String shortUrl);
}

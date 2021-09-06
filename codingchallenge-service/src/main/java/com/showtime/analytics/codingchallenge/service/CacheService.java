package com.showtime.analytics.codingchallenge.service;

import com.showtime.analytics.codingchallenge.service.entity.UrlEntity;

public interface CacheService {
  UrlEntity evictFromCache(UrlEntity urlEntity, String shortUrl);

  UrlEntity addToCache(String shortUrl, UrlEntity urlEntity);
}

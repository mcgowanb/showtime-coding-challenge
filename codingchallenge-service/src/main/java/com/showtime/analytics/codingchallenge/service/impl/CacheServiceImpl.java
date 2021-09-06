package com.showtime.analytics.codingchallenge.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.showtime.analytics.codingchallenge.service.CacheService;
import com.showtime.analytics.codingchallenge.service.entity.UrlEntity;
import com.showtime.analytics.codingchallenge.service.repository.UrlRepository;

@Log4j2
@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {

  private final UrlRepository repository;

  @Override
  @CacheEvict(value = "urls", key = "#shortUrl")
  public UrlEntity evictFromCache(final UrlEntity urlEntity, final String shortUrl) {
    log.info("{} is invalid, evicting from the cache", urlEntity);
    return urlEntity;
  }

  @Override
  @CachePut(value = "urls", key = "#shortUrl")
  public UrlEntity addToCache(final String shortUrl, final UrlEntity urlEntity) {
    return urlEntity;
  }

}

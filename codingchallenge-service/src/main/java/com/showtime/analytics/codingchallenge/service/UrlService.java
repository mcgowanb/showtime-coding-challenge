package com.showtime.analytics.codingchallenge.service;

import com.showtime.analytics.codingchallenge.common.dto.UrlDto;
import com.showtime.analytics.codingchallenge.service.entity.UrlEntity;

public interface UrlService {

  /**
   * Validates the db records to see if the targets are still active
   */
  void validateDataCollection();

  /**
   * Returns a fqdn from a short url
   *
   * @param shortUrl
   * @return
   */
  UrlEntity getDecodedUrl(String shortUrl);

  String getOrCreateShortUrl(UrlDto urlDto);
}

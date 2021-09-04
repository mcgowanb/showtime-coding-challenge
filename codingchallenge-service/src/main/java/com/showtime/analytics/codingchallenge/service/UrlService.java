package com.showtime.analytics.codingchallenge.service;

import com.showtime.analytics.codingchallenge.common.dto.UrlDto;

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
  String getDecodedUrl(String shortUrl);

  String getShortenedUrl(UrlDto urlDto);
}

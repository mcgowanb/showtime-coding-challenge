package com.showtime.analytics.codingchallenge.webapp.controller.impl;

import static com.showtime.analytics.codingchallenge.common.constant.ApplicationConstants.API_URL_PATH;

import java.net.URI;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.showtime.analytics.codingchallenge.common.dto.UrlDto;
import com.showtime.analytics.codingchallenge.service.UrlService;
import com.showtime.analytics.codingchallenge.webapp.controller.UrlController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(API_URL_PATH)
public class UrlControllerImpl implements UrlController {

  private final UrlService urlService;

  @Override
  public ResponseEntity<String> getUrlRedirect(final String shortUrl) {
    log.info("Searching for a match for {}", shortUrl);
    final String url = urlService.getDecodedUrl(shortUrl);

    log.info("Match for {} found, redirecting to {}", shortUrl, url);
    return ResponseEntity.status(HttpStatus.FOUND)
        .location(URI.create(url))
        .build();
  }

  @Override
  public ResponseEntity<String> createShortenedURL(final UrlDto payload) {
    log.info("Creating short url for {}", payload.getFqdn());
    return ResponseEntity.ok(urlService.getOrCreateShortUrl(payload));
  }
}

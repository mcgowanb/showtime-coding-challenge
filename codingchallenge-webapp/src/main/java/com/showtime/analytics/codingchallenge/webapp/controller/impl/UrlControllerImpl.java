package com.showtime.analytics.codingchallenge.webapp.controller.impl;

import static com.showtime.analytics.codingchallenge.common.constant.ApplicationConstants.API_URL_PATH;
import static com.showtime.analytics.codingchallenge.common.constant.ApplicationConstants.PATH_PARAM_URL;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.showtime.analytics.codingchallenge.common.dto.UrlDto;
import com.showtime.analytics.codingchallenge.service.UrlValidationService;
import com.showtime.analytics.codingchallenge.webapp.controller.UrlController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(API_URL_PATH)
public class UrlControllerImpl implements UrlController {

  private final UrlValidationService validationService;

  @Override
  public ResponseEntity<String> getUrlRedirect(@PathParam(PATH_PARAM_URL) final String url) {
    validationService.validateDataCollection();
    return ResponseEntity.ok("Hello world, url is: " + url);
  }

  @Override
  public ResponseEntity<UrlDto> createShortenedURL(@Valid @RequestBody final UrlDto payload) {
    return ResponseEntity.ok(payload);
  }
}

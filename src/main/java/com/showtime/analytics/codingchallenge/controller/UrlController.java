package com.showtime.analytics.codingchallenge.controller;

import static com.showtime.analytics.codingchallenge.constant.ApplicationConstants.API_PATH_DECODE;
import static com.showtime.analytics.codingchallenge.constant.ApplicationConstants.API_PATH_ENCODE;
import static com.showtime.analytics.codingchallenge.constant.ApplicationConstants.API_URL_PATH;
import static com.showtime.analytics.codingchallenge.constant.ApplicationConstants.PATH_PARAM_URL;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.showtime.analytics.codingchallenge.dto.UrlDto;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(API_URL_PATH)

public class UrlController {

  @Operation(summary = "Get shortened URL",
      description = "Take a shortened url and return a redirect to the fqdn of the url",
      responses = {
          @ApiResponse(responseCode = "301", description = "Successful match & redirect"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error"),
          @ApiResponse(responseCode = "400", description = "Target url is invalid and no longer available"),
          @ApiResponse(responseCode = "404", description = "URL does not exist in our database")
      })
  @GetMapping(API_PATH_DECODE)
  ResponseEntity<String> getUrlRedirect(@PathParam(PATH_PARAM_URL) final String url) {
    return ResponseEntity.ok("Hello world, url is: " + url);
  }

  @Operation(summary = "Create a shortened URL",
      description = "Take a url and return a shortened url.",
      responses = {
          @ApiResponse(responseCode = "201", description = "Successful created"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error"),
          @ApiResponse(responseCode = "400", description = "Target url is invalid and no longer available"),
          @ApiResponse(responseCode = "404", description = "URL does not exist in our database")
      })
  @PostMapping(API_PATH_ENCODE)
  ResponseEntity<UrlDto> createShortenedURL(@Valid @RequestBody final UrlDto payload) {
    return ResponseEntity.ok(payload);
  }
}

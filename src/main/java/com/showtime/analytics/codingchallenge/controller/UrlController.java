package com.showtime.analytics.codingchallenge.controller;

import static com.showtime.analytics.codingchallenge.constant.ApplicationConstants.URL_API_PATH;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(URL_API_PATH)

public class UrlController {

  @Operation(summary = "Get Shortened URL",
      description = "Get the shortend url for the fully qualified web address",
      responses = {
          @ApiResponse(responseCode = "200", description = "Success"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      })
  @GetMapping
  ResponseEntity<String> getCheckoutInformation(final String url) {
    return ResponseEntity.ok("Hello world");
  }
}

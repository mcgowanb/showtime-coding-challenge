package com.showtime.analytics.codingchallenge.controller;

import static com.showtime.analytics.codingchallenge.constant.ApplicationConstants.API_PATH_DECODE;
import static com.showtime.analytics.codingchallenge.constant.ApplicationConstants.API_URL_PATH;
import static com.showtime.analytics.codingchallenge.constant.ApplicationConstants.PATH_PARAM_URL;

import javax.websocket.server.PathParam;

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
@RequestMapping(API_URL_PATH)

public class UrlController {

//  @Operation(summary = "Get Shortened URL",
//      description = "Get the shortend url for the fully qualified web address",
//      responses = {
//          @ApiResponse(responseCode = "200", description = "Success"),
//          @ApiResponse(responseCode = "500", description = "Internal Server Error")
//      })
//  @GetMapping("/{" + PATH_PARAM_FQDN + "}")
//  ResponseEntity<String> getShortenedUrl(@PathVariable(name = PATH_PARAM_FQDN) final String fqdn) {
//    return ResponseEntity.ok("Hello world, url is: " + fqdn);
//  }

  @Operation(summary = "Get Shortened URL",
      description = "Get the shortend url for the fully qualified web address",
      responses = {
          @ApiResponse(responseCode = "200", description = "Success"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      })
  @GetMapping(API_PATH_DECODE)
  ResponseEntity<String> getShortenedUrl(@PathParam(PATH_PARAM_URL) final String url) {
    return ResponseEntity.ok("Hello world, url is: " + url);
  }
}

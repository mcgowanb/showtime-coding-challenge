package com.showtime.analytics.codingchallenge.webapp.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.showtime.analytics.codingchallenge.common.constant.ApplicationConstants;
import com.showtime.analytics.codingchallenge.common.dto.UrlDto;

public interface UrlController {

  @Operation(summary = "Get shortened URL",
      description = "Take a shortened url and return a redirect to the fqdn of the url",
      responses = {
          @ApiResponse(responseCode = "301", description = "Successful match & redirect"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error"),
          @ApiResponse(responseCode = "400", description = "Target url is invalid and no longer available"),
          @ApiResponse(responseCode = "404", description = "URL does not exist in our database")
      })
  @GetMapping(ApplicationConstants.API_PATH_DECODE)
  public ResponseEntity<String> getUrlRedirect(@PathParam(ApplicationConstants.PATH_PARAM_URL) final String url);

  @Operation(summary = "Create a shortened URL",
      description = "Take a url and return a shortened url.",
      responses = {
          @ApiResponse(responseCode = "201", description = "Successful created"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error"),
          @ApiResponse(responseCode = "400", description = "Target url is invalid and no longer available"),
          @ApiResponse(responseCode = "404", description = "URL does not exist in our database")
      })
  @PostMapping(ApplicationConstants.API_PATH_ENCODE)
  public ResponseEntity<String> createShortenedURL(@Valid @RequestBody final UrlDto payload);

}

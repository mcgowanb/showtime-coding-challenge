package com.showtime.analytics.codingchallenge.service.client;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class WebClient {

  private final OkHttpClient okHttpClient;

  public boolean validateUrl(final String url) {

    final Request request = new Request.Builder().url(url).head().build();

    try (final Response response = okHttpClient.newCall(request).execute()) {
      return true;
    } catch (final IOException e) {
      return false;
    }
  }
}

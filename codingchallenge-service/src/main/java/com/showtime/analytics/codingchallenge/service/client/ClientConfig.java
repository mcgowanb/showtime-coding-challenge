package com.showtime.analytics.codingchallenge.service.client;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import okhttp3.OkHttpClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

  @Value("${client.readTimeoutMilliseconds}")
  private Integer readTimeoutMs;

  @Value("${client.connectionTimeoutMilliseconds}")
  private Integer connectionTimeoutMs;

  @Bean
  public OkHttpClient externalWebClient() {
    return new OkHttpClient.Builder().connectTimeout(connectionTimeoutMs, MILLISECONDS)
        .readTimeout(readTimeoutMs, MILLISECONDS).build();
  }

}

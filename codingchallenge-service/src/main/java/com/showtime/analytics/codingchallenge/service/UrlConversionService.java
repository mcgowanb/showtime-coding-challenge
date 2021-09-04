package com.showtime.analytics.codingchallenge.service;

public interface UrlConversionService {

  /**
   * Encode a {@link Long} to a string representing the short path
   *
   * @param input
   * @return
   */
  String encode(long input);

  /**
   * Takes a {@link Long} representing the identifier of the FQDN and returning a short path
   *
   * @param input
   * @return
   */
  long decode(String input);
}

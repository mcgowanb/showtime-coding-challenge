package com.showtime.analytics.codingchallenge.service.impl;

import static com.showtime.analytics.codingchallenge.common.constant.ApplicationConstants.ACCEPTABLE_CHARACTERS;

import org.springframework.stereotype.Service;

import com.showtime.analytics.codingchallenge.service.UrlConversionService;

@Service
public class UrlConversionServiceImpl implements UrlConversionService {
  //  https://github.com/AnteMarin/UrlShortener-API/blob/develop/src/main/java/com/amarin/urlshortenerapi/service/BaseConversion.java
  private final char[] allowedCharacters = ACCEPTABLE_CHARACTERS.toCharArray();

  private final int base = allowedCharacters.length;

  @Override
  public String encode(long input) {
    final StringBuilder encodedString = new StringBuilder();

    if (input == 0) {
      return String.valueOf(allowedCharacters[0]);
    }

    while (input > 0) {
      encodedString.append(allowedCharacters[(int) (input % base)]);
      input = input / base;
    }

    return encodedString.reverse().toString();
  }

  @Override
  public long decode(final String input) {
    final char[] characters = input.toCharArray();
    final int length = characters.length;

    int decoded = 0;

    //counter is used to avoid reversing input string
    int counter = 1;
    for (int i = 0; i < length; i++) {
      decoded += ACCEPTABLE_CHARACTERS.indexOf(characters[i]) * Math.pow(base, length - (double) counter);
      counter++;
    }
    return decoded;
  }
}

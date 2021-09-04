package com.showtime.analytics.codingchallenge.service.impl;

import static com.showtime.analytics.codingchallenge.common.constant.ApplicationConstants.INVALID_SHORT_URL_EXCEPTION_MESSAGE;
import static com.showtime.analytics.codingchallenge.common.constant.ApplicationConstants.URL_NOT_FOUND_MESSAGE;
import static com.showtime.analytics.codingchallenge.service.mapper.Mappers.urlToEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.showtime.analytics.codingchallenge.common.dto.UrlDto;
import com.showtime.analytics.codingchallenge.common.exception.ApplicationException;
import com.showtime.analytics.codingchallenge.service.UrlConversionService;
import com.showtime.analytics.codingchallenge.service.UrlService;
import com.showtime.analytics.codingchallenge.service.entity.UrlEntity;
import com.showtime.analytics.codingchallenge.service.repository.UrlRepository;

@Service
@Log4j2
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

  private final UrlConversionService urlConversionService;

  private final UrlRepository repository;

  @Value("${spring.application.baseUrl}")
  private String baseUrl;

  @Value("${spring.application.scheme}")
  private String scheme;

  @Override
  @SneakyThrows
  public void validateDataCollection() {
    log.info("Validating db stuff, sleeping");
    Thread.sleep(3000);
    log.info("Sleeping over bai");

  }

  @Override
  public String getDecodedUrl(final String shortUrl) {
    final long urlIdentifier = urlConversionService.decode(shortUrl);

    final UrlEntity urlEntity = repository.findById(urlIdentifier)
        .orElseThrow(() -> new ApplicationException.InvalidShortUrlException(URL_NOT_FOUND_MESSAGE,
            INVALID_SHORT_URL_EXCEPTION_MESSAGE,
            HttpStatus.BAD_REQUEST,
            Level.ERROR
        ));

    return urlEntity.getFqdn();
  }

  @Override
  public String getShortenedUrl(final UrlDto urlDto) {

    final Optional<UrlEntity> fqdnOptional = repository.findByFqdn(urlDto.getFqdn());

    if (fqdnOptional.isPresent()) {
      return buildShortenedUrl(urlConversionService.encode(fqdnOptional.get().getId()));
    }

    final UrlEntity entity = urlToEntity.apply(urlDto);
    repository.save(entity);

    final String encodedPath = urlConversionService.encode(entity.getId());
    return buildShortenedUrl(encodedPath);
  }

  private String buildShortenedUrl(final String encodedPath) {
    return UriComponentsBuilder.newInstance()
        .scheme(scheme).host(baseUrl).path(encodedPath).build().toString();
  }

  private String getEncodedPathFromUrl(final String url) {
    try {
      return new URI(url).getPath();
    } catch (final URISyntaxException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    }

  }

}

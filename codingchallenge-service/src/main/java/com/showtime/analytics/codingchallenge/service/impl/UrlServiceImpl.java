package com.showtime.analytics.codingchallenge.service.impl;

import static com.showtime.analytics.codingchallenge.common.constant.ApplicationConstants.INVALID_SHORT_URL_EXCEPTION_MESSAGE;
import static com.showtime.analytics.codingchallenge.common.constant.ApplicationConstants.URL_NOT_FOUND_MESSAGE;
import static com.showtime.analytics.codingchallenge.service.mapper.Mappers.urlToEntity;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.showtime.analytics.codingchallenge.common.dto.UrlDto;
import com.showtime.analytics.codingchallenge.common.exception.ApplicationException;
import com.showtime.analytics.codingchallenge.service.UrlConversionService;
import com.showtime.analytics.codingchallenge.service.UrlService;
import com.showtime.analytics.codingchallenge.service.UrlValidationService;
import com.showtime.analytics.codingchallenge.service.entity.UrlEntity;
import com.showtime.analytics.codingchallenge.service.repository.UrlRepository;

@Service
@Log4j2
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

  private final UrlConversionService urlConversionService;

  private final UrlValidationService urlValidationService;

  private final UrlRepository repository;

  @Value("${spring.application.baseUrl}")
  private String baseUrl;

  @Value("${spring.application.scheme}")
  private String scheme;

  @Override
  @SneakyThrows
  @Transactional
  public void validateDataCollection() {
    log.info("Beginning to validate db for valid urls");

    final List<UrlEntity> failedUrls = repository.getAllRecords().parallel()
        .filter(Predicate.not(urlValidationService::urlIsValid))
        .map(i -> {
          evictFromCache(urlConversionService.encode(i.getId()));
          return i;
        })
        .collect(Collectors.toList());

    log.info("{} url's are no longer valid, purging from the database", failedUrls.size());

    repository.deleteAll(failedUrls);

  }

  @Override
  @Cacheable(value = "urls", key = "#shortUrl")
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
  public String getOrCreateShortUrl(final UrlDto urlDto) {

    final Optional<UrlEntity> urlEntityOptional = repository.findByFqdn(urlDto.getFqdn());

    if (urlEntityOptional.isPresent()) {

      UrlEntity urlEntity = urlEntityOptional.get();
      if (urlValidationService.urlIsValid(urlEntity)) {
        return buildShortenedUrl(urlConversionService.encode(urlEntity.getId()));
      }
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
    
  @CacheEvict(value = "urls", key = "#shortUrl")
  public void evictFromCache(final String shortUrl) {
//    log.info("Evicting {} from the cache", shortUrl);
  }
}

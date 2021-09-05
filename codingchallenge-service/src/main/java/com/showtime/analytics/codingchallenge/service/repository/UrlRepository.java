package com.showtime.analytics.codingchallenge.service.repository;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.showtime.analytics.codingchallenge.service.entity.UrlEntity;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {

  Optional<UrlEntity> findByFqdn(final String fqdn);

  @Query("SELECT u FROM UrlEntity u")
  Stream<UrlEntity> getAllRecords();
}

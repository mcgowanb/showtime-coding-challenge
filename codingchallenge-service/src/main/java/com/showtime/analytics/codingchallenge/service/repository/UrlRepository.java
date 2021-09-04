package com.showtime.analytics.codingchallenge.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.showtime.analytics.codingchallenge.service.entity.UrlEntity;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {

  Optional<UrlEntity> findByFqdn(final String fqdn);
}

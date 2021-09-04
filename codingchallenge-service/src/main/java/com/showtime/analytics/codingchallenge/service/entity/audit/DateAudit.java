package com.showtime.analytics.codingchallenge.service.entity.audit;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import lombok.Data;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
    value = { "createdAt", "updatedAt" },
    allowGetters = true
)
public abstract class DateAudit implements Serializable {

  @CreatedDate
  @Column(nullable = false, updatable = false, name = "created_at")
  private Instant createdAt;

  @LastModifiedDate
  @Column(nullable = false, name = "updated_at")
  private Instant updatedAt;

}
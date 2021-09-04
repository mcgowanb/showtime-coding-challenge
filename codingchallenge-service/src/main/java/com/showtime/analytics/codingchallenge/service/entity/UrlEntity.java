package com.showtime.analytics.codingchallenge.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hibernate.annotations.DynamicUpdate;

import com.showtime.analytics.codingchallenge.service.entity.audit.DateAudit;

@Data
@Entity
@DynamicUpdate
@Table(name = "url_metadata")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class UrlEntity extends DateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "urlSequencer")
  @SequenceGenerator(name = "urlSequencer", sequenceName = "URL_SEQ", allocationSize = 1)
  @ToString.Include
  private Long id;

  @Column(name = "fqdn")
  @ToString.Include
  private String fqdn;
}

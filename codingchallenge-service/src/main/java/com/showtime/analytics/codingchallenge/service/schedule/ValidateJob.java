package com.showtime.analytics.codingchallenge.service.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Service;

import com.showtime.analytics.codingchallenge.service.UrlService;

@Log4j2
@Service
@RequiredArgsConstructor
@DisallowConcurrentExecution
public class ValidateJob implements Job {

  private final UrlService urlService;

  @Override
  public void execute(final JobExecutionContext context) {
    urlService.validateDataCollection();
  }
}

package com.showtime.analytics.codingchallenge.service.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ActiveScheduleJob implements Job {
  @Override
  public void execute(final JobExecutionContext jobExecutionContext) throws JobExecutionException {
    final int x = 4;
  }
}

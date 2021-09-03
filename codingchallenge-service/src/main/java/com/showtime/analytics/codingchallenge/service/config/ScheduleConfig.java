package com.showtime.analytics.codingchallenge.service.config;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.showtime.analytics.codingchallenge.service.schedule.JobScheduler;

@Configuration
public class ScheduleConfig {

  /*
  Using the job in this way is a suboptimal solution if this microservice was to be used in distributed environment where there
  would be more than one instance of the application running, as this would trigger a job on a schedule for ever active pod
   */

  @Bean
  public JobDetailFactoryBean jobDetail() {
    final JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
    jobDetailFactory.setJobClass(JobScheduler.class);
    jobDetailFactory.setDescription("Invoke Sample Job service...");
    jobDetailFactory.setDurability(true);
    return jobDetailFactory;
  }

  @Bean
  public SimpleTriggerFactoryBean trigger(final JobDetail job) {
    final SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
    trigger.setJobDetail(job);
    trigger.setRepeatInterval(5000);
    trigger.setStartDelay(10000);
    trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
    return trigger;
  }

}

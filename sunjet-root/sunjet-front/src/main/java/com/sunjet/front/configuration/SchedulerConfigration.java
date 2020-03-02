package com.sunjet.front.configuration;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sunjet.front.job.CalendarJob;

@Configuration
public class SchedulerConfigration {

	@Value("${scheduler.trigger.calendarJob.cron}")
	private String calendarJobTrigger;

	

	@Bean
	public JobDetail CalendarJobDetail() {
		return JobBuilder.newJob(CalendarJob.class).withIdentity("CalendarJob").storeDurably()
				.build();
	}

	@Bean
	public Trigger calendarJobTrigger() {
		CronScheduleBuilder cronSchedulerBuilder = CronScheduleBuilder.cronSchedule(calendarJobTrigger);
		return TriggerBuilder.newTrigger().forJob(CalendarJobDetail())
				.withIdentity("CalendarJobScheduler").withSchedule(cronSchedulerBuilder).build();
	}

}

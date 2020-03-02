package com.sunjet.front.job;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.sunjet.front.job.service.JobService;

@DisallowConcurrentExecution
public class CalendarJob extends QuartzJobBean {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JobService jobService;

	public void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info(" =========== CalendarJob START... ===========");
		LocalDate start = LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
		LocalDate end = LocalDate.now().with(TemporalAdjusters.lastDayOfYear());
		jobService.calculateCalendar(start, end);
		logger.info(" =========== CalendarJob END!     ===========");
	}

}
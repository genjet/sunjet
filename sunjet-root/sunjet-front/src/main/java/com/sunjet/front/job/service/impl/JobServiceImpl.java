package com.sunjet.front.job.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunjet.common.dao.SjCalendarRepository;
import com.sunjet.common.entity.SjCalendar;
import com.sunjet.front.job.service.JobService;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private SjCalendarRepository sjCalendarRepository;

	@Override
	public void calculateCalendar(LocalDate start, LocalDate end) {
		List<SjCalendar> sjCalendarList = new ArrayList<SjCalendar>();

		List<LocalDate> dates = Stream.iterate(start, date -> date.plusDays(1)).limit(ChronoUnit.DAYS.between(start, end)).collect(Collectors.toList());
		List<SjCalendar> allCalendars = sjCalendarRepository.findAll();
		for (LocalDate localDate : dates) {
			if (allCalendars.stream().anyMatch(s -> !s.getCalendarDate().equals(localDate))) {
				SjCalendar sjCalendar = new SjCalendar();
				sjCalendar.setCalendarDate(localDate);
				DayOfWeek dayOfWeek = localDate.getDayOfWeek();
				sjCalendar.setDayOfWeek(dayOfWeek);
				boolean isHoliday = Boolean.FALSE;
				if (DayOfWeek.SUNDAY.equals(dayOfWeek) || DayOfWeek.SATURDAY.equals(dayOfWeek)) {
					isHoliday = Boolean.TRUE;
				}
				sjCalendar.setIsHoliday(isHoliday);
				sjCalendarList.add(sjCalendar);
			}
		}
		sjCalendarRepository.saveAll(sjCalendarList);

	}

}

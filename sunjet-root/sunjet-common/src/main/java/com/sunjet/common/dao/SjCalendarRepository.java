package com.sunjet.common.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunjet.common.entity.SjCalendar;

@Repository
public interface SjCalendarRepository extends JpaRepository<SjCalendar, String> {
	List<SjCalendar> findByCalendarDateBetween(LocalDate startDate, LocalDate EndDate);
}

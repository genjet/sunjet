package com.sunjet.common.entity;

import java.time.DayOfWeek;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "sj_calendar")
public class SjCalendar extends GenericEntity {

	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "jpa-uuid")
	private String oid;

	@Column(name = "calendar_date")
	private LocalDate calendarDate;

	@Column(name = "is_holiday")
	@Type(type = "yes_no")
	private Boolean isHoliday;

	@Column(name = "day_of_week")
	@Enumerated(EnumType.STRING)
	private DayOfWeek dayOfWeek;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public LocalDate getCalendarDate() {
		return calendarDate;
	}

	public void setCalendarDate(LocalDate calendarDate) {
		this.calendarDate = calendarDate;
	}

	public Boolean getIsHoliday() {
		return isHoliday;
	}

	public void setIsHoliday(Boolean isHoliday) {
		this.isHoliday = isHoliday;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	@Override
	public String toString() {
		return "SjCalendar [oid=" + oid + ", calendarDate=" + calendarDate + ", isHoliday=" + isHoliday + ", dayOfWeek=" + dayOfWeek + "]";
	}

}

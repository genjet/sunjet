package com.sunjet.front.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeaveUtil {
	private static Logger logger = LoggerFactory.getLogger(LeaveUtil.class);

	/**
	 * 特休假計算。2017/1/1 之後的新制。
	 * <p>
	 * . 6個月以上未滿1年：3日特休 (新增)<br>
	 * . 1年以上未滿2年：7日特休<br>
	 * . 2年以上未滿3年：10日特休 (增加3日)<br>
	 * . 3年以上未滿5年：14日特休 (增加4日)<br>
	 * . 5年以上未滿10年：15日特休 (增加1日)<br>
	 * . 10年以上，每一年多給1日，加至30日<br>
	 * 
	 * @param on
	 *            到值日
	 * @return
	 */
	public static double countNewAN(LocalDate onDate) {
		double totalANDays = 0;
		LocalDate today = LocalDate.now();
		LocalDate curOnDate = onDate;
		logger.info("now:{}", today);
		logger.info("onDateTime:{}", onDate.atStartOfDay());
		int countTimes = -1; // 年資
		// 計算年資
		while (curOnDate.isBefore(today) || curOnDate.isEqual(today)) {
			curOnDate = curOnDate.plusYears(1);
			countTimes++;
		}
		Period p = Period.between(onDate, today);
		int monthDiff = p.getMonths();
		logger.info("monthsdiff:{}", monthDiff); // 不足一個月，給0
		totalANDays = countYearPeriod(countTimes, monthDiff);

		return totalANDays;
	}

	/**
	 * 回傳新制的特休時數。
	 * 
	 * @param countTimes
	 * @param monthDiff
	 * @return
	 */
	public static double countYearPeriod(int countTimes, int monthDiff) {
		int anDays = getNewANDaysBySeniority(countTimes, monthDiff);
		return (new BigDecimal(anDays).multiply(new BigDecimal(8)).divide(new BigDecimal(1), 0, BigDecimal.ROUND_UP)).doubleValue();
	}

	/**
	 * 計算舊制的天數。
	 * 
	 * @param seniority
	 * @return
	 */
	public static int getANDaysBySeniority(int seniority) {
		int days = 0;
		if (seniority < 1) {
			days = 0;
		} else if (1 <= seniority && seniority < 3) {
			days = 7;
		} else if (3 <= seniority && seniority < 5) {
			days = 10;
		} else if (5 <= seniority && seniority < 10) {
			days = 14;
		} else if (10 <= seniority && seniority < 25) {
			days = seniority + 5;
		} else {
			days = 30;
		}
		logger.info("seniority: {} old rule given days：{}", new Object[] { seniority, days });
		return days;
	}

	/**
	 * 計算2017新制的天數。
	 * 
	 * @param seniority
	 * @return
	 */
	public static int getNewANDaysBySeniority(int seniority, int months) {
		int days = 0;
		if (seniority < 1) {
			days = 0;
			if (months >= 6) {
				days = 3;
			}
		} else if (1 <= seniority && seniority < 2) {
			days = 7;
		} else if (2 <= seniority && seniority < 3) {
			days = 10;
		} else if (3 <= seniority && seniority < 5) {
			days = 14;
		} else if (5 <= seniority && seniority < 10) {
			days = 15;
		} else if (10 <= seniority && seniority < 25) {
			days = seniority + 6;
		} else {
			days = 30;
		}

		if (days >= 30) {
			days = 30;
		}
		logger.info("seniority {} new rule given days：{}", new Object[] { seniority, days });
		return days;
	}
}

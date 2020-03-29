package com.sunjet.front.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

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
	public static Map<String, Object> countNewAN(LocalDate onDate) {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		double totalANDays = 0;
		StringBuffer sb = new StringBuffer();
		LocalDate today = LocalDate.now();
		LocalDate curOnDate = onDate;
		logger.info("now:{}", today);
		logger.info("onDateTime:{}", onDate.atStartOfDay());
		// sb.append("計算起：" + onDate.toLocalDate());
		// sb.append("計算迄：" + today.toLocalDate());
		// LocalDate curOnDate = new LocalDate(onDate);
		// LocalDate curStartDate = new LocalDate(onDate);
		int countTimes = -1; // 年資
		// 計算年資
		while (curOnDate.isBefore(today) || curOnDate.isEqual(today)) {
			curOnDate = curOnDate.plusYears(1);
			countTimes++;
		}
		// curStartDate = curOnDate.minusYears(1);
		//
		// log.info("count date:{}", curOnDate);
		// sb.append("年資計算到：" + curOnDate.toLocalDate() +"(下一個到職日)");
		// log.info("on duty years:{}", countTimes);
		// sb.append("總年資(年)：" + countTimes);
		//
		// Days days = Days.daysBetween(onDate, today);
		// log.info("daysdiff:{}" , days.getDays());
		//
		Period p = Period.between(onDate, today);
		int monthDiff = p.getMonths();
		logger.info("monthsdiff:{}", monthDiff); // 不足一個月，給0
		// sb.append("總年資(月)：" + monthDiff);
		// sb.append("總年資(天)：" + days.getDays());
		// sb.append(BR_TEXT);
		//
		// // 如果計算的到職日在 2017年，代表是第一年處理
		// if( curOnDate.isAfter(new LocalDate(new Date(2016-1900 ,12-1,
		// 31))) && curOnDate.isBefore(new LocalDate(new Date(2018-1900
		// ,1-1, 1)))){
		// log.info("----2017 count way.");
		// sb.append("下一個到職計算日是 2017 年，需計算新舊制差異。");
		int plusDays = getDiffBetweenNewRule(countTimes, monthDiff);
		// sb.append("年資 "+countTimes + " 年。換算新舊制差異天數：" +plusDays +"天。");
		int oldDays = getANDaysBySeniority(countTimes);
		// sb.append("年資 "+countTimes +" 年。舊制給的天數"+ oldDays +"天。");
		// totalANDays = firstYearPeriod(curOnDate , oldDays , plusDays , sb);
		// curStartDate = new LocalDate(new Date(2017-1900 ,1-1, 1));
		// }else{
		// sb.append("下一個到職計算日是不是 2017 年，不需計算新舊制差異，直接用新制。");
		// log.info("----not 2017 count way.");
		totalANDays = countYearPeriod(countTimes, monthDiff);
		// sb.append("年資 "+countTimes +"新制給的天數(小時)"+ totalANDays);
		// }
		// log.info("final total AN hours:{} . start Date {} , end Date {} " ,
		// new Object[]{totalANDays , curStartDate , curOnDate });
		//
		//
		Map<String, Object> returnMap = new HashMap();
		returnMap.put("hours", totalANDays);
		// returnMap.put("countStartDate", curStartDate.toDate());
		// returnMap.put("countEndDate", curOnDate.toDate());
		// returnMap.put("processWord", sb.toString());

		return returnMap;
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
	 * 計算新舊制差異部分，第一年實施時，需在舊制的剩餘天數加上新制內容。
	 * 
	 * @param seniority
	 * @param months
	 * @return
	 */
	public static int getDiffBetweenNewRule(int seniority, int months) {
		int days = 0;
		if (seniority < 1) {
			days = 0;
			if (months >= 6) {
				days = 3;
			}
		} else if (2 <= seniority && seniority < 3) {
			days = 3;
		} else if (3 <= seniority && seniority < 5) {
			days = 4;
		} else if (5 <= seniority && seniority < 10) {
			days = 1;
		} else if (10 <= seniority) {
			days = 1;
		} else {

		}
		logger.info("seniority {} diff rule add days：{}", new Object[] { seniority, days });
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

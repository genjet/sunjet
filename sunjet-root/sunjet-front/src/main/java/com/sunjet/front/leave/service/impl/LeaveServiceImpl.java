package com.sunjet.front.leave.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunjet.common.dao.SjCalendarRepository;
import com.sunjet.common.dao.SjLeaveRepository;
import com.sunjet.common.dao.SjUserRepository;
import com.sunjet.common.entity.SjApply;
import com.sunjet.common.entity.SjCalendar;
import com.sunjet.common.entity.SjLeave;
import com.sunjet.common.entity.SjUser;
import com.sunjet.common.entity.enumeration.LeaveStatusEnum;
import com.sunjet.common.entity.enumeration.LeaveTypeEnum;
import com.sunjet.front.common.services.security.vo.UserInfo;
import com.sunjet.front.leave.service.LeaveService;
import com.sunjet.front.leave.vo.LeaveVO;
import com.sunjet.front.util.LeaveUtil;

@Service
public class LeaveServiceImpl implements LeaveService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SjLeaveRepository sjLeaveRepository;
	@Autowired
	private SjUserRepository sjUserRepository;
	@Autowired
	private SjCalendarRepository sjCalendarRepository;

	@Override
	public SjLeave createLeave(LeaveVO leaveVO) {
		SjLeave sjLeave = new SjLeave();
		SjUser sjUser = sjUserRepository.findByName(leaveVO.getUserName());
		sjLeave.setSjUser(sjUser);
		sjLeave.setDep(sjUser.getSjDep().getName());
		sjLeave.setStartDatetime(leaveVO.getStartDate());
		sjLeave.setEndDatetime(leaveVO.getEndDate());
		sjLeave.setLeaveType(leaveVO.getLeaveType());
		sjLeave.setLeaveStatus(LeaveStatusEnum.F);
		sjLeave.setLeaveHours(leaveVO.getLeaveHours());

		SjApply sjApply = new SjApply();
		sjApply.setName("假單");
		sjApply.setCode("APPLY_1");
		sjApply.setOrdinary(1);// TODO
		String flowKey = LocalDate.now().toString().concat(StringUtils.leftPad("1", 3, "0").concat(sjUser.getAccount()));// TODO
		sjApply.setFlowKey(flowKey);
		sjApply.setApplyStatus("F");// TODO
		sjApply.setApplyUser(sjUser.getName());

		sjLeave.setSjApply(sjApply);
		sjLeaveRepository.save(sjLeave);
		return sjLeave;
	}

	@Override
	public Map<String, Integer> countLeaveaDays(LocalDateTime startDate, LocalDateTime endDate) {
		List<SjCalendar> sjCalendarList = sjCalendarRepository.findByCalendarDateBetween(startDate.toLocalDate(), endDate.toLocalDate());
		Map<String, Integer> rtnMap = new HashMap<String, Integer>();
		Integer holidDay = 0;
		Integer totalDay = -1;
		Integer countHours = 0;
		LocalDateTime culStartDate = startDate.plusDays(-1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		if (CollectionUtils.isNotEmpty(sjCalendarList)) {
			logger.debug(" =======  Calendar size is : {}  ======= ", sjCalendarList.size());
			if (1 == sjCalendarList.size()) {
				Duration duration = Duration.between(startDate, endDate);
				Long hour = duration.toMinutes();
				countHours = hour.intValue();
				LocalDateTime middenEndDate = LocalDateTime.parse(endDate.toLocalDate().toString() + " 12:00", formatter);
				if (endDate.isAfter(middenEndDate)) {
					countHours = countHours - 60;
				}
			} else {
				for (SjCalendar sjCalendar : sjCalendarList) {
					// if(culStartDate.toLocalDate().isBefore(sjCalendar.getCalendarDate())){
					if (sjCalendar.getIsHoliday()) {
						if (startDate.toLocalDate().isEqual(sjCalendar.getCalendarDate())) {
							culStartDate = LocalDateTime.parse(culStartDate.toLocalDate().toString() + " 08:00", formatter);
						}
						holidDay++;
					} else {
						totalDay++;
					}
					culStartDate = culStartDate.plusDays(1);
					// }
				}

			}
		}
		if (0 != totalDay || 0 != holidDay) {
			logger.debug(" ======= culStartDate is : {} ========= ", culStartDate);
			LocalDateTime middenEndDate = LocalDateTime.parse(endDate.toLocalDate().toString() + " 12:00", formatter);
			if (culStartDate.isAfter(middenEndDate)) {
				countHours = countHours + 60;
			}
			if (endDate.isAfter(middenEndDate)) {
				countHours = countHours - 60;
			}
			logger.debug(" ======= countHours1 is : {} ========= ", countHours);
			Duration duration2 = null;
			if (culStartDate.isBefore(endDate)) {
				duration2 = Duration.between(culStartDate, endDate);
			} else {
				duration2 = Duration.between(endDate, culStartDate);
			}
			Long totalHours = countHours + duration2.toMinutes();
			logger.debug(" ======= countHours2 is : {} ========= ", countHours);
			countHours = totalHours.intValue();

			if (countHours == 480) {
				countHours = 0;
				totalDay++;
				// // countHours = countHours - 480;
				logger.debug(" ======= countHours3 is : {} ========= ", countHours);
			}
		}
		countHours = (8 * 60 * totalDay + countHours) / 60;
		rtnMap.put("day", totalDay);
		rtnMap.put("hour", countHours);
		return rtnMap;
	}

	@Override
	public double countLeaveableDays(UserInfo userInfo, LeaveTypeEnum leaveType) {
		double rtnHours = 0;
		int totalHours = 0;
		SjUser sjUser = sjUserRepository.findByName(userInfo.getName());
		List<SjLeave> leaves = sjLeaveRepository.findBySjUserAndLeaveTypeAndExpire(sjUser, leaveType, Boolean.FALSE);
		if (CollectionUtils.isNotEmpty(leaves)) {
			for (SjLeave sjLeave : leaves) {
				if (!LeaveStatusEnum.R.equals(sjLeave.getLeaveStatus())) {
					totalHours += sjLeave.getLeaveHours();
				}
			}
		}
		LocalDate offDate = sjUser.getOffdate();
		switch (leaveType) {
		case SPECIAL:
			double days = LeaveUtil.countNewAN(offDate);
			// int iii = (new BigDecimal(days).divide(new BigDecimal(8), 0,
			// BigDecimal.ROUND_UP)).intValue();
			rtnHours = days - totalHours;
			break;

		default:
			break;
		}
		return rtnHours;
	}

}

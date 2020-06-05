package com.sunjet.front.management.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sunjet.common.dao.SjDepRepository;
import com.sunjet.common.dao.SjUserRepository;
import com.sunjet.common.entity.SjDep;
import com.sunjet.common.entity.SjUser;
import com.sunjet.front.management.service.ManagementService;
import com.sunjet.front.management.vo.DepVO;
import com.sunjet.front.management.vo.UserVO;
import com.sunjet.front.util.ToolUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ManagementServiceImpl implements ManagementService {
	@Autowired
	private SjUserRepository sjUserRepository;
	@Autowired
	private SjDepRepository sjDepRepository;

	@Override
	public List<UserVO> getAllUserVo() {
		List<SjUser> sjUsers = sjUserRepository.findAll();
		List<UserVO> rtnVOs = new ArrayList<UserVO>();
		// String id = 1;
		for (SjUser sjUser : sjUsers) {
			UserVO user = new UserVO();
			user.setOid(sjUser.getOid());
			user.setEmpId(sjUser.getEmpId());
			user.setAccount(sjUser.getAccount());
			user.setName(sjUser.getName());
			user.setAvatar("Sorceress-Witch");
			user.setPwd(null == sjUser.getPwd() ? "" : sjUser.getPwd());
			user.setDep(null != sjUser.getSjDep() ? sjUser.getSjDep().getName() : "");
			user.setNickName(null != sjUser.getNickName() ? sjUser.getNickName() : "");
			user.setEnabled(null == sjUser.getEnabled() ? Boolean.TRUE : sjUser.getEnabled());
			user.setArrivalDay(null == sjUser.getArrivalDay() ? LocalDate.now() : sjUser.getArrivalDay());
			// id++;
			rtnVOs.add(user);
		}
		return rtnVOs;

	}

	@Override
	public List<DepVO> getAllDepVO() {
		List<DepVO> rtnVOs = new ArrayList<DepVO>();
		List<SjDep> deps = sjDepRepository.findAll();
		if(!CollectionUtils.isEmpty(deps)){
			for (SjDep sjDep : deps) {
				DepVO depVO = new DepVO();
				depVO.setKey(sjDep.getOid());
				depVO.setValue(sjDep.getName());
				rtnVOs.add(depVO);
			}
		}
		return rtnVOs;
	}

	@Override
	public UserVO addUser(UserVO userVO) {
		UserVO rtnUserVO = userVO;
		SjUser topSjUser = sjUserRepository.findTopByOrderByEmpIdDesc();
		String empId = ToolUtil.addOne4Str(topSjUser.getEmpId());
		userVO.setEmpId(empId);
		log.info("new empId is {}", empId);
		
		SjUser user = new SjUser();		
		user.setEmpId(userVO.getEmpId());
		user.setAccount(userVO.getAccount());
		user.setName(userVO.getName());
		user.setPwd(userVO.getPwd());
		user.setEnabled(Boolean.TRUE);
		user.setNickName(userVO.getNickName());
		Optional<SjDep> sjDep = sjDepRepository.findById(userVO.getDep());
		if (sjDep.isPresent()) {
			user.setSjDep(sjDep.get());
			rtnUserVO.setDep(sjDep.get().getName());
		}
		user.setArrivalDay(userVO.getArrivalDay());
		sjUserRepository.save(user);
		return rtnUserVO;
	}

	@Override
	public UserVO updateUser(UserVO userVO) {
		UserVO rtnUserVO = userVO;
		Optional<SjUser> sjUserOpt = sjUserRepository.findById(userVO.getEmpId());
		if (sjUserOpt.isPresent()) {
			SjUser user = sjUserOpt.get();
			user.setAccount(userVO.getAccount());
			user.setName(userVO.getName());
			user.setPwd(userVO.getPwd());
			user.setEnabled(userVO.getEnabled());
			user.setNickName(userVO.getNickName());
			Optional<SjDep> sjDep = sjDepRepository.findById(userVO.getDep());
			if (sjDep.isPresent()) {
				user.setSjDep(sjDep.get());
				rtnUserVO.setDep(sjDep.get().getName());
			}
			user.setArrivalDay(userVO.getArrivalDay());
			sjUserRepository.save(user);
		}
		return rtnUserVO;
	}

	@Override
	public UserVO deleteUser(String oid) {
		UserVO rtnUserVO = new UserVO();
		Optional<SjUser> userOpt = sjUserRepository.findById(oid);
		if (userOpt.isPresent()) {
			SjUser sjUser = userOpt.get();
			rtnUserVO.setName(sjUser.getName());
			sjUserRepository.delete(sjUser);
		}
		return rtnUserVO;
	}

}

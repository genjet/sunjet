package com.sunjet.front.management.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunjet.common.dao.SjDepRepository;
import com.sunjet.common.dao.SjUserRepository;
import com.sunjet.common.entity.SjDep;
import com.sunjet.common.entity.SjUser;
import com.sunjet.front.common.services.security.UserDetailsImpl;
import com.sunjet.front.management.vo.DepVO;
import com.sunjet.front.management.vo.UserVO;
import com.sunjet.front.payload.response.ResponseObj;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/management")
public class ManagementController {
	@Autowired
	private SjUserRepository sjUserRepository;
	@Autowired
	private SjDepRepository sjDepRepository;

	@GetMapping("/user")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> getUsers() {
		UserDetailsImpl userInfo = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("======================> "+userInfo.getAccount());
		List<UserVO> rtnVOs = getUserVo();
		ResponseObj rspObj = new ResponseObj();
		rspObj.setData(rtnVOs);
		return ResponseEntity.ok(rspObj);
	}
	
	@GetMapping("/deps")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> getDeps() {
		List<DepVO> rtnVOs = new ArrayList<DepVO>();
		List<SjDep> deps = sjDepRepository.findAll();
		if(!CollectionUtils.isEmpty(deps)){
			for (SjDep sjDep : deps) {
				DepVO depVO = new DepVO();
				depVO.setKey(sjDep.getOid());
				depVO.setDisplayName(sjDep.getName());
				rtnVOs.add(depVO);
			}
		}
		ResponseObj rspObj = new ResponseObj();
		rspObj.setData(rtnVOs);
		return ResponseEntity.ok(rspObj);
	}

	private List<UserVO> getUserVo() {
		List<SjUser> sjUsers = sjUserRepository.findAll();
		List<UserVO> rtnVOs = new ArrayList<UserVO>();
//		String id = 1;
		for (SjUser sjUser : sjUsers) {
			UserVO user = new UserVO();
			user.setId(sjUser.getOid());
			user.setAccount(sjUser.getAccount());
			user.setName(sjUser.getName());
			user.setAvatar("Sorceress-Witch");
			user.setPwd(null == sjUser.getPwd()? "": sjUser.getPwd());
			user.setDep(null != sjUser.getSjDep() ? sjUser.getSjDep().getName() : "");
			user.setNickName(null != sjUser.getNickName() ? sjUser.getNickName() : "");
			user.setEnabled(null == sjUser.getEnabled()? Boolean.TRUE: sjUser.getEnabled());
			user.setArrivalDay(null == sjUser.getArrivalDay()? LocalDate.now() : sjUser.getArrivalDay());
//			id++;
			rtnVOs.add(user);
		}
		return rtnVOs;
	}

	
	@PostMapping("/user")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addUser(@RequestBody UserVO userVO) {
		ResponseObj rspObj = new ResponseObj();
		SjUser user = new SjUser();
		user.setAccount(userVO.getAccount());
		user.setName(userVO.getName());
		user.setPwd(userVO.getPwd());
		user.setEnabled(Boolean.TRUE);
		Optional<SjDep> sjDep = sjDepRepository.findById(userVO.getDep());
		if(sjDep.isPresent()){
			user.setSjDep(sjDep.get());
		}
		user.setArrivalDay(userVO.getArrivalDay());
		sjUserRepository.save(user);
		rspObj.setData(userVO);
		return ResponseEntity.ok(rspObj);
	}
	
	@PatchMapping("/user")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateUser(@RequestBody UserVO userVO) {
		ResponseObj rspObj = new ResponseObj();
		Optional<SjUser> sjUserOpt = sjUserRepository.findById(userVO.getId());
		if(sjUserOpt.isPresent()){
			SjUser user = sjUserOpt.get();
			user.setAccount(userVO.getAccount());
			user.setName(userVO.getName());
			user.setPwd(userVO.getPwd());
			user.setEnabled(userVO.getEnabled());
			Optional<SjDep> sjDep = sjDepRepository.findById(userVO.getDep());
			if(sjDep.isPresent()){
				user.setSjDep(sjDep.get());
			}
			user.setArrivalDay(userVO.getArrivalDay());
			sjUserRepository.save(user);
		}
		rspObj.setData(userVO);
		return ResponseEntity.ok(rspObj);
	}
	
	@DeleteMapping("/user/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteUser(@PathVariable String id) {
		ResponseObj rspObj = new ResponseObj();
		Optional<SjUser> todoOpt = sjUserRepository.findById(id);
		if(todoOpt.isPresent()){
			sjUserRepository.delete(todoOpt.get());
		}
		return ResponseEntity.ok(rspObj);
	}

	@GetMapping("/signup")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String showSignUpForm(UserVO userVO) {
		return "management/management";
	}

	@PostMapping("/add")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String addStudent(@Valid UserVO userVO, BindingResult result, Model model) {
		// String[] s = new String[]{"error1"};
		// String[] s1 = new String[]{"name"};
		// ObjectError error = new ObjectError("student", s, s1, "喔喔喔喔");
		// result.addError(error);
		result.rejectValue("name", "error.student", "喔喔喔喔");
		if (result.hasErrors()) {
			return "management/management";
		}

		// studentRepository.save(student);
		return "redirect:list";
	}
}

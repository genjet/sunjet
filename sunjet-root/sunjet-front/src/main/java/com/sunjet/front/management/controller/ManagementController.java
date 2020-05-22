package com.sunjet.front.management.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunjet.common.dao.SjUserRepository;
import com.sunjet.common.entity.SjUser;
import com.sunjet.front.management.vo.UserVO;
import com.sunjet.front.payload.response.ResponseObj;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/management")
public class ManagementController {
	@Autowired
	private SjUserRepository sjUserRepository;

	@GetMapping("/user")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> getUsers() {
		List<UserVO> rtnVOs = getUserVo();
		ResponseObj rspObj = new ResponseObj();
		rspObj.setData(rtnVOs);
		return ResponseEntity.ok(rspObj);
	}

	private List<UserVO> getUserVo() {
		List<SjUser> sjUsers = sjUserRepository.findAll();
		List<UserVO> rtnVOs = new ArrayList<UserVO>();
		int id = 1;
		for (SjUser sjUser : sjUsers) {
			UserVO user = new UserVO();
			user.setId(id);
			user.setAccount(sjUser.getAccount());
			user.setName(sjUser.getName());
			user.setAvatar("Sorceress-Witch");
			user.setPsw("df123r");
			user.setDep("文化部");
			user.setNickName("ggyy");
			user.setEnabled(Boolean.TRUE);
			user.setArrivalDay(LocalDate.now());
			id++;
			rtnVOs.add(user);
		}
		return rtnVOs;
	}

	@PostMapping("/adduser")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String addUser(@Valid UserVO userVO, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("userVOs", getUserVo());
			return "management/management";
		}
		SjUser user = new SjUser();
		user.setAccount(userVO.getAccount());
		user.setName(userVO.getName());
		user.setPwd(userVO.getPsw());
		sjUserRepository.save(user);
//		model.addAttribute("users", getUserVo());
		return "redirect:management";
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

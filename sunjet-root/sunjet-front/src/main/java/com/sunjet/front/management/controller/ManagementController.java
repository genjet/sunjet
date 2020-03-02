package com.sunjet.front.management.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sunjet.common.dao.SjUserRepository;
import com.sunjet.common.entity.SjUser;
import com.sunjet.front.management.vo.UserVO;

@Controller
public class ManagementController {
	@Autowired
	private SjUserRepository sjUserRepository;

	@GetMapping("/management")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String management(UserVO userVO, Model model) {
		List<UserVO> rtnVOs = getUserVo();
		model.addAttribute("userVOs", rtnVOs);
		return "management/management";
	}

	private List<UserVO> getUserVo() {
		List<SjUser> sjUsers = sjUserRepository.findAll();
		List<UserVO> rtnVOs = new ArrayList<UserVO>();
		for (SjUser sjUser : sjUsers) {
			UserVO user = new UserVO();
			user.setAccount(sjUser.getAccount());
			user.setName(sjUser.getName());
			rtnVOs.add(user);
		}
		return rtnVOs;
	}

	@PostMapping("/adduser")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String addUser(@Valid UserVO userVO, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "management/management";
		}
		SjUser user = new SjUser();
		user.setAccount(userVO.getAccount());
		user.setName(userVO.getName());
		user.setPwd(userVO.getPsw());
		sjUserRepository.save(user);
		model.addAttribute("users", getUserVo());
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

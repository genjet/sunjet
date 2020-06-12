package com.sunjet.front.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunjet.front.common.payload.response.ApiResponse;
import com.sunjet.front.management.service.ManagementService;
import com.sunjet.front.management.vo.DepVO;
import com.sunjet.front.management.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/management")
@Slf4j
public class ManagementController {

	@Autowired
	private ManagementService managementService;

	@GetMapping("/user")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ApiResponse getUsers() {
		// UserDetailsImpl userInfo = (UserDetailsImpl)
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			List<UserVO> rtnVOs = managementService.getAllUserVo();
			return ApiResponse.ok("", rtnVOs);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}

	@GetMapping("/deps")
//	@PreAuthorize("hasAnyRole('ADMIN')")
	public ApiResponse getDeps() {
		try {
			List<DepVO> rtnVOs = managementService.getAllDepVO();
			return ApiResponse.ok("", rtnVOs);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}

	@PostMapping("/user")
	@PreAuthorize("hasRole('ADMIN')")
	public ApiResponse addUser(@RequestBody UserVO userVO) {
		try {
			UserVO rtnVO = managementService.addUser(userVO);
			return ApiResponse.ok("", rtnVO);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}

	@PatchMapping("/user")
	@PreAuthorize("hasRole('ADMIN')")
	public ApiResponse updateUser(@RequestBody UserVO userVO) {
		try {
			UserVO rtnVO = managementService.updateUser(userVO);
			return ApiResponse.ok("", rtnVO);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}

	@DeleteMapping("/user/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ApiResponse deleteUser(@PathVariable String id) {
		try {
			UserVO rtnVO = managementService.deleteUser(id);
			return ApiResponse.ok(rtnVO.getName() + " delete sucess! ");
		} catch (Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
		
	
	}

}

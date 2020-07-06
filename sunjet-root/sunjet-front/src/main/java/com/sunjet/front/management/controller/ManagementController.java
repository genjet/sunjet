package com.sunjet.front.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
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
import com.sunjet.front.common.vo.OptionVo;
import com.sunjet.front.management.service.ManagementService;
import com.sunjet.front.management.vo.RoleVo;
import com.sunjet.front.management.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/management")
@Slf4j
public class ManagementController {

	@Autowired
	private ManagementService managementService;

	@GetMapping("/user")
	public ApiResponse getUsers() {
		try {
			List<UserVo> rtnVOs = managementService.getAllUserVo();
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
			List<OptionVo> rtnVOs = managementService.getDepOptionVos();
			return ApiResponse.ok("", rtnVOs);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}

	@PostMapping("/user")
	public ApiResponse addUser(@RequestBody UserVo userVO) {
		try {
			UserVo rtnVO = managementService.addUser(userVO);
			return ApiResponse.ok("", rtnVO);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}

	@PatchMapping("/user")
	public ApiResponse updateUser(@RequestBody UserVo userVO) {
		try {
			UserVo rtnVO = managementService.updateUser(userVO);
			return ApiResponse.ok("", rtnVO);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}

	@DeleteMapping("/user/{id}")
//	@PreAuthorize("hasRole('ADMIN')")
	public ApiResponse deleteUser(@PathVariable String id) {
		try {
			UserVo rtnVO = managementService.deleteUser(id);
			return ApiResponse.ok(rtnVO.getName() + " delete sucess! ");
		} catch (Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}
	
	@GetMapping("/role")
	public ApiResponse getRoles() {
		try {
			List<RoleVo> rtnVOs = managementService.getAllRoleVo();
			return ApiResponse.ok("", rtnVOs);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}
	
	@PostMapping("/role")
	public ApiResponse addRole(@RequestBody RoleVo RoleVo) {
		try {
			RoleVo rtnVO = managementService.addRole(RoleVo);
			return ApiResponse.ok("", rtnVO);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}
	
	@PatchMapping("/role")
	public ApiResponse updateRole(@RequestBody @Validated RoleVo roleVo, BindingResult result) {
		try {
			if(result.hasErrors()){
				StringBuffer sb = new StringBuffer();;
				for (FieldError fieldError : result.getFieldErrors()) {
					sb.append(fieldError.getDefaultMessage());
	            }
				return ApiResponse.fail(sb.toString());
			}

			RoleVo rtnVO = managementService.updateRole(roleVo);
			return ApiResponse.ok("", rtnVO);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}
	
	@GetMapping("/roleOptions")
	public ApiResponse getRoleOptions() {
		try {
			List<OptionVo> rtnVos = managementService.getRoleOptionVos();
			return ApiResponse.ok("", rtnVos);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}

	
}

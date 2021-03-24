package com.sunjet.front.management.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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

import com.sunjet.front.common.exception.ValidateErrorException;
import com.sunjet.front.common.payload.response.ApiResponse;
import com.sunjet.front.common.vo.FailureVo;
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

	@GetMapping("/test")
	public ApiResponse getTest(@RequestBody UserVo userVO) {
		try {
			log.info("===============  開始執行　test　　測試中文　================== {} ", userVO.getName());
//			final List<UserVo> rtnVOs = managementService.getAllUserVo();
			log.info("===============  結束執行　test　　測試中文　==================");
			return ApiResponse.ok("好好好");
		} catch (final Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}

	@GetMapping("/user")
	public ApiResponse getUsers() {
		try {
			log.info("===============  開始執行　ｕｓｅｒ　　測試中文　==================");
			log.info("我file.encoding is : " + System.getProperty("file.encoding"));
		        final String defaultCharacterEncoding = System.getProperty("file.encoding");
		        System.out.println("要defaultCharacterEncoding by property: " + defaultCharacterEncoding);
		        System.out.println("測defaultCharacterEncoding by code: " + getDefaultCharEncoding());
		        System.out.println("是defaultCharacterEncoding by charSet: " + Charset.defaultCharset());

//		        System.setProperty("file.encoding", "UTF-16");

		        System.out.println("文defaultCharacterEncoding by property after updating file.encoding : " + System.getProperty("file.encoding"));

		        System.out.println("自defaultCharacterEncoding by code after updating file.encoding : " + getDefaultCharEncoding());

		        System.out.println("拉defaultCharacterEncoding by java.nio.Charset after updating file.encoding : " + Charset.defaultCharset());
			final List<UserVo> rtnVOs = managementService.getAllUserVo();
			log.info("===============  結束執行　ｕｓｅｒ　　測試中文　================== {}", rtnVOs.stream().map(UserVo::getName).collect(Collectors.joining(", ")).toString());
			return ApiResponse.ok("", rtnVOs);
		} catch (final Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}
	public String getDefaultCharEncoding() throws IOException{
//         Charset charset = Charset.forName("UTF-8");
		final String str = "王八蛋";
        final InputStream is = new ByteArrayInputStream(str.getBytes(Charset.forName("UTF-8")));
        final InputStreamReader reader = new InputStreamReader(is, "utf-8");
    	final BufferedReader br = new BufferedReader(reader);
    	final StringBuffer content = new StringBuffer();
		int i;
		while ((i = br.read()) != -1) {
			content.append((char) i);
		}
		log.info("HttpEntiyInfo:\n{}", content.toString());
        final String defaultCharacterEncoding = reader.getEncoding();
        return defaultCharacterEncoding;
    }


	@GetMapping("/deps")
	// @PreAuthorize("hasAnyRole('ADMIN')")
	public ApiResponse getDeps() {
		try {
			final List<OptionVo> rtnVOs = managementService.getDepOptionVos();
			return ApiResponse.ok("", rtnVOs);
		} catch (final Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}

	@PostMapping("/user")
	public ApiResponse addUser(@RequestBody UserVo userVO) {
		try {
			final UserVo rtnVO = managementService.addUser(userVO);
			return ApiResponse.ok("", rtnVO);
		} catch (final Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}

	@PatchMapping("/user")
	public ApiResponse updateUser(@RequestBody UserVo userVO) {
		try {
			final UserVo rtnVO = managementService.updateUser(userVO);
			return ApiResponse.ok("", rtnVO);
		} catch (final Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}

	@DeleteMapping("/user/{id}")
	public ApiResponse deleteUser(@PathVariable String id) {
		try {
			final UserVo rtnVO = managementService.deleteUser(id);
			return ApiResponse.ok(rtnVO.getName() + " delete sucess! ");
		} catch (final Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}

	@GetMapping("/role")
	public ApiResponse getRoles() {
		try {
			final List<RoleVo> rtnVOs = managementService.getAllRoleVo();
			return ApiResponse.ok("", rtnVOs);
		} catch (final Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}

	@PostMapping("/role")
	public ApiResponse addRole(@RequestBody RoleVo RoleVo, BindingResult result) {
		try {
			if (result.hasErrors()) {
				final String errStr = result.getFieldErrors().stream().map(it -> it.getDefaultMessage())
						.collect(Collectors.joining(","));
				throw new ValidateErrorException(new FailureVo(errStr));
			}
			final RoleVo rtnVO = managementService.addRole(RoleVo);
			return ApiResponse.ok("", rtnVO);
		} catch (final ValidateErrorException e) {
			log.error(e.getFailure().getMessage());
			return ApiResponse.fail(e.getFailure().getMessage());
		} catch (final Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}

	@PatchMapping("/role")
	public ApiResponse updateRole(@RequestBody @Validated RoleVo roleVo, BindingResult result) {
		try {
			if (result.hasErrors()) {
				final String errStr = result.getFieldErrors().stream().map(it -> it.getDefaultMessage())
						.collect(Collectors.joining(","));
				throw new ValidateErrorException(new FailureVo(errStr));
			}

			final RoleVo rtnVO = managementService.updateRole(roleVo);
			return ApiResponse.ok("", rtnVO);
		} catch (final ValidateErrorException e) {
			log.error(e.getFailure().getMessage());
			return ApiResponse.fail(e.getFailure().getMessage());
		} catch (final Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}

	@GetMapping("/roleOptions")
	public ApiResponse getRoleOptions() {
		try {
			final List<OptionVo> rtnVos = managementService.getRoleOptionVos();
			return ApiResponse.ok("", rtnVos);
		} catch (final Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}

	@DeleteMapping("/role/{id}")
	public ApiResponse deleteRole(@PathVariable String id) {
		try {
			final RoleVo rtnVO = managementService.deleteRole(id);
			return ApiResponse.ok(rtnVO.getRoleName() + " delete sucess! ");
		} catch (final ValidateErrorException e) {
			log.error(e.getFailure().getMessage());
			return ApiResponse.fail(e.getFailure().getMessage());
		} catch (final Exception e) {
			log.error(e.getMessage());
			return ApiResponse.fail(e.getMessage());
		}
	}

}

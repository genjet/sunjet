package com.sunjet.front.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunjet.common.dao.SjUserRepository;
import com.sunjet.common.entity.SjUser;
import com.sunjet.common.entity.enumeration.LeaveStatusEnum;
import com.sunjet.common.entity.enumeration.LeaveTypeEnum;
import com.sunjet.front.common.payload.request.LoginRequest;
import com.sunjet.front.common.payload.response.ApiResponse;
import com.sunjet.front.common.payload.response.JwtResponse;
import com.sunjet.front.common.security.jwt.JwtUtils;
import com.sunjet.front.common.security.vo.SecurityUserDetails;
import com.sunjet.front.common.services.CommonService;
import com.sunjet.front.common.vo.OptionVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class CommonController {
	private static final Enum FlowStatusEnum = null;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	SjUserRepository userRepository;
	
	@Autowired
	CommonService CommonService;

	// @Autowired
	// RoleRepository roleRepository;

	// @Autowired
	// PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;


	@Autowired
	private SessionRegistry sessionRegistry;

	@GetMapping("/loggedUsers")
	public ResponseEntity<?> getLoggedUsers() {
		return ResponseEntity.ok(sessionRegistry.getAllPrincipals().stream()
				.filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty()).map(Object::toString)
				.collect(Collectors.toList()));
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		log.info("=================================  CommonController.authenticateUser ======================================================");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("authorities", roles);
		claims.put("name", userDetails.getUsername());
		String jwt = jwtUtils.generateJwtToken(userDetails, claims);
		log.info("=================  token {} =========", jwt);
		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getAccount(), roles));
	}
	
//	@PostMapping("/login")
//	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
//		log.info("login = >>>>>>>>>>>>>>---------------------->> {}");
//		return ResponseEntity.ok(new ApiResponse());
//	}

//	@PostMapping("/logout")
//	public ResponseEntity<?> logout(String token) {
//		log.info("logout = >>>>>>>>>>>>>>>> {}", token);
//		return ResponseEntity.ok(new ApiResponse());
//	}

	@GetMapping("/info")
	public ResponseEntity<?> allAccess(String token) {
		String account = jwtUtils.getAccountFromJwtToken(token);
		SjUser sjUser = userRepository.findByAccount(account);
		if (null != sjUser) {
			List<String> roles = new ArrayList<>();
			sjUser.getSjUserRoleRels().stream().forEach(r -> roles.add(r.getSjRole().getRoleCode()));
			JwtResponse jj = new JwtResponse(token, sjUser.getOid(), sjUser.getName(), sjUser.getAccount(), roles);
			jj.setAvatar(sjUser.getAvatar());

			return ResponseEntity.ok(jj);
		} else {
			return ResponseEntity.ok(null);
		}
	}

	@GetMapping("/getLeaveType")
	public ResponseEntity<?> getLeaveType() {
		List<OptionVo> optionList = new ArrayList<OptionVo>();

		for (LeaveTypeEnum type : LeaveTypeEnum.values()) {
			OptionVo bean = new OptionVo(type.name(), type.getValue());
			optionList.add(bean);
		}
		ApiResponse rspObj = new ApiResponse();
		rspObj.setData(optionList);
		return ResponseEntity.ok(rspObj);
	}

	@GetMapping("/getAllAuthoritys")
	public ResponseEntity<?> getAllAuthoritys() {
		ApiResponse rspObj = new ApiResponse();
		List<OptionVo> parentOptions = CommonService.getAllAuthorityOptionVos();
		rspObj.setData(parentOptions);
		return ResponseEntity.ok(rspObj);
	}

	@GetMapping("/getAllOptions")
	public ResponseEntity<?> getAllOptions() {
		Map<String, List<OptionVo>> map = new HashMap<>();
		

//		List<OptionVO> optionList = new ArrayList<OptionVO>();
//		for (LeaveTypeEnum type : LeaveTypeEnum.values()) {
//			OptionVO bean = new OptionVO(type.getCode(), type.getValue());
//			optionList.add(bean);
//		}
		List<OptionVo> optionList = Stream.of(LeaveTypeEnum.values()).map(it -> {
			return new OptionVo(it.getValue(), it.name());
		}).collect(Collectors.toList());
		map.put("leaveType", optionList);
		
		
//		List<Enum[]> ll =  new ArrayList<>();
//		ll.add(LeaveStatusEnum.values());
//		for (Enum object : ll) {
//			List<OptionVO> leaveStatusList = Stream.of(object).map(it -> {
//				return new OptionVO(it.name(), it.getValue());
//			}).collect(Collectors.toList());
////			map.put(LeaveStatusEnum.class.getSimpleName().replace("Enum", ""), leaveStatusList);
//		}
		
		List<OptionVo> leaveStatusList = Stream.of(LeaveStatusEnum.values()).map(it -> {
			return new OptionVo(it.name(), it.getValue());
		}).collect(Collectors.toList());
		map.put(LeaveStatusEnum.class.getSimpleName().replace("Enum", ""), leaveStatusList);

		
		ApiResponse rspObj = new ApiResponse();
		rspObj.setData(map);
		return ResponseEntity.ok(rspObj);
	}

}

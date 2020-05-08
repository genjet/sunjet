package com.sunjet.front.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import com.sunjet.front.common.services.security.UserDetailsImpl;
import com.sunjet.front.common.services.security.jwt.JwtUtils;
import com.sunjet.front.common.services.security.vo.ActiveUserStore;
import com.sunjet.front.payload.request.LoginRequest;
import com.sunjet.front.payload.response.JwtResponse;
import com.sunjet.front.payload.response.ResponseObj;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class CommonController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	SjUserRepository userRepository;

	// @Autowired
	// RoleRepository roleRepository;

	// @Autowired
	// PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	ActiveUserStore activeUserStore;
	
	@Autowired
	private SessionRegistry sessionRegistry;

	@GetMapping("/loggedUsers")
	public ResponseEntity<?>  getLoggedUsers() {
		return ResponseEntity.ok(sessionRegistry.getAllPrincipals().stream()
			      .filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty())
			      .map(Object::toString)
			      .collect(Collectors.toList()));
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("authorities", roles);
		claims.put("name", userDetails.getUsername());
		String jwt = jwtUtils.generateJwtToken(userDetails, claims);
		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getAccount(), roles));
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(String token) {

		return ResponseEntity.ok(new ResponseObj());
	}

	@GetMapping("/info")
	public ResponseEntity<?> allAccess(String token) {
		String userName = jwtUtils.getUserNameFromJwtToken(token);
		SjUser sjUser = userRepository.findByAccount(userName);
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

}

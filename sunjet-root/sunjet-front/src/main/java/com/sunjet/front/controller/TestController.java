package com.sunjet.front.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunjet.front.common.payload.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TestController {

	
	@PostMapping("/logout")
	public ResponseEntity<?> logout() {
		log.info("logout = >>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>> ");
		return ResponseEntity.ok(new ApiResponse());
	}
}

package com.sunjet.front.model;

import java.util.List;

import com.sunjet.front.common.services.security.UserDetailsImpl;

import lombok.Data;

@Data
public class AjaxResponseBody {
	String msg;

	String code;

	List<UserDetailsImpl> result;


}

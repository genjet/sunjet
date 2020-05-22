package com.sunjet.front.management.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserVO {

	private long id;

	// @NotBlank(message = "Account is not be blank")
	// @Size(min = 2, max = 10)
	private String account;

	// @NotBlank(message = "Name is not be blank")
	// @Size(min=2, max=10)
	private String name;

	// @NotBlank(message = "Password is not be blank")
	// @Pattern(regexp = "^[0-9][0-9]{2}-[0-9]{2}-[0-9]{4}$", message="SSN must
	// use numbers in this format: XXX-YY-ZZZZ")
	private String psw;
	
	private String nickName;
	
	private String avatar;
	
	private Boolean  enabled;
	
	private String dep;

	private LocalDate arrivalDay;
	// private long phoneNo;

	

}
package com.sunjet.front.management.vo;

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

	// private long phoneNo;

	public UserVO() {
	}

	public UserVO(String name, String psw) {
		this.name = name;
		// this.psw = psw;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getName() {
		return name;
	}

	public String getPsw() {
		return psw;
	}
	//
	// public long getPhoneNo() {
	// return phoneNo;
	// }
	//
	// public void setPhoneNo(long phoneNo) {
	// this.phoneNo = phoneNo;
	// }
}
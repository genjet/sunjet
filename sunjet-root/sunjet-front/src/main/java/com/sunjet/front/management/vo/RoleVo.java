package com.sunjet.front.management.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
//@AllArgsConstructor
public class RoleVo {
	private String oid;
	@NotBlank(message="角色代號不可為空值!")
	private String roleCode;
	private String roleName;
	private List<String> authority = new ArrayList<>();
	private List<AuthorityVo> authorityVo = new ArrayList<>();

}

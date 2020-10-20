package com.sunjet.front.management.service;

import java.util.List;

import com.sunjet.front.common.vo.OptionVo;
import com.sunjet.front.management.vo.DepVo;
import com.sunjet.front.management.vo.RoleVo;
import com.sunjet.front.management.vo.UserVo;

public interface ManagementService {
	public List<UserVo> getAllUserVo();

	public List<DepVo> getAllDepVO();
	
	public List<OptionVo> getDepOptionVos();

	public UserVo addUser(UserVo userVO);

	public UserVo updateUser(UserVo userVO);

	public UserVo deleteUser(String id);
	
	public List<RoleVo> getAllRoleVo();
	
	public RoleVo addRole(RoleVo roleVO);
	
	public RoleVo updateRole(RoleVo roleVO);

	public List<OptionVo> getRoleOptionVos();
	
	public RoleVo deleteRole(String id);
	
}

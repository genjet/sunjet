package com.sunjet.front.management.service;

import java.util.List;

import com.sunjet.front.management.vo.DepVO;
import com.sunjet.front.management.vo.UserVO;

public interface ManagementService {
	public List<UserVO> getAllUserVo();

	public List<DepVO> getAllDepVO();

	public UserVO addUser(UserVO userVO);

	public UserVO updateUser(UserVO userVO);

	public UserVO deleteUser(String id);
}

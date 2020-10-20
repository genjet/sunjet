package com.sunjet.front.common.services;

import java.util.List;
import java.util.Map;

import com.sunjet.common.entity.SjAuthority;
import com.sunjet.front.common.vo.OptionVo;

public interface CommonService {
	Map<SjAuthority, List<SjAuthority>> getAllAuthoritys();
	List<OptionVo> getAllAuthorityOptionVos();
}

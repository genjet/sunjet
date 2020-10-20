package com.sunjet.front.common.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunjet.common.dao.SjAuthorityRepository;
import com.sunjet.common.entity.SjAuthority;
import com.sunjet.front.common.services.CommonService;
import com.sunjet.front.common.vo.BaseOptionVo;
import com.sunjet.front.common.vo.OptionVo;

@Service
public class CommonServiceImpl implements CommonService{

	@Autowired
	SjAuthorityRepository sjAuthorityRepository;

	@Override
	public Map<SjAuthority, List<SjAuthority>> getAllAuthoritys() {
		final Map<SjAuthority, List<SjAuthority>> rtnMap = new HashMap<SjAuthority, List<SjAuthority>>();
		final Optional<List<SjAuthority>> sjAuthoritys = sjAuthorityRepository.findAllByOrderByParentAscOrdinaryAsc();
		if (sjAuthoritys.isPresent()) {
			final Map<String, SjAuthority> parentMap = sjAuthoritys.get().stream().filter(e -> e.getParent() == null)
					.collect(Collectors.toMap(SjAuthority::getAuthorityCode, Function.identity()));
			sjAuthoritys.get().stream().filter(e -> e.getParent() != null).forEach(o -> {
				{
					if (parentMap.containsKey(o.getAuthorityCode())) {
						rtnMap.computeIfAbsent(parentMap.get(o.getAuthorityCode()), v -> new ArrayList<SjAuthority>()).add(o);
					}
				}
			});
		}
		return rtnMap;
	}

	@Override
	public List<OptionVo> getAllAuthorityOptionVos() {
		List<OptionVo> parentOptions = new ArrayList<OptionVo>();
		final Optional<List<SjAuthority>> sjAuthoritys = sjAuthorityRepository.findAllByOrderByParentAscOrdinaryAsc();
		if (sjAuthoritys.isPresent()) {
			parentOptions = sjAuthoritys.get().stream().filter(event -> event.getParent() == null)
					.map(item -> new OptionVo(item.getAuthorityName(), item.getAuthorityCode())).collect(Collectors.toList());
			final Map<String, List<SjAuthority>> childOptionMap = sjAuthoritys.get().stream().filter(event -> event.getParent() != null).collect(Collectors.groupingBy(SjAuthority::getParent));
			if (CollectionUtils.isNotEmpty(parentOptions)) {
				parentOptions.forEach(it -> {
					if (childOptionMap.containsKey(it.getValue())) {
						final List<SjAuthority> childAuthorityList = childOptionMap.get(it.getValue());
						it.getChildren()
								.addAll(childAuthorityList.stream().map(item -> new BaseOptionVo(item.getAuthorityName(), item.getAuthorityCode())).collect(Collectors.toList()));
					}
				});
			}
		}
		return parentOptions;
	}

}

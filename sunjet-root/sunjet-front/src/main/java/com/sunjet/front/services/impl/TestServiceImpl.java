package com.sunjet.front.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunjet.common.dao.SjUserRepository;
import com.sunjet.common.entity.SjUser;
import com.sunjet.front.services.TestService;

@Service
public class TestServiceImpl implements TestService{
	@Autowired
	private SjUserRepository sjUserRepository;
	
	public int insertUser(){
		SjUser sjUser = new SjUser();
//		sjUser.setOid("1");
		sjUser.setId("oo");
		sjUser.setName("名");
		sjUser.setPwd("dddd");
		sjUserRepository.save(sjUser);
		System.out.println("OKKKKKKK");
		return 0;
	}
}

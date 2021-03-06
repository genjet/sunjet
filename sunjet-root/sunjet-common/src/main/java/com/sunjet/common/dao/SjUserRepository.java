package com.sunjet.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunjet.common.entity.SjUser;



@Repository
public interface SjUserRepository  extends JpaRepository<SjUser, String> {
	public SjUser findByIdAndPwd(String id, String pwd);

}

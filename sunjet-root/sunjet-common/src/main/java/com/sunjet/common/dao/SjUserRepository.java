package com.sunjet.common.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunjet.common.entity.SjUser;



@Repository
public interface SjUserRepository  extends JpaRepository<SjUser, String> {
	public SjUser findByAccountAndPwd(String account, String pwd);
	public SjUser findByAccount(String account);
	public SjUser findTopByOrderByEmpIdDesc();
	public Optional<SjUser> findByEmpId(String empId);

}

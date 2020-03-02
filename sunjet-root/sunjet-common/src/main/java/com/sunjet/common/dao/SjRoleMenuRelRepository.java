package com.sunjet.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunjet.common.entity.SjRole;
import com.sunjet.common.entity.SjRoleMenuRel;
import com.sunjet.common.entity.SjUser;



@Repository
public interface SjRoleMenuRelRepository  extends JpaRepository<SjRoleMenuRel, String> {
	public SjUser findBySjRole(SjRole role);
}

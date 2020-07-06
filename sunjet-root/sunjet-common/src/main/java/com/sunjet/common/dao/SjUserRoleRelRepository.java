package com.sunjet.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunjet.common.entity.SjUserRoleRel;



@Repository
public interface SjUserRoleRelRepository  extends JpaRepository<SjUserRoleRel, String> {

}

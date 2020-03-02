package com.sunjet.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunjet.common.entity.SjRole;



@Repository
public interface SjRoleRepository  extends JpaRepository<SjRole, String> {

}

package com.sunjet.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunjet.common.entity.SjRoleAuthorityRel;

@Repository
public interface SjRoleAuthorityRelRepository extends JpaRepository<SjRoleAuthorityRel, String> {

}

package com.sunjet.common.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunjet.common.entity.SjAuthority;

@Repository
public interface SjAuthorityRepository extends JpaRepository<SjAuthority, String> {
	public List<SjAuthority> findByAuthorityCodeIn(List<String> authorityCode);

}

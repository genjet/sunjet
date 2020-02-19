package com.sunjet.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunjet.common.entity.SjMenu;



@Repository
public interface SjMenuRepository  extends JpaRepository<SjMenu, String> {

}

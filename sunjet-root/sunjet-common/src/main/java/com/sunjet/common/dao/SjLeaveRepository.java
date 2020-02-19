package com.sunjet.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunjet.common.entity.SjLeave;



@Repository
public interface SjLeaveRepository  extends JpaRepository<SjLeave, String> {

}

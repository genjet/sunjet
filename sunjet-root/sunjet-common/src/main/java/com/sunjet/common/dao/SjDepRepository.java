package com.sunjet.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunjet.common.entity.SjDep;



@Repository
public interface SjDepRepository  extends JpaRepository<SjDep, String> {

}

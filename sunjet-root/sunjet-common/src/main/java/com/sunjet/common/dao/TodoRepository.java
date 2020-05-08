package com.sunjet.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunjet.common.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
	
}

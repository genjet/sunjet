package com.sunjet.front.todo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunjet.common.dao.TodoRepository;
import com.sunjet.common.entity.Todo;
import com.sunjet.front.common.payload.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TodoController {
	@Autowired
	private TodoRepository todoRepository;

	@PostMapping("/todo")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addTodo(String id, String title) {
		final ApiResponse rspObj = new ApiResponse();
		final Todo todo = new Todo();
		todo.setId(Long.valueOf(id));
		todo.setTitle(title);
		todoRepository.save(todo);
		rspObj.setData(todo);
		return ResponseEntity.ok(rspObj);
	}

	@GetMapping("/todo")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getTodos() {

		final ApiResponse rspObj = new ApiResponse();
		final List<Todo> todos = todoRepository.findAll();
		rspObj.setData(todos);
		return ResponseEntity.ok(rspObj);
	}

	@DeleteMapping("/todo/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteTodo(@PathVariable String id) {
		final ApiResponse rspObj = new ApiResponse();
		final Optional<Todo> todoOpt = todoRepository.findById(Long.valueOf(id));
		if(todoOpt.isPresent()){
			todoRepository.delete(todoOpt.get());
		}

		return ResponseEntity.ok(rspObj);
	}

	@PatchMapping("/todo/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateTodo(@PathVariable String id, Todo todo) {
		final ApiResponse rspObj = new ApiResponse();
		final Optional<Todo> todoOpt = todoRepository.findById(Long.valueOf(id));
		if(todoOpt.isPresent()){
			final Todo todoDB = todoOpt.get();
			todoDB.setTitle(todo.getTitle());
			todoDB.setCompleted(todo.isCompleted());
			rspObj.setData(todoDB);
			todoRepository.save(todoDB);
		}
		return ResponseEntity.ok(rspObj);
	}

	@PatchMapping("/todo/checkAll")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> checkAllTodo(boolean completed) {
		log.info(" ----------- completed: "+completed);
		final ApiResponse rspObj = new ApiResponse();
		final List<Todo> todos = todoRepository.findAll();
		todos.forEach(t -> t.setCompleted(completed));
		todoRepository.saveAll(todos);
		rspObj.setData(todos);
		return ResponseEntity.ok(rspObj);
	}

	@PatchMapping("/todo/deleteCompleted")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> clearCompleted() {
		final ApiResponse rspObj = new ApiResponse();
		final List<Todo> todos = todoRepository.findAll();
		final List<Todo> filter = todos.stream().filter(t -> t.isCompleted()).collect(Collectors.toList());
		todoRepository.deleteAll(filter);
		rspObj.setData(todos);
		return ResponseEntity.ok(rspObj);
	}
}

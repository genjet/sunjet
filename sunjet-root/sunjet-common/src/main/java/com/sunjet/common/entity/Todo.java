package com.sunjet.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import lombok.Data;

@Entity
@Table(	name = "todos")
@Data
public class Todo extends GenericEntity{

	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "jpa-uuid")
	private String oid;
	
	@Column(name = "id")
	private Long id;

	@NotBlank
	@Column(name = "title")
	@Size(max = 120)
	private String title;

	@NotNull
	@Column(name = "completed")
	@Type(type = "yes_no")
	private boolean completed = Boolean.FALSE;

	@NotNull
	@Column(name = "editing")
	@Type(type = "yes_no")
	private boolean editing = Boolean.FALSE;

	
}

package com.sunjet.common.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class GenericEntity {
	@Column(name = "create_id")
	@CreatedBy
	protected String createId;

	@Column(name = "create_datetime")
	@CreatedDate
	protected LocalDateTime createDate;

	@Column(name = "update_id")
	@LastModifiedBy
	protected String updateId;

	@Column(name = "update_datetime")
	@LastModifiedDate
	protected LocalDateTime updateDate;

}

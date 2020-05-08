package com.sunjet.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Andy
 *
 */
@Entity
@Table(name = "sj_apply")
@Data
public class SjApply extends GenericEntity {
	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "jpa-uuid")
	private String oid;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "flow_key")
	private String flowKey;

	@Column(name = "ordinary")
	private Integer ordinary;

	@Column(name = "apply_status")
	private String applyStatus;

	@Column(name = "apply_user")
	private String applyUser;

}

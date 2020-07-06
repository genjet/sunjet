package com.sunjet.common.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "sj_menu")
@Data
public class SjMenu extends GenericEntity{
	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "jpa-uuid")
	private String oid;
	@Column(name = "name")
	private String name;
	@Column(name = "url")
	private String url;
	@Column(name = "ordinary")
	private Integer ordinary;
	@Column(name = "parent_menu")
	private String parentMenu;
//	@Column(name = "sj_authority")
//	private SjAuthority sjAuthority;
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "sj_authority")
	private SjAuthority sjAuthority;
	
}

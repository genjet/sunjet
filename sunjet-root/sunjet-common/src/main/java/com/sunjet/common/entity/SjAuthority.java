package com.sunjet.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

//
@Entity
@Table(name = "SJ_AUTHORITY")
@Data
public class SjAuthority extends GenericEntity {
	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "jpa-uuid")
	private String oid;

	@Column(name = "authority_code")
	private String authorityCode;

	@Column(name = "authority_name")
	private String authorityName;
	
	@Column(name = "ordinary")
	private String ordinary;
	
	@Column(name = "parent")
	private String parent;

	// @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional
	// = false)
	// @JoinColumn(name = "sj_role")
	// private SjRole sjRole;// 角色
	// @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy =
	// "sjAuthority")
	// List<SjRole> sjRoles;

	// @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy =
	// "sjAuthority", orphanRemoval = true)
	// private List<SjAuthorityMenuRel> sjAuthorityMenuRels;

	// @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy =
	// "sjAuthority")
	// List<SjApi> sjApi;
	@OneToOne(mappedBy = "sjAuthority")
	private SjApi sjApi;

	// @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy =
	// "sjAuthority", orphanRemoval = true)
	// private List<SjRoleAuthorityRel> sjRoleAuthorityRels;

}

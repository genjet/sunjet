package com.sunjet.common.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Andy
 *
 */
@Entity
@Table(name = "sj_role")
@Data
public class SjRole extends GenericEntity {
	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "jpa-uuid")
	private String oid;

	@Column(name = "role_code")
	private String roleCode;
	
	@Column(name = "role_name")
	private String roleName;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "sjRole")
	private List<SjUserRoleRel> sjUserRoleRels;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "sjRole", orphanRemoval = true)
	private List<SjRoleAuthorityRel> sjRoleAuthorityRels;
//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "sjRole", orphanRemoval = true)
//	private Set<SjAuthority> sjAuthoritys;

}

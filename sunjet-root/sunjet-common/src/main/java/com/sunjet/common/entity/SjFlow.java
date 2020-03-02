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

import com.sunjet.common.entity.enumeration.FlowStatusEnum;

/**
 * @author Andy
 *
 */
@Entity
@Table(name = "sj_flow")
public class SjFlow extends GenericEntity {
	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "jpa-uuid")
	private String oid;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "description")
	private String description;

	@Column(name = "flow_status")
	private FlowStatusEnum flowStatus;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "sjFlow", orphanRemoval = true)
	private List<SjFlowNode> sjFlowNodes;
	//
	// @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy =
	// "sjRole", orphanRemoval = true)
	// private List<SjRoleMenuRel> sjRoleMenuRels;
	//
	// @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy =
	// "sjRole", orphanRemoval = true)
	// private Set<SjAuthority> sjAuthoritys;
	//
	//
	// public String getOid() {
	// return oid;
	// }
	//
	// public void setOid(String oid) {
	// this.oid = oid;
	// }
	//
	// public String getRoleCode() {
	// return roleCode;
	// }
	//
	// public void setRoleCode(String roleCode) {
	// this.roleCode = roleCode;
	// }
	//
	// public List<SjUserRoleRel> getSjUserRoleRels() {
	// return sjUserRoleRels;
	// }
	//
	// public void setSjUserRoleRels(List<SjUserRoleRel> sjUserRoleRels) {
	// this.sjUserRoleRels = sjUserRoleRels;
	// }
	//
	// public List<SjRoleMenuRel> getSjRoleMenuRels() {
	// return sjRoleMenuRels;
	// }
	//
	// public void setSjRoleMenuRels(List<SjRoleMenuRel> sjRoleMenuRels) {
	// this.sjRoleMenuRels = sjRoleMenuRels;
	// }
	//
	// public Set<SjAuthority> getSjAuthoritys() {
	// return sjAuthoritys;
	// }
	//
	// public void setSjAuthoritys(Set<SjAuthority> sjAuthoritys) {
	// this.sjAuthoritys = sjAuthoritys;
	// }

}

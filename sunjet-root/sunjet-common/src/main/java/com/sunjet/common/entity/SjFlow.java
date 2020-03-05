package com.sunjet.common.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	@Enumerated(EnumType.STRING)
	private FlowStatusEnum flowStatus;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sjFlowNode", referencedColumnName = "oid")
	private SjFlowNode sjFlowNode;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FlowStatusEnum getFlowStatus() {
		return flowStatus;
	}

	public void setFlowStatus(FlowStatusEnum flowStatus) {
		this.flowStatus = flowStatus;
	}

	public SjFlowNode getSjFlowNode() {
		return sjFlowNode;
	}

	public void setSjFlowNode(SjFlowNode sjFlowNode) {
		this.sjFlowNode = sjFlowNode;
	}

}

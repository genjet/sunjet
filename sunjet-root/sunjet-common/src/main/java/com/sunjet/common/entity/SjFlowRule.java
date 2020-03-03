package com.sunjet.common.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Andy
 *
 */
@Entity
@Table(name = "sj_flow_rule")
public class SjFlowRule extends GenericEntity {
	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "jpa-uuid")
	private String oid;

	@OneToOne(mappedBy = "sjFlowRule")
	private SjFlowProcess sjFlowProcess;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sjFlowNode_oid", referencedColumnName = "oid")
	private SjFlowNode sjFlowNode;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public SjFlowProcess getSjFlowProcess() {
		return sjFlowProcess;
	}

	public void setSjFlowProcess(SjFlowProcess sjFlowProcess) {
		this.sjFlowProcess = sjFlowProcess;
	}

	public SjFlowNode getSjFlowNode() {
		return sjFlowNode;
	}

	public void setSjFlowNode(SjFlowNode sjFlowNode) {
		this.sjFlowNode = sjFlowNode;
	}

}

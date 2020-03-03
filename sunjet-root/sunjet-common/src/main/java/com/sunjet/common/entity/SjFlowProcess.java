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
@Table(name = "sj_flow_process")
public class SjFlowProcess extends GenericEntity {
	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "jpa-uuid")
	private String oid;

	@Column(name = "handler_User")
	private String handlerUser;

	@OneToOne(mappedBy = "sjFlowProcess")
	private SjFlowNode sjFlowNode;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sjFlowRule_oid", referencedColumnName = "oid")
	private SjFlowRule sjFlowRule;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getHandlerUser() {
		return handlerUser;
	}

	public void setHandlerUser(String handlerUser) {
		this.handlerUser = handlerUser;
	}

	public SjFlowNode getSjFlowNode() {
		return sjFlowNode;
	}

	public void setSjFlowNode(SjFlowNode sjFlowNode) {
		this.sjFlowNode = sjFlowNode;
	}

	public SjFlowRule getSjFlowRule() {
		return sjFlowRule;
	}

	public void setSjFlowRule(SjFlowRule sjFlowRule) {
		this.sjFlowRule = sjFlowRule;
	}

}

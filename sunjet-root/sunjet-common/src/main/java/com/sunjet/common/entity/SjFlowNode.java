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
@Table(name = "sj_flow_node")
public class SjFlowNode extends GenericEntity {
	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "jpa-uuid")
	private String oid;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "dec")
	private String dec;

	@OneToOne(mappedBy = "sjFlowNode")
	private SjFlow sjFlow;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sjFlowProcess_oid", referencedColumnName = "oid")
	private SjFlowProcess sjFlowProcess;

	@OneToOne(mappedBy = "sjFlowNode")
	private SjFlowRule sjFlowRule;

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

	public String getDec() {
		return dec;
	}

	public void setDec(String dec) {
		this.dec = dec;
	}

	public SjFlow getSjFlow() {
		return sjFlow;
	}

	public void setSjFlow(SjFlow sjFlow) {
		this.sjFlow = sjFlow;
	}

	public SjFlowProcess getSjFlowProcess() {
		return sjFlowProcess;
	}

	public void setSjFlowProcess(SjFlowProcess sjFlowProcess) {
		this.sjFlowProcess = sjFlowProcess;
	}

	public SjFlowRule getSjFlowRule() {
		return sjFlowRule;
	}

	public void setSjFlowRule(SjFlowRule sjFlowRule) {
		this.sjFlowRule = sjFlowRule;
	}

}

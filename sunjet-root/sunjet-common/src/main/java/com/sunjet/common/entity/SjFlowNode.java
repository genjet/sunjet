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

import lombok.Data;

/**
 * @author Andy
 *
 */
@Entity
@Table(name = "sj_flow_node")
@Data
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
	@JoinColumn(name = "sjFlowProcess", referencedColumnName = "oid")
	private SjFlowProcess sjFlowProcess;

	// @OneToOne(mappedBy = "sjFlowNode")
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "sjFlowRule", referencedColumnName = "oid")
	private SjFlowRule sjFlowRule;

}

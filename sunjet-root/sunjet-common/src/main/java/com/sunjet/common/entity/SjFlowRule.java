package com.sunjet.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Andy
 *
 */
@Entity
@Table(name = "sj_flow_rule")
@Data
public class SjFlowRule extends GenericEntity {
	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "jpa-uuid")
	private String oid;

	@OneToOne(mappedBy = "sjFlowRule")
	private SjFlowProcess sjFlowProcess;

	// @OneToOne(cascade = CascadeType.MERGE)
	// @JoinColumn(name = "sjFlowNode", referencedColumnName = "oid")
	@OneToOne(mappedBy = "sjFlowRule")
	private SjFlowNode sjFlowNode;


}

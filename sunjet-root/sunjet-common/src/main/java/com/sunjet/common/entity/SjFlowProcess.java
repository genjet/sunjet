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
@Table(name = "sj_flow_process")
@Data
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
	@JoinColumn(name = "sjFlowRule", referencedColumnName = "oid")
	private SjFlowRule sjFlowRule;


}

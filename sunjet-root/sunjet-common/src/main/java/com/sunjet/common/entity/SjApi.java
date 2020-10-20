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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sunjet.common.entity.enumeration.MethodEnum;

import lombok.Data;
@Entity
@Table(name = "sj_api")
@Data
public class SjApi  extends GenericEntity{
	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "jpa-uuid")
	private String oid;
	@Column(name = "url")
	private String url;
	@Column(name = "api_name")
	private String apiName;
	@Column(name = "method")
    @Enumerated(EnumType.STRING)
	private MethodEnum method;
	
//	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
//	@JoinColumn(name = "sj_authority")
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="sj_authority")
	private SjAuthority sjAuthority;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "sj_menu")
	private SjMenu sjMenu;

}

package com.crossp.jpa.domain;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="app_template")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class AppTemplate{

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private AppTemplateConf appTconf;	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="appTemplate_id")
	private List<AppItemArea> itemAreas;
	
	@JsonIgnore
	@OneToOne(mappedBy = "appTemplate", fetch = FetchType.LAZY)
	private AppSpace appSpace;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AppTemplateConf getAppTconf() {
		return appTconf;
	}

	public void setAppTconf(AppTemplateConf appTconf) {
		this.appTconf = appTconf;
	}

	public List<AppItemArea> getItemAreas() {
		return itemAreas;
	}

	public void setItemAreas(List<AppItemArea> itemAreas) {
		this.itemAreas = itemAreas;
	}

	public AppSpace getAppSpace() {
		return appSpace;
	}

	public void setAppSpace(AppSpace appSpace) {
		this.appSpace = appSpace;
	}
}

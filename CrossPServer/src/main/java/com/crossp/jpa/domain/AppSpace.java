package com.crossp.jpa.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="app_space")
public class AppSpace{

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String position;
	private int showType;
	private String transparency;	
	private boolean isPublic = true;
		
	@JsonIgnore
	@ManyToOne(optional = false)
	private User user;
	
	@OneToOne(cascade = CascadeType.ALL)
	private AppTemplate appTemplate; 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getShowType() {
		return showType;
	}
	public void setShowType(int showType) {
		this.showType = showType;
	}
	public String getTransparency() {
		return transparency;
	}
	public void setTransparency(String transparency) {
		this.transparency = transparency;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}	
	public void setUser(User user) {
		this.user = user;
	}
	public AppTemplate getAppTemplate() {
		return appTemplate;
	}
	public void setAppTemplate(AppTemplate appTemplate) {
		this.appTemplate = appTemplate;
	}
}

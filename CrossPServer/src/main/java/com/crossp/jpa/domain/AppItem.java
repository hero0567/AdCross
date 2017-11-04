package com.crossp.jpa.domain;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="app_item")
public class AppItem{

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String appId;
	private String icon;
	private String type;
	private String description;
	private long date = System.currentTimeMillis();
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE, optional = false)
	private User user;
	
	@OneToMany(fetch = FetchType.LAZY)
	List<AppEvent> events;
	
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
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<AppEvent> getEvents() {
		return events;
	}
	public void setEvents(List<AppEvent> events) {
		this.events = events;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
}

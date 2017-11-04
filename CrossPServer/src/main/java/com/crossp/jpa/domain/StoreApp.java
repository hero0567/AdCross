package com.crossp.jpa.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="store_app")
public class StoreApp{

	@Id
	@GeneratedValue
	private Long id;
	
	private String appstoreId;
	private String name;
	private String icon175Link;
	private String icon350Link;
	private String type;
	private String description;
	private String screenPictures;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAppstoreId() {
		return appstoreId;
	}
	public void setAppstoreId(String appstoreId) {
		this.appstoreId = appstoreId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon175Link() {
		return icon175Link;
	}
	public void setIcon175Link(String icon175Link) {
		this.icon175Link = icon175Link;
	}
	public String getIcon350Link() {
		return icon350Link;
	}
	public void setIcon350Link(String icon350Link) {
		this.icon350Link = icon350Link;
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
	public String getScreenPictures() {
		return screenPictures;
	}
	public void setScreenPictures(String screenPictures) {
		this.screenPictures = screenPictures;
	}
	
	
}

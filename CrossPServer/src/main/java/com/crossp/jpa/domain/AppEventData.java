package com.crossp.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

@Entity
@Inheritance
@Table(name="app_event_data")
public class AppEventData{

	@Id  
	@GeneratedValue
	private Long id;
	private Long appOwnerUserId;
	private Long appOwnerAppId;
	private Long appOwnerSpaceId;
	private Long appOwnerItemId;	
	private Long appOwnerTemplateId;
	private Long appOwnerAreaId;
	
	private Long appFromUserId;
	private Long appFromAppId;
	
	
	private String location;
	private String os;
	private int age;
	private int sex;
	private String share;
	private String comment;
	/*app, qq, sms, weibo, facebook*/
	private String eventFrom;
	/*show, click, download, share, comments*/
	private int eventType;
	private long date = System.currentTimeMillis();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAppOwnerUserId() {
		return appOwnerUserId;
	}
	public void setAppOwnerUserId(Long appOwnerUserId) {
		this.appOwnerUserId = appOwnerUserId;
	}
	public Long getAppOwnerAppId() {
		return appOwnerAppId;
	}
	public void setAppOwnerAppId(Long appOwnerAppId) {
		this.appOwnerAppId = appOwnerAppId;
	}
	public Long getAppOwnerSpaceId() {
		return appOwnerSpaceId;
	}
	public void setAppOwnerSpaceId(Long appOwnerSpaceId) {
		this.appOwnerSpaceId = appOwnerSpaceId;
	}
	public Long getAppOwnerItemId() {
		return appOwnerItemId;
	}
	public void setAppOwnerItemId(Long appOwnerItemId) {
		this.appOwnerItemId = appOwnerItemId;
	}
	public Long getAppOwnerTemplateId() {
		return appOwnerTemplateId;
	}
	public void setAppOwnerTemplateId(Long appOwnerTemplateId) {
		this.appOwnerTemplateId = appOwnerTemplateId;
	}
	public Long getAppOwnerAreaId() {
		return appOwnerAreaId;
	}
	public void setAppOwnerAreaId(Long appOwnerAreaId) {
		this.appOwnerAreaId = appOwnerAreaId;
	}
	public Long getAppFromUserId() {
		return appFromUserId;
	}
	public void setAppFromUserId(Long appFromUserId) {
		this.appFromUserId = appFromUserId;
	}
	public Long getAppFromAppId() {
		return appFromAppId;
	}
	public void setAppFromAppId(Long appFromAppId) {
		this.appFromAppId = appFromAppId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getShare() {
		return share;
	}
	public void setShare(String share) {
		this.share = share;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getEventFrom() {
		return eventFrom;
	}
	public void setEventFrom(String eventFrom) {
		this.eventFrom = eventFrom;
	}
	public int getEventType() {
		return eventType;
	}
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}
	
}

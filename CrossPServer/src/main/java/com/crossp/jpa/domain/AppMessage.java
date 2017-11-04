package com.crossp.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

@Entity
@Inheritance
@Table(name="app_message")
public class AppMessage{

	@Id  
	@GeneratedValue
	private Long id;
	private Long cid;
	private String cName;
	private Long pid;
	private String pName;
	private Long appId;
	private String appName;
	private Long appSpaceId;
	private String appSpaceName;
	private Long appItemId;
	private String appItemName;	
	private Long appTemplateId;
	private String appTemplateName;
	private Long appAreaId;
	private String data;
	private int type;
	private int status;
	private long date = System.currentTimeMillis();
	
	public AppMessage() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public Long getAppSpaceId() {
		return appSpaceId;
	}
	public void setAppSpaceId(Long appSpaceId) {
		this.appSpaceId = appSpaceId;
	}
	public Long getAppItemId() {
		return appItemId;
	}
	public void setAppItemId(Long appItemId) {
		this.appItemId = appItemId;
	}
	public Long getAppTemplateId() {
		return appTemplateId;
	}
	public void setAppTemplateId(Long appTemplateId) {
		this.appTemplateId = appTemplateId;
	}
	public Long getAppAreaId() {
		return appAreaId;
	}
	public void setAppAreaId(Long appAreaId) {
		this.appAreaId = appAreaId;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppSpaceName() {
		return appSpaceName;
	}

	public void setAppSpaceName(String appSpaceName) {
		this.appSpaceName = appSpaceName;
	}

	public String getAppTemplateName() {
		return appTemplateName;
	}

	public void setAppTemplateName(String appTemplateName) {
		this.appTemplateName = appTemplateName;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getAppItemName() {
		return appItemName;
	}

	public void setAppItemName(String appItemName) {
		this.appItemName = appItemName;
	}	
}

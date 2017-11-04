package com.crossp.beans;

import java.util.List;

// response of mobile's fetching request
public class DeliveredSpace {
	private String appId;
	private String position;
	private int showType;
	private String transparency;
	private String downloadLink;
	private List<DeliveredItem> items;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
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
	public String getDownloadLink() {
		return downloadLink;
	}
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}
	public List<DeliveredItem> getItems() {
		return items;
	}
	public void setItems(List<DeliveredItem> items) {
		this.items = items;
	}
	

}

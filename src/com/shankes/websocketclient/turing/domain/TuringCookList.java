package com.shankes.websocketclient.turing.domain;

/**
 * 菜谱列表(菜名,菜谱信息,详情链接,信息图标)
 * 
 * @author shankes
 */
public class TuringCookList {

	private String name;// 菜名
	private String info;// 菜谱信息
	private String detailurl;// 详情链接
	private String icon;// 信息图标

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getDetailurl() {
		return detailurl;
	}

	public void setDetailurl(String detailurl) {
		this.detailurl = detailurl;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}

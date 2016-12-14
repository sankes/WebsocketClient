package com.shankes.websocketclient.turing.domain;

/**
 * 链接类(链接类标识码 ,提示语,链接地址)
 * 
 * 列车,航班
 * 
 * @author shankes
 */
public class TuringLink extends TuringBaseInfo {

	private String url;// 链接地址

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

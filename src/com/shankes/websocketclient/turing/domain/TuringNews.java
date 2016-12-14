package com.shankes.websocketclient.turing.domain;

import java.util.List;

/**
 * 新闻类(新闻类标识码,提示语,信息列表<array>) )
 * 
 * @author shankes
 */
public class TuringNews extends TuringBaseInfo {

	private List<TuringNewsList> list;

	public List<TuringNewsList> getList() {
		return list;
	}

	public void setList(List<TuringNewsList> list) {
		this.list = list;
	}
}

package com.shankes.websocketclient.turing.domain;

import java.util.List;

/**
 * 菜谱类(菜谱类标识码,提示语,菜谱列表)
 * 
 * @author shankes
 */
public class TuringCook extends TuringBaseInfo {

	private List<TuringCookList> list;

	public List<TuringCookList> getList() {
		return list;
	}

	public void setList(List<TuringCookList> list) {
		this.list = list;
	}
}

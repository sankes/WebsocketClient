package com.shankes.websocketclient.turing.domain;

/**
 * 诗词类(诗词类标识码,提示语,诗词信息)
 * 
 * 注：该功能仅限儿童版使用
 * 
 * @author shankes
 */
public class TuringChildPoem extends TuringBaseInfo {

	private TuringChildPoemFunction function;

	public TuringChildPoemFunction getFunction() {
		return function;
	}

	public void setFunction(TuringChildPoemFunction function) {
		this.function = function;
	}
}

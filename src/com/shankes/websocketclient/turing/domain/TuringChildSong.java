package com.shankes.websocketclient.turing.domain;

/**
 * 儿歌类(儿歌类标识码,提示语,歌曲信息)
 * 
 * 注：该功能仅限儿童版使用
 * 
 * @author shankes
 * 
 */
public class TuringChildSong extends TuringBaseInfo {

	private TuringChildSongFunction fun;

	public TuringChildSongFunction getFun() {
		return fun;
	}

	public void setFun(TuringChildSongFunction fun) {
		this.fun = fun;
	}
}

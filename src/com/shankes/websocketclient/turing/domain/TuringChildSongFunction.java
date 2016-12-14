package com.shankes.websocketclient.turing.domain;

/**
 * 歌曲信息(歌曲名,歌手)
 * 
 * @author shankes
 */
public class TuringChildSongFunction {

	private String song;// 歌曲名
	private String singer;// 歌手

	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}
}

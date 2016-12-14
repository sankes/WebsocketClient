package com.shankes.websocketclient.turing.domain;

/**
 * 诗词信息(作者,诗词名)
 * 
 * @author shankes
 */
public class TuringChildPoemFunction {

	private String author;// 作者
	private String name;// 诗词名

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

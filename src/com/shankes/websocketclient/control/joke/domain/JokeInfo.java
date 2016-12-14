package com.shankes.websocketclient.control.joke.domain;

public class JokeInfo {

	private int status;
	private String msg;
	private JokeResult result;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public JokeResult getResult() {
		return result;
	}

	public void setResult(JokeResult result) {
		this.result = result;
	}
}

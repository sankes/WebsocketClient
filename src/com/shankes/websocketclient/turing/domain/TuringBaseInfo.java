package com.shankes.websocketclient.turing.domain;

import java.util.Date;

/**
 * @author shankes
 */
public class TuringBaseInfo {

	private int code;// 标识码
	private String text;// 结果
	private MessageType messageType;// 消息类型
	private Date time;// 消息发送时间

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}

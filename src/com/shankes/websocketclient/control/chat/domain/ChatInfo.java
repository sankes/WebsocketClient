package com.shankes.websocketclient.control.chat.domain;

public class ChatInfo {

	private String senderId;// 发送人id
	private String content;// 内容
	private MessageType messageType;// 消息类型
	private String time;// 消息发送时间

	public ChatInfo() {
		super();
	}

	public ChatInfo(String senderId, String content, MessageType messageType, String time) {
		super();
		this.senderId = senderId;
		this.content = content;
		this.messageType = messageType;
		this.time = time;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}

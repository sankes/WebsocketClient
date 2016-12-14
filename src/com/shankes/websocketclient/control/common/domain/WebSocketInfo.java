package com.shankes.websocketclient.control.common.domain;

import java.io.Serializable;

/**
 * Created by shankes on 2016/9/8.
 */

public class WebSocketInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String senderId;
	protected String recvId;
	protected MsgType type;
	protected Object content;

	public WebSocketInfo() {

	}

	public WebSocketInfo(String senderId, MsgType type) {
		super();
		this.senderId = senderId;
		this.type = type;
	}

	public WebSocketInfo(String senderId, String recvId, MsgType type) {
		this.senderId = senderId;
		this.recvId = recvId;
		this.type = type;
	}

	public WebSocketInfo(String senderId, String recvId, MsgType type, Object content) {
		this.senderId = senderId;
		this.recvId = recvId;
		this.type = type;
		this.content = content;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getRecvId() {
		return recvId;
	}

	public void setRecvId(String recvId) {
		this.recvId = recvId;
	}

	public MsgType getType() {
		return type;
	}

	public void setType(MsgType type) {
		this.type = type;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
}

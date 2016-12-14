package com.shankes.websocketclient.control.chat.domain;

public enum MessageType {

	FROM(0), // 收到的消息
	TO(1); // 发送的消息

	private int mType;

	private MessageType(int _type) {
		this.mType = _type;
	}

	public int getValueType() {
		return mType;
	}

	public static MessageType valueOf(int _type) {
		MessageType messageType = FROM;
		switch (_type) {
		case 0:
			messageType = FROM;
			break;
		case 1:
			messageType = TO;
			break;

		default:
			break;
		}
		return messageType;
	}
}

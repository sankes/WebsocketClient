package com.shankes.websocketclient.control.common.util;

import android.content.Context;

import com.shankes.app.App;
import com.shankes.util.LogUtil;
import com.shankes.util.ToastUtil;
import com.shankes.websocketclient.R;
import com.shankes.websocketclient.control.common.domain.MsgType;
import com.shankes.websocketclient.control.common.domain.WebSocketInfo;

/**
 * @author shankes
 * 
 * @data 2016-10-26
 */
public class WebSocketMessage {

	private static final String TAG = "WEBSOCKET";

	public static void sendWebSocketMessage(Context context, WebSocketInfo webSocketInfo) {
		if (WebSocketUtil.getStatus()) {
			MsgType type = webSocketInfo.getType();
			String websocketMessage = null;
			switch (type) {
			case LOGIN:// 登录
				websocketMessage = App.getGson().toJson(webSocketInfo, WebSocketInfo.class);
				LogUtil.i("发送登录信息成功");
				ToastUtil.showShortDebug(context, "发送登录信息成功");
				break;
			case LOGINOUT:// 退出登录
				websocketMessage = App.getGson().toJson(webSocketInfo, WebSocketInfo.class);
				LogUtil.i("发送退出登录信息成功");
				ToastUtil.showShortDebug(context, "发送退出登录信息成功");
				break;
			case MESSAGE_TO_ONE:// 单人消息
				websocketMessage = App.getGson().toJson(webSocketInfo, WebSocketInfo.class);
				LogUtil.i("发送单人消息成功");
				ToastUtil.showShortDebug(context, "发送单人消息成功");
				break;
			}
			WebSocketUtil.getWebSocketConnection().sendTextMessage(websocketMessage);
		} else {
			LogUtil.e(TAG, context.getResources().getString(R.string.msg_send_login_first));
			ToastUtil.showShort(context, R.string.msg_send_login_first);
		}
	}
}

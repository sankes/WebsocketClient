package com.shankes.websocketclient.control.common.util;

import android.app.Activity;

import com.shankes.util.LogUtil;
import com.shankes.util.ToastUtil;
import com.shankes.websocketclient.activity.LoginActivity;
import com.shankes.websocketclient.config.Config;
import com.shankes.websocketclient.control.common.domain.MsgType;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;

/**
 * 处理websocket工具类
 * <p>
 * Created by shankes on 2016/10/20.
 */
public class WebSocketUtil {

	private static final String TAG = "WEBSOCKET";

	private static boolean isOnline = false;
	// private static boolean isLogin = false;

	private static String senderId, recvId;
	private static MsgType type;

	private static WebSocketConnection webSocketConnection;

	public static WebSocketConnection getWebSocketConnection() {
		if (webSocketConnection == null) {
			webSocketConnection = new WebSocketConnection();
		}
		return webSocketConnection;
	}

	/**
	 * 建立websocket连接
	 * 
	 * @param activity
	 *            上下文
	 */
	public static void getConnect(final Activity activity) {
		getWebSocketConnection();
		try {
			webSocketConnection.connect(Config.SERVER_IP_WEBSOCKET, new WebSocketHandler() {
				@Override
				public void onOpen() {
					handleOpen(activity, webSocketConnection);
				}

				@Override
				public void onTextMessage(String payload) {
					handleTextMessage(activity, webSocketConnection, payload);
				}

				@Override
				public void onClose(int code, String reason) {
					handleClose(activity, webSocketConnection, code, reason);
				}
			});
		} catch (WebSocketException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取在线状态
	 * 
	 * @return 返回是否在线
	 */
	public static boolean getStatus() {
		return isOnline;
	}

	private static void handleOpen(Activity activity, WebSocketConnection webSocketConnection) {
		isOnline = true;
		LogUtil.e(TAG, "websocket连接打开");
		ToastUtil.showShort(activity, "websocket连接打开");
		if (LoginActivity.getInstance() != null) {
			LoginActivity.getInstance().finish();
		}
	}

	private static void handleTextMessage(Activity activity, WebSocketConnection webSocketConnection, String payload) {
		LogUtil.e(TAG, payload);
		ToastUtil.showShort(activity, payload);
	}

	private static void handleClose(Activity activity, WebSocketConnection webSocketConnection, int code, String reason) {
		isOnline = false;
		ToastUtil.showShort(activity, "连接断开");

		LogUtil.e(TAG, "reason:" + reason);
		ToastUtil.showShortDebug(activity, "reason:" + reason);
		// activity.finish();
	}

	public static String getSenderId() {
		return senderId;
	}

	public static void setSenderId(String senderId) {
		WebSocketUtil.senderId = senderId;
	}

	public static String getRecvId() {
		return recvId;
	}

	public static void setRecvId(String recvId) {
		WebSocketUtil.recvId = recvId;
	}

	public static MsgType getType() {
		return type;
	}

	public static void setType(MsgType type) {
		WebSocketUtil.type = type;
	}
}

package com.shankes.websocketclient.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;

import com.shankes.util.LogUtil;
import com.shankes.util.ToastUtil;
import com.shankes.websocketclient.activity.AboutActivity;
import com.shankes.websocketclient.activity.LoginActivity;
import com.shankes.websocketclient.guide.GuideActivity;

/**
 * 1、接收广播消息：BootBroadcastReceiver.java
 * 
 * 2、application内添加注册了一个receiver
 * 
 * 3、在manifest中添加以下权限
 * 
 * @author shankes
 * 
 * @data 2016-11-9
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

	private static final String TAG = "MyBroadcastReceiver";

	// 在系统启动后广播
	private static final String ACTION_BOOT_COMPLETED = Intent.ACTION_BOOT_COMPLETED;
	// 屏幕被打开
	private static final String ACTION_SCREEN_ON = Intent.ACTION_SCREEN_ON;
	// 屏幕被关闭
	private static final String ACTION_SCREEN_OFF = Intent.ACTION_SCREEN_OFF;
	// 网络状态改变
	private static final String CONNECTIVITY_ACTION = ConnectivityManager.CONNECTIVITY_ACTION;

	@Override
	public void onReceive(Context context, Intent intent) {
		ToastUtil.showShort(context, intent.getAction());
		LogUtil.i(TAG, intent.getAction());
		switch (intent.getAction()) {
		case ACTION_BOOT_COMPLETED:
			intent = new Intent(context, GuideActivity.class); // 要启动的Activity
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
			break;
		case ACTION_SCREEN_ON:
			intent = new Intent(context, LoginActivity.class); // 要启动的Activity
			// intent.addFlags(intent2.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
			break;
		case ACTION_SCREEN_OFF:
			intent = new Intent(context, AboutActivity.class); // 要启动的Activity
			// intent.addFlags(intent2.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
			break;
		case CONNECTIVITY_ACTION:
			State wifiState = null;
			State mobileState = null;
			ConnectivityManager connectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			wifiState = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
			mobileState = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
			if (wifiState != null && mobileState != null && State.CONNECTED != wifiState
					&& State.CONNECTED == mobileState) {
				// 手机网络连接成功
				ToastUtil.showShort(context, "手机网络连接成功");
			} else if (wifiState != null && mobileState != null && State.CONNECTED != wifiState
					&& State.CONNECTED != mobileState) {
				// 手机没有任何的网络
				ToastUtil.showShort(context, "手机没有任何的网络  ");
			} else if (wifiState != null && State.CONNECTED == wifiState) {
				// 无线网络连接成功
				ToastUtil.showShort(context, "无线网络连接成功  ");
			}
			break;
		// TODO Auto-generated method stub
		default:
			break;
		}
	}

}

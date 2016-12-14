package com.shankes.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shankes.util.ACache;
import com.shankes.websocketclient.control.user.domain.UserInfo;

public class App extends Application {

	private static UserInfo userInfo;

	// 缓存
	private static ACache mACache;

	private static RequestQueue volleyQueue;
	private static Gson mGson;

	private static App mInstance;

	public synchronized static App getInstance() {
		if (mInstance == null) {
			mInstance = new App();
		}
		return mInstance;
	}

	/**
	 * 获取缓存操作对象
	 * 
	 * @return 缓存操作对象
	 */
	public synchronized static ACache getmACache() {
		if (mACache == null) {
			mACache = ACache.get(getInstance());
		}
		return mACache;
	}

	/**
	 * 初始化gson
	 * 
	 * @return gson工具对象
	 */
	public synchronized static Gson getGson() {
		if (mGson == null) {
			mGson = new GsonBuilder().create();
		}
		return mGson;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mInstance = this; // 建立Volley的Http请求队列
		volleyQueue = Volley.newRequestQueue(getApplicationContext());
	}

	// 开放Volley的HTTP请求队列接口
	public static RequestQueue getRequestQueue() {
		return volleyQueue;
	}

	public synchronized static UserInfo getUserInfo() {
		return userInfo;
	}
}

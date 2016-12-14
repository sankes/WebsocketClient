package com.shankes.volley.util;

import java.util.Map;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.shankes.app.App;
import com.shankes.volley.encode.MyStringRequest;
import com.shankes.volley.listener.VolleyListenerInterface;

/**
 * 使用时注意:
 * 
 * 1.添加volley的jar包volley-lib-1.0.19.jar
 * 
 * 2.AndroidManifest.xml中添加网络权限
 * 
 * 3.AndroidManifest.xml中<application>添加属性
 * android:name="com.shankes.util.volley.app.App"
 * 
 * 
 * https://mvnrepository.com/artifact/com.mcxiaoke.volley/library compile group:
 * 
 * 'com.mcxiaoke.volley', name: 'library', version: '1.0.19'
 * 
 * @author shankesmile
 */
public class VolleyRequestUtil {

	public static StringRequest stringRequest;

	public static Context context;

	/**
	 * 获取GET请求内容
	 * 
	 * @param context
	 *            当前上下文
	 * @param url
	 *            请求的url地址
	 * @param tag
	 *            当前请求的标签
	 * @param headers
	 *            Header
	 * @param volleyListenerInterface
	 *            VolleyListenerInterface接口
	 */
	public static void requestGet(Context context, String url, String tag, final Map<String, String> headers,
			VolleyListenerInterface volleyListenerInterface) {
		// 清除请求队列中的tag标记请求
		App.getRequestQueue().cancelAll(tag);
		// 创建当前的请求，获取字符串内容,这里使用MyStringRequest解决返回值乱码问题
		stringRequest = new MyStringRequest(Request.Method.GET, url, volleyListenerInterface.successListener(),
				volleyListenerInterface.errorListener()) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				if (headers != null) {
					return headers;
				} else {
					return super.getHeaders();
				}
			}
		};
		// 为当前请求添加标记
		stringRequest.setTag(tag);
		// 将当前请求添加到请求队列中
		App.getRequestQueue().add(stringRequest);
		// 重启当前请求队列
		// App.getRequestQueue().start();
	}

	/**
	 * 获取POST请求内容
	 * 
	 * @param context
	 *            当前上下文
	 * @param url
	 *            请求的url地址
	 * @param tag
	 *            当前请求的标签
	 * @param params
	 *            POST请求内容
	 * @param volleyListenerInterface
	 *            VolleyListenerInterface接口
	 */
	public static void requestPost(Context context, String url, String tag, final Map<String, String> params,
			final Map<String, String> headers, VolleyListenerInterface volleyListenerInterface) {
		// 清除请求队列中的tag标记请求
		App.getRequestQueue().cancelAll(tag);
		// 创建当前的POST请求，并将请求内容写入Map中,这里使用MyStringRequest解决返回值乱码问题
		stringRequest = new MyStringRequest(Request.Method.POST, url, volleyListenerInterface.successListener(),
				volleyListenerInterface.errorListener()) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				return params;
			}

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				if (headers != null) {
					return headers;
				} else {
					return super.getHeaders();
				}
			}
		};
		// 为当前请求添加标记
		stringRequest.setTag(tag);
		// 将当前请求添加到请求队列中
		App.getRequestQueue().add(stringRequest);
		// 重启当前请求队列
		// App.getRequestQueue().start();
	}
}

package com.shankes.websocketclient.turing.util;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.shankes.util.LogUtil;
import com.shankes.websocketclient.turing.util.PostServer.HttpPostCallBack;
import com.shankes.websocketclient.turing.util.SleepThreadUtil.SleepThreadCallBack;

;

/**
 * 图灵机器人
 * 
 * @author shankes
 * 
 * @api:http://www.tuling123.com
 */
public class TuringUtil {

	public static final String HTTP_URL = "http://www.tuling123.com/openapi/api";

	protected static final String TAG = "TURING";
	public static final String APIKEY = "43be4b5743ad4c5aa8895399a00f3e33";
	public static final String SECRET = "6e245327f9c7a409";

	// * 注册之后在机器人接入页面获得
	private static String key;// * APIKEY
	// * 请求内容，编码方式为UTF-8长度1-30
	private static String info;// * 今天天气怎么样
	private static String loc;// 北京市中关村”，
	// 开发者给自己的用户分配的唯一标志（对应自己的每一个用户）
	// abc123（支持0-9，a-z,A-Z组合，不能包含特殊字符）
	private static String userid;// 123456

	// 拼接参数
	private static StringBuffer HTTP_ARG = null;

	/**
	 * @param secret
	 *            图灵网站上的secret
	 * @param apiKey
	 *            图灵网站上的apiKey
	 * @param infoValue
	 *            发送的内容
	 */
	private static String getParam(String secret, String apiKey, String infoValue) {
		// 待加密的json数据
		String data = "{\"key\":\"" + apiKey + "\",\"info\":\"" + infoValue + "\"}";
		// 获取时间戳
		String timestamp = String.valueOf(System.currentTimeMillis());
		// 生成密钥
		String keyParam = secret + timestamp + apiKey;
		String key = Md5.MD5(keyParam);
		// 加密
		Aes mc = new Aes(key);
		data = mc.encrypt(data);
		// 封装请求参数
		JSONObject json = new JSONObject();
		try {
			json.put("key", apiKey);
			json.put("timestamp", timestamp);
			json.put("data", data);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}

	/**
	 * @param infoValue
	 *            请求内容，编码方式为UTF-8长度1-30,例如:今天天气怎么样
	 * @param locValue
	 *            地点
	 * @param useridValue
	 *            开发者给自己的用户分配的唯一标志（对应自己的每一个用户）
	 * @param textView
	 *            显示结果的文本框
	 */
	public static void turingPost(final Handler handler, String infoValue, String locValue, String useridValue) {
		if (TextUtils.isEmpty(infoValue)) {
			infoValue = "发送消息失败";
		}
		try {
			// 请求图灵api
			// String result = PostServer.SendPost(json.toString(),
			// "http://www.tuling123.com/openapi/api");
			// System.out.println(result);
			// post方法
			String jsonString = getParam(SECRET, APIKEY, infoValue);
			PostServer.doPostAsyn(HTTP_URL, jsonString, new HttpPostCallBack() {
				@Override
				public void onRequestComplete(String r) {
					try {
						LogUtil.e(r);
						if (TextUtils.isEmpty(r)) {
							return;
						}
						final String result = r;
						final JSONObject jsonObject = new JSONObject(result);
						long sleepTime = jsonObject.getString("text").length() * 200;
						final Message msg = new Message();
						msg.what = jsonObject.getInt("code");
						Bundle bundle = new Bundle();
						bundle.putString("result", result);
						msg.setData(bundle);
						new SleepThreadUtil(sleepTime, TuringCodeType.getValueCode(TuringCodeType.RECEIVE_RESULT),
								null, new SleepThreadCallBack() {
									@Override
									public void onSleepComplete() {
										handler.sendMessage(msg);
									}
								}, bundle);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
			/*
			 * HttpUtils.doPostAsyn(HTTP_URL, HTTP_ARG.toString(), new
			 * CallBack() {
			 * 
			 * @Override public void onRequestComplete(String result) {
			 * textView.setText(result); } });
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
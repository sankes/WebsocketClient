package com.shankes.websocketclient.control.joke.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.shankes.app.App;
import com.shankes.volley.listener.VolleyListenerInterface;
import com.shankes.volley.util.VolleyRequestUtil;
import com.shankes.websocketclient.activity.Tab02Activity;
import com.shankes.websocketclient.control.joke.domain.JokeInfo;

public class JokeUtil {

	private static Context mContext;
	private static int mTimes;
	private static int mCount;

	public static List<JokeInfo> mData;

	public static void getJokes(Context context) {
		getJokeVolleyGet(context);
	}

	public static void startGetJokesTask(Context context, int times) {
		mContext = context;
		mTimes = times;
		mCount = 0;
		// 启动定时器,获取joke,暂定1秒发一次
		handler.postDelayed(runnable, 1000);
	}

	private static Handler handler = new Handler();
	private static Runnable runnable = new Runnable() {

		@Override
		public void run() {
			if (mCount < mTimes) {
				mCount++;
				// 要执行的语句
				getJokes(mContext);
				handler.postDelayed(this, 1000);
			} else {
				// 3. 停止计时器
				handler.removeCallbacks(runnable);
			}
		}
	};

	/**
	 * 1 .用GET方式请求网络资源
	 */
	private static void getJokeVolleyGet(final Context context) {
		// 智能问答api: http://www.jisuapi.com/api/iqa/
		String httpUrl = "http://api.jisuapi.com/iqa/query?appkey=c36f5e0c04a75df0&question=笑话";
		VolleyRequestUtil.requestGet(context, httpUrl, "get", null, new VolleyListenerInterface(context,
				VolleyListenerInterface.mSuccessListener, VolleyListenerInterface.mErrorListener) {
			// Volley请求成功时调用的函数
			@Override
			public void onMySuccess(String result) {
				Toast.makeText(context, "1.用GET方式请求网络资源成功结果：" + result, Toast.LENGTH_LONG).show();
				JokeInfo jokeInfo = App.getGson().fromJson(result, JokeInfo.class);
				if (mData != null) {
					mData.add(jokeInfo);
					if (Tab02Activity.mJokeAdapter != null) {
						Tab02Activity.mJokeAdapter.notifyDataSetChanged();
					}
				}
			}

			// Volley请求失败时调用的函数
			@Override
			public void onMyError(VolleyError error) {
				Toast.makeText(context, "1.用GET方式请求网络资源失败结果：" + error, Toast.LENGTH_LONG).show();
			}
		});
	}

	/**
	 * 2.用POST方式请求网络资源
	 */
	@SuppressWarnings("unused")
	private void testVolleyPost(final Context context) {
		// 百度timezone
		// String httpUrl =
		// "http://api.map.baidu.com/timezone/v1?ak=7sqA81OWkmxg20kWEGb5idInSj71pCG1&timestamp=1473595403&location=40.055,116.308";
		// Map<String, String> params = null;
		// Map<String, String> headers = null;

		// 返回结果不符合预期,应该是接口不支持post
		// String httpUrl = "http://api.map.baidu.com/timezone/v1";
		// Map<String, String> params = new HashMap<String, String>();
		// params.put("ak", "7sqA81OWkmxg20kWEGb5idInSj71pCG1");
		// params.put("timestamp", "1473595403");
		// params.put("location", "40.055,116.308");
		// Map<String, String> headers = null;

		// String httpUrl =
		// "http://apis.baidu.com/apistore/mobilenumber/mobilenumber?phone=18366136009";
		String httpUrl = "http://apis.baidu.com/apistore/mobilephoneservice/mobilephone?tel=18366136009";
		Map<String, String> params = null;
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("apikey", "9940f7550db411f7dea7acf00a10bb93");

		// 手机号码归属地查询api:http://apistore.baidu.com/apiworks/servicedetail/117.html
		// String httpUrl =
		// "http://apis.baidu.com/apistore/mobilenumber/mobilenumber";
		// Map<String, String> params = new HashMap<String, String>();
		// params.put("phone", "18366136009");
		// Map<String, String> headers = new HashMap<String, String>();
		// headers.put("apikey", "9940f7550db411f7dea7acf00a10bb93");

		VolleyRequestUtil.requestPost(context, httpUrl, "post", params, headers, new VolleyListenerInterface(context,
				VolleyListenerInterface.mSuccessListener, VolleyListenerInterface.mErrorListener) {
			// Volley请求成功时调用的函数
			@Override
			public void onMySuccess(String result) {
				Toast.makeText(context, "2.用POST方式请求网络资源" + result, Toast.LENGTH_LONG).show();
			}

			// Volley请求失败时调用的函数
			@Override
			public void onMyError(VolleyError error) {
				Toast.makeText(context, "2.用POST方式请求网络资源" + error, Toast.LENGTH_LONG).show();
			}
		});

	}
}

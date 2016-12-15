package com.shankes.websocketclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.shankes.util.SleepThread;
import com.shankes.websocketclient.R;
import com.shankes.websocketclient.guide.GuideActivity;

public class WelcomeActivity extends Activity implements OnClickListener {

	private ImageView welcomeBgIV;
	private static boolean isClick = false;
	private static final int toAppMain = 0x10001;

	static Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case toAppMain:
				toAppMain();
				break;

			default:
				break;
			}
		};
	};

	private static WelcomeActivity instance;

	public static synchronized WelcomeActivity getInstance() {
		if (instance == null) {
			instance = new WelcomeActivity();
		}
		return instance;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置全屏,android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
		// requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);// 去掉信息栏
		setContentView(R.layout.activity_welcome);
		instance = this;
		isFirstStart();// 判断是否第一次打开app
		initView();
		initEventClick();
	}

	private void initView() {
		welcomeBgIV = (ImageView) findViewById(R.id.iv_welcome_bg);
	}

	private void initEventClick() {
		welcomeBgIV.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_welcome_bg:
			toAppMain();
			break;

		default:
			break;
		}
	}

	/**
	 * 判断是否第一次打开app
	 */
	private void isFirstStart() {
		SharedPreferences preferences = getSharedPreferences("websocketclient", MODE_PRIVATE);
		int count = preferences.getInt("firststart", 0);
		// 判断程序与第几次运行
		if (count == 0) {// 如果是第一次运行则跳转到引导页面
			// if (true) {// TODO 如果是第一次运行则跳转到引导页面
			Editor editor = preferences.edit();
			// 存入数据
			editor.putInt("firststart", ++count);
			// 提交修改
			editor.commit();
			// 启动引导页
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), GuideActivity.class);
			startActivity(intent);
			finish();
		} else {// 睡眠两秒后进入程序主界面
			isClick = false;
			new SleepThread(2000, toAppMain, handler);
		}
	}

	/**
	 * 进入主界面
	 */
	private static void toAppMain() {
		if (!isClick) {
			isClick = true;
			Intent intent = new Intent();
			intent.setClass(getInstance(), TabActivity.class);
			getInstance().startActivity(intent);
			getInstance().finish();
		}
	}
}

package com.shankes.websocketclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.shankes.customview.SlideSwitch;
import com.shankes.websocketclient.R;

public class SettingActivity extends Activity implements OnClickListener {

	// private RelativeLayout msgSwitchRelativeLayout;
	private SlideSwitch settingMsgSlideSwitch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);

		// 初始化控件
		initView();
		// 初始化点击事件
		initEventClick();
		// 初始化滑动开关
		initSlideSwitch();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		// msgSwitchRelativeLayout = (RelativeLayout)
		// findViewById(R.id.setting_msg_rl);
		settingMsgSlideSwitch = (SlideSwitch) findViewById(R.id.setting_msg_slide_switch);
	}

	/**
	 * 初始化点击事件
	 */
	private void initEventClick() {
		// msgSwitchRelativeLayout.setOnClickListener(this);

	}

	/**
	 * 初始化滑动开关监听
	 */
	private void initSlideSwitch() {
		// 设置开关初始化状态
		settingMsgSlideSwitch.setStatus(true);
		settingMsgSlideSwitch.setOnSwitchChangedListener(new SlideSwitch.OnSwitchChangedListener() {
			@Override
			public void onSwitchChanged(SlideSwitch obj, int status) {
				switch (status) {
				case SlideSwitch.SWITCH_ON:// 开启

					break;
				case SlideSwitch.SWITCH_OFF:// 关闭

					break;
				case SlideSwitch.SWITCH_SCROLING:
					break;
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		// case R.id.setting_msg_rl:
		// break;

		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

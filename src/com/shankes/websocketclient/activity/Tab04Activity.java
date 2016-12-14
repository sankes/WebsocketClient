package com.shankes.websocketclient.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shankes.app.ExitActivity;
import com.shankes.volley.customview.CircleNetworkImageView;
import com.shankes.volley.util.ImageLoaderUtil;
import com.shankes.websocketclient.R;
import com.shankes.websocketclient.control.user.domain.UserInfo;

public class Tab04Activity extends ExitActivity {

	private CircleNetworkImageView mTab04UserIconCNIV;// 头像
	private TextView mTab04UserNameTV;// 用户名

	private RelativeLayout turingRelativeLayout;
	private RelativeLayout settingRelativeLayout;
	private RelativeLayout aboutRelativeLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 加载头像
		String netWorkImageViewUrl = "https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/skin/207.jpg?2";
		UserInfo userInfo = new UserInfo(null, "小雪", netWorkImageViewUrl, 23, "girl");
		loadUserInfo(userInfo);
	}

	@Override
	protected int getContentView() {
		return R.layout.activity_tab04;
	}

	@Override
	protected void initView() {
		mTab04UserIconCNIV = (CircleNetworkImageView) findViewById(R.id.cniv_tab_04_top_user_icon);// 头像
		mTab04UserNameTV = (TextView) findViewById(R.id.tv_tab_04_top_user_name);// 用户名

		turingRelativeLayout = (RelativeLayout) findViewById(R.id.rl_tab04_middle_turing);
		settingRelativeLayout = (RelativeLayout) findViewById(R.id.rl_tab04_middle_setting);
		aboutRelativeLayout = (RelativeLayout) findViewById(R.id.rl_tab04_middle_about);
	}

	@Override
	protected void initEventClick() {
		mTab04UserIconCNIV.setOnClickListener(this);
		mTab04UserNameTV.setOnClickListener(this);

		turingRelativeLayout.setOnClickListener(this);
		settingRelativeLayout.setOnClickListener(this);
		aboutRelativeLayout.setOnClickListener(this);
	}

	@Override
	protected void initEventTouch() {

	}

	/**
	 * 加载用户信息
	 * 
	 * @param userInfo
	 *            用户信息
	 */
	private void loadUserInfo(UserInfo userInfo) {
		ImageLoaderUtil.setNetWorkImageView(this, userInfo.getUserIcon(), mTab04UserIconCNIV, R.drawable.ic_launcher,
				R.drawable.ic_launcher);
		mTab04UserNameTV.setText(userInfo.getUserName());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cniv_tab_04_top_user_icon:
		case R.id.tv_tab_04_top_user_name:
			intent.setClass(this, LoginActivity.class);
			startActivity(intent);
			break;
		case R.id.rl_tab04_middle_turing:
			intent.setClass(this, TuringChatActivity.class);
			startActivity(intent);
			break;
		case R.id.rl_tab04_middle_setting:
			intent.setClass(this, SettingActivity.class);
			startActivity(intent);
			break;
		case R.id.rl_tab04_middle_about:
			intent.setClass(this, AboutActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tab04, menu);
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

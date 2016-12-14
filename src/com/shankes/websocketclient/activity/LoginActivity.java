package com.shankes.websocketclient.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.shankes.util.LogUtil;
import com.shankes.util.ToastUtil;
import com.shankes.websocketclient.R;
import com.shankes.websocketclient.control.common.domain.MsgType;
import com.shankes.websocketclient.control.common.domain.WebSocketInfo;
import com.shankes.websocketclient.control.common.util.WebSocketMessage;
import com.shankes.websocketclient.control.common.util.WebSocketUtil;

public class LoginActivity extends Activity implements OnClickListener {

	private final String TAG = "LOGIN";

	private ScrollView mLoginFormSV;

	private ProgressBar mLoginProgressBar;
	private AutoCompleteTextView mLoginUserNameAutoTV;
	private EditText mLoginPswdET;
	private Button mLoginSignInBtn;

	private static LoginActivity instance = null;

	public synchronized static LoginActivity getInstance() {
		return instance;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		instance = this;
		// 初始化控件
		initView();
		// 初始化点击事件
		initEventClick();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		mLoginFormSV = (ScrollView) findViewById(R.id.login_form);

		mLoginProgressBar = (ProgressBar) findViewById(R.id.login_progress);
		mLoginUserNameAutoTV = (AutoCompleteTextView) findViewById(R.id.login_user_name);
		mLoginPswdET = (EditText) findViewById(R.id.login_password);
		mLoginSignInBtn = (Button) findViewById(R.id.login_sign_in_button);
	}

	/**
	 * 初始化点击事件
	 */
	private void initEventClick() {
		mLoginSignInBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_sign_in_button:
			loginCheck();
			break;

		default:
			break;
		}
	}

	/**
	 * 登录前验证
	 */
	private void loginCheck() {
		String userName = getUserName();
		String password = getPassword();
		if (userName != null && !"".equalsIgnoreCase(userName)) {
			if (password != null && !"".equalsIgnoreCase(password)) {
				login(userName, password);
			} else {
				LogUtil.e(TAG, getResources().getString(R.string.error_invalid_password));
				ToastUtil.showShort(this, R.string.error_invalid_password);
			}
		} else {
			LogUtil.e(TAG, getResources().getString(R.string.error_invalid_username));
			ToastUtil.showShort(this, R.string.error_invalid_username);
		}
	}

	/**
	 * 登录
	 */
	private void login(String userName, String password) {
		// 显示登录等待
		mLoginFormSV.setVisibility(View.GONE);
		mLoginProgressBar.setVisibility(View.VISIBLE);
		// 用户名,密码都不为空的话,发送登录信息
		WebSocketUtil.setSenderId(userName);
		WebSocketUtil.setType(MsgType.LOGIN);
		WebSocketInfo webSocketInfo = new WebSocketInfo(WebSocketUtil.getSenderId(), WebSocketUtil.getType());
		WebSocketMessage.sendWebSocketMessage(this, webSocketInfo);
	}

	/**
	 * 获取用户名
	 * 
	 * @return 用户名
	 */
	private String getUserName() {
		String userName = mLoginUserNameAutoTV.getText().toString().trim();
		return userName;
	}

	/**
	 * 获取密码
	 * 
	 * @return 密码
	 */
	private String getPassword() {
		String password = mLoginPswdET.getText().toString().trim();
		return password;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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

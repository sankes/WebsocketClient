package com.shankes.app;

import android.app.Activity;
import android.os.Bundle;
import butterknife.ButterKnife;

public abstract class BaseActivity extends Activity {

	protected abstract int getContentView();

	protected abstract void init();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getContentView());
		// 绑定
		ButterKnife.bind(this);
		init();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 解除绑定
		ButterKnife.unbind(this);
	}
}

package com.shankes.websocketclient.activity;

import android.os.Bundle;
import butterknife.OnClick;

import com.shankes.app.BaseActivity;
import com.shankes.websocketclient.R;

public class RadarActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	protected int getContentView() {
		return R.layout.activity_radar;
	}

	@Override
	protected void init() {

	}

	@OnClick(R.id.exit)
	public void exit() {
		this.finish();
	}
}

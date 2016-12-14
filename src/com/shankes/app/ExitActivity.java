package com.shankes.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

import com.shankes.util.ToastUtil;

public abstract class ExitActivity extends Activity implements OnClickListener, OnTouchListener {

	/**
	 * 意图
	 */
	protected Intent intent = new Intent();

	/**
	 * 获取xml视图文件
	 * 
	 * @return
	 */
	protected abstract int getContentView();

	/**
	 * 初始化控件
	 */
	protected abstract void initView();

	/**
	 * 初始化点击事件
	 */
	protected abstract void initEventClick();

	/**
	 * 初始化点击事件
	 */
	protected abstract void initEventTouch();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getContentView());
		initView();
		initEventClick();
		initEventTouch();
	}

	protected int mBackKeyPressedTimes = 0;

	/*
	 * 点击Back按钮退出事件
	 * 
	 * @author shankes
	 */
	@Override
	public void onBackPressed() {
		if (mBackKeyPressedTimes == 0) {
			ToastUtil.showShort(this, "再按一次退出");
			mBackKeyPressedTimes = 1;
			new Thread() {
				@Override
				public void run() {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						mBackKeyPressedTimes = 0;
					}
				}
			}.start();
			return;
		} else {
			finish();
		}
	}
}

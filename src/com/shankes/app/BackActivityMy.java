package com.shankes.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

public abstract class BackActivityMy extends Activity implements OnClickListener, OnTouchListener {

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

	// BaseActivity
	@SuppressWarnings("unchecked")
	protected <T extends View> T $(@IdRes int resId) {
		return (T) super.findViewById(resId);
	}
}

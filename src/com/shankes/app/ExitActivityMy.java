package com.shankes.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

import com.shankes.util.ToastUtil;

public abstract class ExitActivityMy extends Activity implements OnClickListener, OnTouchListener {

	/**
	 * 意图
	 */
	protected Intent intent = new Intent();

	/**
	 * 回退时需要点击Back按钮的次数,默认一次
	 */
	private BackPressedType mBackPressedType = BackPressedType.ONCE;

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

	protected void setBackPressedType(BackPressedType backPressedType) {
		mBackPressedType = backPressedType;
	}

	/**
	 * BaseActivity, 使用示例:TextView tv=$(R.id.tv_name);
	 * 
	 * @param resId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T extends View> T $(@IdRes int resId) {
		return (T) super.findViewById(resId);
	}

	private int mBackKeyPressedTimes = 0;

	/*
	 * 点击Back按钮退出事件
	 * 
	 * @author shankes
	 */
	@Override
	public void onBackPressed() {
		switch (mBackPressedType) {
		case ONCE:// 点击一次Back按钮退出
			super.onBackPressed();
			break;
		case TWICE:// 点击两次Back按钮退出
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
			break;
		}
	}
	
	/**
	 * 
	 * Back按钮点击次数
	 * 
	 * @author shankes
	 * 
	 * @data 2016-11-30
	 */
	public enum BackPressedType {
		ONCE, // 点击一次Back按钮退出
		TWICE, // 点击两次Back按钮退出
	}
}

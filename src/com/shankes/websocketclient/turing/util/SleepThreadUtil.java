package com.shankes.websocketclient.turing.util;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by shankes on 2016/7/19.
 */
public class SleepThreadUtil extends Thread {

	private long mSleepTime = 0;// 默认睡眠时间为0
	private int mMsgWhat = 0x10000;// 默认发送的信息为0x10000
	private Handler mHandler = null;// 默认为空
	private SleepThreadCallBack mCallBack = null;
	private Bundle mBundle = null;

	public interface SleepThreadCallBack {
		void onSleepComplete();
	}

	/**
	 * 有睡眠后的回调函数,睡眠后不发送消息
	 * 
	 * @param sleepTime
	 *            睡眠时间
	 * @param sleepThreadCallBack
	 *            睡眠后回调的函数
	 */
	public SleepThreadUtil(long sleepTime, SleepThreadCallBack sleepThreadCallBack) {
		this.mSleepTime = sleepTime;
		this.mCallBack = sleepThreadCallBack;
		start();
	}

	/**
	 * 睡眠后发送消息,睡眠后没有有回调函数
	 * 
	 * @param sleepTime
	 *            睡眠时间
	 * @param msgWhat
	 *            睡眠后发送消息的识别码
	 * @param handler
	 *            睡眠后发送消息的handler,为空则不发送消息
	 * @param bundle
	 *            睡眠后发送消息的携带的数据,可以为空
	 */
	public SleepThreadUtil(long sleepTime, int msgWhat, Handler handler, Bundle bundle) {
		this.mSleepTime = sleepTime;
		this.mMsgWhat = msgWhat;
		this.mHandler = handler;
		this.mBundle = bundle;
		start();
	}

	/**
	 * 睡眠后有回调函数,并可以睡眠后发送消息
	 * 
	 * @param sleepTime
	 *            睡眠时间
	 * @param msgWhat
	 *            睡眠后发送消息的识别码
	 * @param handler
	 *            睡眠后发送消息的handler,为空则不发送消息
	 * @param sleepThreadCallBack
	 *            睡眠后触发的函数,为空则默认不触发
	 * @param bundle
	 *            睡眠后发送消息的携带的数据,可以为空
	 */
	public SleepThreadUtil(long sleepTime, int msgWhat, Handler handler, SleepThreadCallBack sleepThreadCallBack,
			Bundle bundle) {
		this.mSleepTime = sleepTime;
		this.mMsgWhat = msgWhat;
		this.mHandler = handler;
		this.mCallBack = sleepThreadCallBack;
		this.mBundle = bundle;
		start();
	}

	@Override
	public void run() {
		super.run();
		Looper.prepare();
		try {
			sleep(mSleepTime);
			if (mCallBack != null) {
				mCallBack.onSleepComplete();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (mHandler != null) {
			Message msg = new Message();
			msg.what = mMsgWhat;
			if (mBundle != null) {
				msg.setData(mBundle);
			}
			if (mHandler != null) {
				mHandler.sendMessage(msg);
			}
		}
	}
}

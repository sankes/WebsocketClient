package com.shankes.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by shankes on 2016/7/19.
 */
public class SleepThread extends Thread {

	private long mSleepTime = 0;// 默认睡眠时间为0
	private int mMsgWhat = 0x10000;// 默认发送的信息为0x10000
	private Handler mHandler = null;// 默认为空

	public SleepThread(long sleepTime, int msgWhat, Handler handler) {
		this.mSleepTime = sleepTime;
		this.mMsgWhat = msgWhat;
		this.mHandler = handler;
		start();
	}

	@Override
	public void run() {
		super.run();
		Looper.prepare();
		try {
			sleep(mSleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Message msg = new Message();
		msg.what = mMsgWhat;
		if (mHandler != null) {
			mHandler.sendMessage(msg);
		}
	}
}

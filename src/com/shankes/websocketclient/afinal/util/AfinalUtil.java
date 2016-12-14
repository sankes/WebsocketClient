package com.shankes.websocketclient.afinal.util;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;

import com.shankes.app.App;

/**
 * 使用afinal快速开发框架需要有以下权限
 * 
 * <uses-permission android:name="android.permission.INTERNET" />
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 * 
 * @author shankes
 * 
 * @data 2016-11-3
 */
public class AfinalUtil {

	// 1.数据库操作对象
	private static FinalDb finalDb;

	// 2.下载工具对象
	private static FinalHttp finalHttp;
	// 默认超时时长为10秒
	private static final int TIME_OUT_DEFAULT = 10 * 1000;

	// 3.加载图片工具对象
	private static FinalBitmap finalBitmap;

	public synchronized static FinalDb getFinalDb() {
		if (finalDb == null) {
			finalDb = FinalDb.create(App.getInstance());
		}
		return finalDb;
	}

	public synchronized static FinalHttp getFinalHttp() {
		if (finalHttp == null) {
			finalHttp = new FinalHttp();
			finalHttp.configTimeout(TIME_OUT_DEFAULT);
		}
		return finalHttp;
	}

	public synchronized static FinalBitmap getFinalBitmap() {
		if (finalBitmap == null) {
			finalBitmap = FinalBitmap.create(App.getInstance());
		}
		return finalBitmap;
	}
}

package com.shankes.volley.cache;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class MyBitmapCache implements ImageCache {
	/*
	 * 内存缓存:把图片暂存在内存中(不是永久的)。目的：为了提升程序的流畅度，同时节省用户的流量
	 * 
	 * 本地缓存:把图片持久化(永久保存在外部的存储介质中)。目的：在程序没有网络的时候，依然有数据可显示
	 * 
	 * 
	 * 使用Volley缓存框架实现图片的3级缓存：
	 * 
	 * L1、LruCache (内存缓存)
	 * 
	 * Android系统对每一个应用程序有一个固定大小的内存分配(16M或32M)
	 * 
	 * 这些存储空间有一部分是专门用于存放图片的，当程序不断的往内存中加载图片的操作，
	 * 
	 * 达到系统为存放图片分配的内存的峰值之后，程序就会报OOM错误，被系统强制退出。
	 * 
	 * 在Android3.0之前，程序中对图片的内存缓存都是由开发者自行控制，这种操作方式比较容易导致OOM
	 * 
	 * 因为实际开发的需求，Google在Android3.0引入LruCache类来实现内存的管理。
	 * 
	 * LruCache是一个在内存中缓存数据的容器，该容器的特点：1、可以指定容器的容量
	 * 
	 * 2、基于Lru(最近最少使用)算法实现对容器中的数据的管理
	 * 
	 * L2、SoftReference
	 * (内存缓存)(软引用，当LruCache中的数据满的时候会有一些数据被踢出来，踢出来的数据有两种情况，一种是用户自己将数据踢出来的，这时这种数据是
	 * 真的不需要的
	 * ，无需放到softReference中，第二种是一级缓存中放不下了，将会踢出一些数据，这些数据有可能还是我们需要的，需要将他放在二级缓存中缓存起来
	 * )
	 * 
	 * L3、外部存储介质 (本地缓存)
	 */
	@SuppressWarnings("rawtypes")
	private LruCache bitmapLruCache;// 创建强引用的容器（一级缓存）
	private HashMap<String, SoftReference<Bitmap>> allEvictedHashMap;// 创建软引用容器（二级引用）
	private static MyBitmapCache myCache;// 为了不创建多个缓存本类要使用单例模式
	private static Context context;

	public static MyBitmapCache getMyImageCache(Context context) {
		if (myCache == null) {
			myCache = new MyBitmapCache(context);
		}
		return myCache;
	}

	private MyBitmapCache(Context context) {
		this.context = context;
		// 计算程序分配到的总内存
		long totalMemory = Runtime.getRuntime().maxMemory();
		// 实例化一二级缓存
		allEvictedHashMap = new HashMap<String, SoftReference<Bitmap>>();
		bitmapLruCache = new LruCache<String, Bitmap>((int) (totalMemory / 8)) {
			// 本来官方提供的缓存分配方法中，缓存是按个数来计算，这种计算方式容易让内存溢出（oom）,但是可以通过sizeof方法更改计算方法，该成计算图片的大小
			@Override
			protected int sizeOf(String key, Bitmap value) {
				// TODO Auto-generated method stub
				// 计算每张图片的大小
				int rowByte = value.getRowBytes();// 获取图片每一行的字节数
				int height = value.getHeight();// 图片的高度
				int perBitmapSize = rowByte * height;// 计算图片的总容量

				return perBitmapSize;
			}

			// 显示指明从容器中被移除的图片的释放方式
			// 参数一：表示数据是否是因为一级缓存不足而被踢出的 参数二：图片对应的key值，参数三：旧的数据，参数四:新的数据
			@Override
			protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
				// TODO Auto-generated method stub
				super.entryRemoved(evicted, key, oldValue, newValue);
				// 缓存因容量不够而被踢出的图片对象(Volley3级缓存中的第2级缓存L2)
				if (evicted) {// true则把oldValue存入软引用
					SoftReference<Bitmap> bitmapReference = new SoftReference<Bitmap>(oldValue);
					allEvictedHashMap.put(key, bitmapReference);
				}
			}
		};
	}

	/**
	 * 当使用volley框架下载图片的时候并不是马上将下载的，它会先去查看各级缓存是否有，如果有将不会相应请求，如果没有将响应下载请求
	 */
	@Override
	public Bitmap getBitmap(String url) {
		// 取已经缓存图片
		Bitmap bitmap1 = (Bitmap) bitmapLruCache.get(url);
		if (bitmap1 != null) {
			return bitmap1;
		}
		// 获取二级缓存的图片
		SoftReference<Bitmap> aBitmapReference = allEvictedHashMap.get(url);
		if (aBitmapReference != null) {
			Bitmap bitmap2 = aBitmapReference.get();
			if (bitmap2 != null) {
				// 如果有数据
				bitmapLruCache.put(url, bitmap2);// 把数据从新添加到一级缓存中
				return bitmap2;
			}
		}

		// 取得三级缓存中的图片(取得本地图片)
		File cacheFile = getCacheFile();
		readImage(url, cacheFile);

		return null;
	}

	/**
	 * 读取本地缓存图片
	 * 
	 * @param url
	 * @param cacheFile
	 */
	@SuppressWarnings("unchecked")
	private Bitmap readImage(String url, File cacheFile) {
		String[] str = url.split("/");
		try {
			FileInputStream fis = new FileInputStream(cacheFile.getAbsolutePath() + File.separator
					+ str[str.length - 1]);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] tmp = new byte[1024];
			int len = 0;
			while ((len = fis.read(tmp)) != -1) {
				bos.write(tmp, 0, len);
			}
			byte[] imgData = bos.toByteArray();
			Bitmap bitmap3 = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
			bitmapLruCache.put(url, bitmap3);// 把存在本地的图片缓存到内存中
			return bitmap3;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	private File getCacheFile() {
		File cacheFile;
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			// 在SD卡上创建缓存文件夹 sd卡路径/android/data/包名/cache
			File sdCache = context.getExternalCacheDir();
			cacheFile = sdCache;
		} else {
			// 手机自带的存储空间中创建缓存文件夹 data/data/包名/cache
			File internalCache = context.getCacheDir();
			cacheFile = internalCache;
		}
		return cacheFile;
	}

	// 缓存图片
	@SuppressWarnings("unchecked")
	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		// TODO Auto-generated method stub
		// 实现第一级缓存
		bitmapLruCache.put(url, bitmap);

		// 实现第三级缓存
		File cacheFile = getCacheFile();
		saveImage(url, bitmap, cacheFile);

	}

	// 将图片写入本地
	private void saveImage(String url, Bitmap bitmap, File cacheFile) {
		// TODO Auto-generated method stub
		String[] str = url.split("/");
		try {
			FileOutputStream fos = new FileOutputStream(cacheFile.getAbsolutePath() + File.separator
					+ str[str.length - 1]);
			bitmap.compress(CompressFormat.JPEG, 100, fos);// 将图片写入到本地
															// 参数一：图片的格式,参数二：保存的质量,
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

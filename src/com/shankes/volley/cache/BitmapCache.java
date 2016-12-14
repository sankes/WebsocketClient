package com.shankes.volley.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

/**
 * 简单的缓存(暂时未使用)
 * 
 * @author shankesmile
 */
public class BitmapCache implements ImageCache {

	public LruCache<String, Bitmap> cache;
	public int maxSize = 10 * 1024 * 1024;// 最大缓存字节数,10M

	public BitmapCache() {
		cache = new LruCache<String, Bitmap>(maxSize) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				// TODO Auto-generated method stub
				return value.getRowBytes() * value.getHeight();
			}
		};
	}

	@Override
	public Bitmap getBitmap(String arg0) {
		return cache.get(arg0);
	}

	@Override
	public void putBitmap(String arg0, Bitmap arg1) {
		cache.put(arg0, arg1);
	}

}

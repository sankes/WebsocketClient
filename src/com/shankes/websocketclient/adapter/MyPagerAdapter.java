package com.shankes.websocketclient.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyPagerAdapter extends PagerAdapter {
	// private Context mContext;
	// private LayoutInflater mInflater;
	private List<View> mPageViews;

	public MyPagerAdapter(Context context, List<View> pageViews) {
		// TODO Auto-generated constructor stub
		// this.mContext = context;
		// this.mInflater = LayoutInflater.from(mContext);
		this.mPageViews = pageViews;
	}

	// 显示数目
	@Override
	public int getCount() {
		return mPageViews.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return super.getItemPosition(object);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(mPageViews.get(position));
	}

	/***
	 * 初始化各项
	 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(mPageViews.get(position));
		return mPageViews.get(position);
	}
}

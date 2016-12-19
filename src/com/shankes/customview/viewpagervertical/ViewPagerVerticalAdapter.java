package com.shankes.customview.viewpagervertical;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shankes.websocketclient.R;

public class ViewPagerVerticalAdapter extends PagerAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private List<String> mTitles;
	private List<View> mListView;

	public ViewPagerVerticalAdapter(Context context, List<String> titles) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mTitles = titles;
		this.mListView = new ArrayList<View>();

		for (String title : titles) {
			View view = mInflater.inflate(R.layout.viewpager_vertical_item, null);
			TextView textView = (TextView) view.findViewById(R.id.viewpager_vertical_item_title);
			textView.setText(title);
			mListView.add(view);
		}
	}

	@Override
	public int getCount() {
		return mTitles.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(mListView.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(mListView.get(position));
		return mListView.get(position);
	}
}

package com.shankes.websocketclient.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;

import com.shankes.customview.viewpagervertical.ViewPagerVertical;
import com.shankes.customview.viewpagervertical.ViewPagerVerticalAdapter;
import com.shankes.websocketclient.R;

public class ViewPagerVerticalActivity extends Activity {

	private ViewPagerVertical mViewPagerVertical;
	private ViewPagerVerticalAdapter mViewPagerVerticalAdapter;

	private List<String> mTitles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewpager_vertical);

		initView();
		initViewPagerVertical();
	}

	private void initView() {
		mViewPagerVertical = (ViewPagerVertical) findViewById(R.id.viewPagerVertical);
	}

	private void initViewPagerVertical() {
		mTitles = new ArrayList<String>();
		mTitles.add("1");
		mTitles.add("2");
		mViewPagerVerticalAdapter = new ViewPagerVerticalAdapter(this, mTitles);
		mViewPagerVertical.setAdapter(mViewPagerVerticalAdapter);
	}
}

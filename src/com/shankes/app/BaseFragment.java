package com.shankes.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

	public abstract int getContentView();

	protected abstract void initView(Bundle savedInstanceState);

	protected Context mContext;
	protected View mRootView;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		this.mRootView = inflater.inflate(getContentView(), container, false);
		// 绑定
		ButterKnife.bind(this, mRootView);
		this.mContext = getActivity();
		initView(savedInstanceState);
		return mRootView;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		// 解绑
		ButterKnife.unbind(this);
	}
}

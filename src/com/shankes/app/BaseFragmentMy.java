package com.shankes.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragmentMy extends Fragment {

	public abstract int getContentView();

	protected abstract void initView();

	protected Context mContext;
	protected View mRootView;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		this.mRootView = inflater.inflate(getContentView(), container, false);
		this.mContext = getActivity();
		initView();
		return mRootView;
	}

	// BaseFragment
	@SuppressWarnings("unchecked")
	public <T extends View> T $(View layoutView, @IdRes int resId) {
		return (T) layoutView.findViewById(resId);
	}
}

package com.shankes.websocketclient.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shankes.websocketclient.R;
import com.shankes.websocketclient.control.joke.domain.JokeInfo;

public class JokeAdapter extends BaseAdapter {

	private Context mContext;
	private List<JokeInfo> mData;
	private LayoutInflater mInflater;

	public JokeAdapter(Context context, List<JokeInfo> data) {
		super();
		this.mContext = context;
		this.mData = data;
		this.mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mData.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		JokeInfo jokeInfo = mData.get(position);
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.joke_list_item, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.mJokeContentTV = (TextView) convertView.findViewById(R.id.joke_list_item_content);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.mJokeContentTV.setText(jokeInfo.getResult().getContent());
		return convertView;
	}

	private class ViewHolder {
		TextView mJokeContentTV;
	}
}

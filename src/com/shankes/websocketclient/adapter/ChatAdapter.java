package com.shankes.websocketclient.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.shankes.websocketclient.R;
import com.shankes.websocketclient.control.chat.domain.ChatInfo;

public class ChatAdapter extends BaseAdapter {

	private Context mContext;
	private List<ChatInfo> mData;
	private LayoutInflater mInflater;
	private ListView mListView;

	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
		mListView.setSelection(ListView.FOCUS_DOWN);// 自动刷新到底部
	}

	public ChatAdapter(Context context, List<ChatInfo> data, ListView listView) {
		this.mContext = context;
		this.mData = data;
		this.mInflater = LayoutInflater.from(mContext);
		this.mListView = listView;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mData.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public int getItemViewType(int position) {
		int result = 0;
		switch (mData.get(position).getMessageType()) {
		case FROM:
			result = 0;
			break;
		case TO:
			result = 1;
			break;
		default:
			break;
		}
		return result;
	}

	/*
	 * 两种不同布局
	 */
	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ChatInfo chatInfo = mData.get(position);
		ViewHolder viewHolder = null;
		if (convertView == null) {
			switch (chatInfo.getMessageType()) {
			case FROM:
				convertView = mInflater.inflate(R.layout.activity_chat_middle_item_from, parent, false);
				viewHolder = new ViewHolder();
				viewHolder.mTimeTextView = (TextView) convertView.findViewById(R.id.chat_item_from_time);
				viewHolder.mMsgTextView = (TextView) convertView.findViewById(R.id.chat_item_from_content_textview);
				break;
			case TO:
				convertView = mInflater.inflate(R.layout.activity_chat_middle_item_to, parent, false);
				viewHolder = new ViewHolder();
				viewHolder.mTimeTextView = (TextView) convertView.findViewById(R.id.chat_item_to_time);
				viewHolder.mMsgTextView = (TextView) convertView.findViewById(R.id.chat_item_to_content_textview);
				break;

			default:
				break;
			}
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.mTimeTextView.setText(chatInfo.getTime());
		viewHolder.mMsgTextView.setText(chatInfo.getContent());

		return convertView;
	}

	private class ViewHolder {
		TextView mTimeTextView;
		TextView mMsgTextView;
	}

}

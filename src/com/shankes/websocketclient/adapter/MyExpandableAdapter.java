package com.shankes.websocketclient.adapter;

import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.shankes.volley.customview.CircleNetworkImageView;
import com.shankes.volley.util.ImageLoaderUtil;
import com.shankes.websocketclient.R;
import com.shankes.websocketclient.control.user.domain.GroupInfo;
import com.shankes.websocketclient.control.user.domain.UserInfo;

public class MyExpandableAdapter extends BaseExpandableListAdapter {

	private Context mContext;
	private List<GroupInfo> mGroupInfosList;

	private LayoutInflater mInflater;

	public MyExpandableAdapter(Context context, List<GroupInfo> groupInfosList) {
		this.mContext = context;
		this.mGroupInfosList = groupInfosList;
		mInflater = LayoutInflater.from(mContext);
	}

	public Object getChild(int groupPosition, int childPosition) {
		return mGroupInfosList.get(groupPosition).getUserInfos().get(childPosition);
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public int getChildrenCount(int groupPosition) {
		return mGroupInfosList.get(groupPosition).getUserInfos().size();
	}

	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		UserInfo userInfo = mGroupInfosList.get(groupPosition).getUserInfos().get(childPosition);
		// TODO View stub to create Children 's View
		ChildViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.friend_list_child_item, parent, false);
			viewHolder = new ChildViewHolder();
			viewHolder.friendListChildUserIcon = (CircleNetworkImageView) convertView
					.findViewById(R.id.friend_list_child_item_user_icon);
			viewHolder.friendListChildUserName = (TextView) convertView
					.findViewById(R.id.friend_list_child_item_user_name);
			viewHolder.friendListChildChatContent = (TextView) convertView
					.findViewById(R.id.friend_list_child_item_chat_content);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ChildViewHolder) convertView.getTag();
		}
		ImageLoaderUtil.setNetWorkImageView(mContext, userInfo.getUserIcon(), viewHolder.friendListChildUserIcon,
				R.mipmap.headicon_girl, R.mipmap.headicon_girl);
		viewHolder.friendListChildUserName.setText(userInfo.getUserName());
		return convertView;
	}

	class ChildViewHolder {
		CircleNetworkImageView friendListChildUserIcon;
		TextView friendListChildUserName;
		TextView friendListChildChatContent;
	}

	// group method stub
	public Object getGroup(int groupPosition) {
		return mGroupInfosList.get(groupPosition);
	}

	public int getGroupCount() {
		return mGroupInfosList.size();
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		GroupInfo groupInfo = mGroupInfosList.get(groupPosition);
		return generateGroupView(groupInfo);
	}

	// TODO View stub to create Group's View
	public View generateGroupView(GroupInfo groupInfo) {
		// Layout parameters for the ExpandableListView
		AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		final TextView textView = new TextView(mContext);
		textView.setLayoutParams(layoutParams);
		// Center the text vertically
		textView.setGravity(Gravity.CENTER_VERTICAL);
		// Set the text starting position
		textView.setPadding(52, 10, 10, 10);
		textView.setTextSize(20);
		textView.setText(groupInfo.getGroupName() + "(" + groupInfo.getUserInfos().size() + ")");
		return textView;
	}

	public boolean hasStableIds() {
		return false;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}

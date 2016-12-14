package com.shankes.websocketclient.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.shankes.app.ExitActivityMy;
import com.shankes.websocketclient.R;
import com.shankes.websocketclient.adapter.MyExpandableAdapter;
import com.shankes.websocketclient.control.user.domain.GroupInfo;
import com.shankes.websocketclient.control.user.domain.UserInfo;

public class Tab01Activity extends ExitActivityMy implements OnChildClickListener {

	private ExpandableListView mFriendExpandableListView;
	private ExpandableListAdapter mMyExpandableListAdapter;
	private List<GroupInfo> groupInfosList;// 群组人员

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化ExpandableListView
		initExpandableListView();
	}

	@Override
	protected int getContentView() {
		return R.layout.activity_tab01;
	}

	@Override
	protected void initView() {
		mFriendExpandableListView = $(R.id.friend_list_expandable_lv);
	}

	@Override
	protected void initEventClick() {

	}

	@Override
	protected void initEventTouch() {

	}

	/**
	 * 初始化ExpandableListView
	 */
	private void initExpandableListView() {
		// 此处模拟数据
		simulateData();

		mMyExpandableListAdapter = new MyExpandableAdapter(this, groupInfosList);
		mFriendExpandableListView.setAdapter(mMyExpandableListAdapter);
		mFriendExpandableListView.setOnChildClickListener(this);
	}

	private void simulateData() {
		groupInfosList = new ArrayList<GroupInfo>();
		// 群组1
		for (int i = 0; i < 10; i++) {
			String groupId = 10000 + i + "";
			String groupName = "群组" + i;
			String groupDescription = "群组描述" + i;
			List<UserInfo> userInfos = new ArrayList<UserInfo>();
			for (int j = 0; j < 12; j++) {
				String userId = groupId + 00 + j;
				String userName = groupName + "人员" + j;
				String userIcon = "https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/skin/207.jpg?2";
				int age = 15 + j;
				String gender = j % 2 == 0 ? "女" : "男";
				UserInfo userInfo = new UserInfo(userId, userName, userIcon, age, gender);
				userInfos.add(userInfo);
			}
			GroupInfo groupInfo = new GroupInfo(groupId, groupName, groupDescription, userInfos);
			groupInfosList.add(groupInfo);
		}
	}

	/*
	 * 列表子项点击事件
	 */
	@Override
	public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
		// TODO Auto-generated method stub
		intent.setClass(this, ChatActivity.class);
		UserInfo userInfo = (UserInfo) mMyExpandableListAdapter.getChild(groupPosition, childPosition);
		Bundle bundle = new Bundle();
		bundle.putSerializable("userInfo", userInfo);
		intent.putExtras(bundle);
		startActivity(intent);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// case value:
		//
		// break;

		default:
			break;
		}
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tab01, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

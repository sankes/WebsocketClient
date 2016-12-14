package com.shankes.websocketclient.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.shankes.websocketclient.R;
import com.shankes.websocketclient.adapter.ChatAdapter;
import com.shankes.websocketclient.control.chat.domain.ChatInfo;
import com.shankes.websocketclient.control.chat.domain.MessageType;
import com.shankes.websocketclient.control.common.domain.MsgType;
import com.shankes.websocketclient.control.common.domain.WebSocketInfo;
import com.shankes.websocketclient.control.common.util.WebSocketMessage;
import com.shankes.websocketclient.control.common.util.WebSocketUtil;
import com.shankes.websocketclient.control.user.domain.UserInfo;

public class ChatActivity extends Activity implements OnClickListener {

	private UserInfo userInfo;

	private ListView mChatMiddleListView;
	private ChatAdapter mChatAdapter;
	private List<ChatInfo> mData;

	// 该Activity的最外层Layout
	private View rootView;
	private EditText mChatBottomSendEditText;
	private Button mChatBottomSendButton;

	private View chatMiddleRootView;

	// 顶部标题栏
	private Button chatTopLeftBackBtn;
	private TextView chatTopMiddleTitleTV;

	private static ChatActivity instance;

	public static ChatActivity getInstance() {
		if (instance == null) {
			instance = new ChatActivity();
		}
		return instance;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.activity_chat);
		instance = this;
		userInfo = (UserInfo) getIntent().getSerializableExtra("userInfo");

		initView();
		initListViewData();
		initEventClick();
		initRootView();
		initData();
	}

	private void initView() {
		chatMiddleRootView = (View) findViewById(R.id.chat_middle_root);
		rootView = (View) findViewById(R.id.chat_main_root);
		mChatMiddleListView = (ListView) findViewById(R.id.chat_middle_listview);
		mChatBottomSendEditText = (EditText) findViewById(R.id.chat_bottom_send_edittext);
		mChatBottomSendButton = (Button) findViewById(R.id.chat_bottom_send_button);

		// 顶部标题
		chatTopLeftBackBtn = (Button) findViewById(R.id.chat_top_back_button);
		chatTopMiddleTitleTV = (TextView) findViewById(R.id.chat_top_title_textview);
	}

	private void initEventClick() {
		mChatBottomSendButton.setOnClickListener(this);
		// 顶部标题
		chatTopLeftBackBtn.setOnClickListener(this);
	}

	private void initListViewData() {
		mData = new ArrayList<ChatInfo>();
		mChatAdapter = new ChatAdapter(this, mData, mChatMiddleListView);
		mChatMiddleListView.setAdapter(mChatAdapter);
	}

	private void initRootView() {
		// 给该layout设置监听，监听其布局发生变化事件
		rootView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				// 比较Activity根布局与当前布局的大小
				int heightDiff = rootView.getRootView().getHeight() - rootView.getHeight();
				if (heightDiff > 100) {
					// 大小超过100时，一般为显示虚拟键盘事件
					mChatAdapter.notifyDataSetChanged();
					chatMiddleRootView.setBackgroundColor(ContextCompat.getColor(ChatActivity.this, R.color.bluelight));
				} else {
					// 大小小于100时，为不显示虚拟键盘或虚拟键盘隐藏
					chatMiddleRootView.setBackgroundColor(ContextCompat.getColor(ChatActivity.this, R.color.bluelight));
				}
			}
		});
	}

	private void initData() {
		if (userInfo != null) {
			chatTopMiddleTitleTV.setText(userInfo.getUserName());
		}
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.chat_bottom_send_button:
			String infoValue = mChatBottomSendEditText.getText().toString().trim();

			ChatInfo turingBaseInfo = new ChatInfo();
			turingBaseInfo.setMessageType(MessageType.TO);
			turingBaseInfo.setContent(infoValue);
			turingBaseInfo.setTime(SimpleDateFormat.getDateTimeInstance().format(new Date()));
			if (!TextUtils.isEmpty(infoValue)) {
				mData.add(turingBaseInfo);
				mChatAdapter.notifyDataSetChanged();
			}
			mChatBottomSendEditText.setText("");
			sendMsg(infoValue);// 发送消息
			break;
		case R.id.chat_top_back_button:
			onBackPressed();
			break;
		default:
			break;
		}

	}

	/**
	 * 发送消息
	 */
	private void sendMsg(String content) {
		WebSocketUtil.setSenderId("105474");
		WebSocketUtil.setRecvId("105638");
		WebSocketUtil.setType(MsgType.MESSAGE_TO_ONE);
		if (content != null) {
			WebSocketInfo webSocketInfo = new WebSocketInfo(WebSocketUtil.getSenderId(), WebSocketUtil.getRecvId(),
					WebSocketUtil.getType(), content);
			WebSocketMessage.sendWebSocketMessage(this, webSocketInfo);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chat, menu);
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

package com.shankes.websocketclient.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.shankes.app.ExitActivity;
import com.shankes.websocketclient.R;
import com.shankes.websocketclient.control.joke.domain.JokeInfo;
import com.shankes.websocketclient.control.joke.util.JokeUtil;
import com.shankes.websocketclient.swipe.adapter.SwipeAdapter;

public class Tab02Activity extends ExitActivity {

	private SwipeRefreshLayout jokeSwipeRefreshLayout;
	private ListView jokeListView;
	public static BaseAdapter mJokeAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 初始化ListView
		initListView();
		// 初始化swipeRefreshLayout
		initSwipeLayout();
		JokeUtil.getJokes(this);
	}

	@Override
	protected int getContentView() {
		return R.layout.activity_tab02;
	}

	@Override
	protected void initView() {
		jokeSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.joke_swipe_layout);
		jokeListView = (ListView) findViewById(R.id.joke_list_view);
	}

	@Override
	protected void initEventClick() {

	}

	@Override
	protected void initEventTouch() {

	}

	private void initSwipeLayout() {

		// jokeSwipeRefreshLayout.setColorSchemeResources(R.color.blue,
		// R.color.orange, R.color.green, R.color.pink);
		jokeSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
		// jokeSwipeRefreshLayout.setPadding(20, 20, 20, 20);
		// jokeSwipeRefreshLayout.setProgressViewOffset(true, 100, 200);
		// jokeSwipeRefreshLayout.setDistanceToTriggerSync(50);
		// jokeSwipeRefreshLayout.setProgressViewEndTarget(true, 100);
		jokeSwipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				JokeUtil.startGetJokesTask(Tab02Activity.this, 3);
				jokeSwipeRefreshLayout.setRefreshing(false);
			}
		});
	}

	private void initListView() {
		JokeUtil.mData = new ArrayList<JokeInfo>();
		mJokeAdapter = new SwipeAdapter(this);
		jokeListView.setAdapter(mJokeAdapter);
	}

	@Override
	public void onClick(View arg0) {

	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tab02, menu);
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

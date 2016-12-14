package com.shankes.websocketclient.swipe.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.shankes.websocketclient.R;
import com.shankes.websocketclient.control.joke.domain.JokeInfo;
import com.shankes.websocketclient.control.joke.util.JokeUtil;
import com.shankes.websocketclient.swipe.view.SwipeItemLayout;

public class SwipeAdapter extends BaseAdapter {
	private Context mContext = null;
	private LayoutInflater mInflater = null;

	public SwipeAdapter(Context context) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return JokeUtil.mData.size();
	}

	@Override
	public Object getItem(int arg0) {
		return JokeUtil.mData.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(final int position, View contentView, ViewGroup parent) {
		ViewHolder holder = null;
		if (contentView == null) {
			holder = new ViewHolder();
			View itemMainView = mInflater.inflate(R.layout.joke_list_item, null);
			View itemRightMenuView = mInflater.inflate(R.layout.joke_list_item_right_swipe_menu, null);
			// holder.btn = (Button)view02.findViewById(R.id.btn);
			holder.mJokeContentTV = (TextView) itemMainView.findViewById(R.id.joke_list_item_content);
			// 右滑置顶菜单
			holder.itemRightMenuStickBtn = (Button) itemRightMenuView.findViewById(R.id.itemRightMenuStickBtn);
			// 右滑删除菜单
			holder.itemRightMenuDeleteBtn = (Button) itemRightMenuView.findViewById(R.id.itemRightMenuDeleteBtn);
			// 右滑取消菜单
			holder.itemRightMenuCancelBtn = (Button) itemRightMenuView.findViewById(R.id.itemRightMenuCancelBtn);

			contentView = new SwipeItemLayout(itemMainView, itemRightMenuView, null, null);
			contentView.setTag(holder);
		} else {
			holder = (ViewHolder) contentView.getTag();
		}
		initViewHolderItemData(contentView, holder, position);
		return contentView;
	}

	class ViewHolder {
		TextView mJokeContentTV;

		Button itemRightMenuStickBtn = null;
		Button itemRightMenuDeleteBtn = null;
		Button itemRightMenuCancelBtn = null;
	}

	private void initViewHolderItemData(final View contentView, final ViewHolder holder, final int position) {
		final JokeInfo jokeInfo = JokeUtil.mData.get(position);
		// 这里初始化列表项
		holder.mJokeContentTV.setText(jokeInfo.getResult().getContent());
		// 列表项中文字点击事件
		holder.mJokeContentTV.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				showInfo(position, jokeInfo.getResult().getContent());
			}
		});
		// 右滑置顶菜单
		holder.itemRightMenuStickBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				JokeUtil.mData.add(0, JokeUtil.mData.remove(position));
				notifyDataSetChanged();
			}
		});
		// 右滑删除菜单
		holder.itemRightMenuDeleteBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				JokeUtil.mData.remove(position);
				notifyDataSetChanged();
			}
		});
		// 右滑取消菜单
		holder.itemRightMenuCancelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				((SwipeItemLayout) contentView).smoothCloseMenu();
			}
		});
	}

	private void showInfo(int position, String content) {
		new AlertDialog.Builder(mContext)
			.setTitle("Joke" + position)
			.setMessage(content)
			.setPositiveButton("确定", null)
			.show();
	}
}

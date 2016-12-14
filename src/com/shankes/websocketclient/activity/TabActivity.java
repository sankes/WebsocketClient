package com.shankes.websocketclient.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shankes.websocketclient.R;
import com.shankes.websocketclient.adapter.MyPagerAdapter;
import com.shankes.websocketclient.control.common.util.WebSocketUtil;

@SuppressWarnings("deprecation")
public class TabActivity extends ActivityGroup implements OnClickListener {

	// 顶部标题栏
	private Button mTabTopTitleLeftBtn;
	private TextView mTabTopTitleMiddleTV;
	private Button mTabTopTitleRightBtn;
	// 中部ViewPager
	private ViewPager mViewpager;
	private List<View> mPagerViews;
	private MyPagerAdapter mPagerAdapter;

	// 底部菜单
	private LinearLayout mTabBottomMenuLL01;
	private LinearLayout mTabBottomMenuLL02;
	private LinearLayout mTabBottomMenuLL03;
	private LinearLayout mTabBottomMenuLL04;

	private ImageView mTabBottomMenuIV01;
	private ImageView mTabBottomMenuIV02;
	private ImageView mTabBottomMenuIV03;
	private ImageView mTabBottomMenuIV04;

	private TextView mTabBottomMenuTV01;
	private TextView mTabBottomMenuTV02;
	private TextView mTabBottomMenuTV03;
	private TextView mTabBottomMenuTV04;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.activity_tab);

		// 初始化控件
		initView();
		// 初始化点击事件
		initEventClick();
		// 初始化ViewPager
		initViewPager();

		// TODO 建立websocket连接,要放到TabActivity中
		WebSocketUtil.getConnect(this);
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		// 顶部标题栏
		mTabTopTitleLeftBtn = (Button) findViewById(R.id.btn_tab_top_title_left_back);
		mTabTopTitleMiddleTV = (TextView) findViewById(R.id.tv_tab_top_title_middle_01);
		mTabTopTitleRightBtn = (Button) findViewById(R.id.btn_tab_top_title_right_01);
		// 中部ViewPager
		mViewpager = (ViewPager) findViewById(R.id.tab_main_viewpager);
		// 底部菜单
		mTabBottomMenuLL01 = (LinearLayout) findViewById(R.id.ll_tab_bottom_01);
		mTabBottomMenuLL02 = (LinearLayout) findViewById(R.id.ll_tab_bottom_02);
		mTabBottomMenuLL03 = (LinearLayout) findViewById(R.id.ll_tab_bottom_03);
		mTabBottomMenuLL04 = (LinearLayout) findViewById(R.id.ll_tab_bottom_04);

		mTabBottomMenuIV01 = (ImageView) findViewById(R.id.iv_tab_bottom_01);
		mTabBottomMenuIV02 = (ImageView) findViewById(R.id.iv_tab_bottom_02);
		mTabBottomMenuIV03 = (ImageView) findViewById(R.id.iv_tab_bottom_03);
		mTabBottomMenuIV04 = (ImageView) findViewById(R.id.iv_tab_bottom_04);

		mTabBottomMenuTV01 = (TextView) findViewById(R.id.tv_tab_bottom_01);
		mTabBottomMenuTV02 = (TextView) findViewById(R.id.tv_tab_bottom_02);
		mTabBottomMenuTV03 = (TextView) findViewById(R.id.tv_tab_bottom_03);
		mTabBottomMenuTV04 = (TextView) findViewById(R.id.tv_tab_bottom_04);
	}

	/**
	 * 初始化点击事件
	 */
	private void initEventClick() {
		// 顶部标题栏
		mTabTopTitleLeftBtn.setOnClickListener(this);
		mTabTopTitleRightBtn.setOnClickListener(this);
		// 底部菜单
		mTabBottomMenuLL01.setOnClickListener(this);
		mTabBottomMenuLL02.setOnClickListener(this);
		mTabBottomMenuLL03.setOnClickListener(this);
		mTabBottomMenuLL04.setOnClickListener(this);
	}

	/**
	 * 初始化ViewPager
	 */
	private void initViewPager() {
		// 初始化ViewPager每个界面
		mPagerViews = new ArrayList<>();

		View view01 = getLocalActivityManager().startActivity("activity01",
				new Intent(this, Tab01Activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
		View view02 = getLocalActivityManager().startActivity("activity02",
				new Intent(this, Tab02Activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
		View view03 = getLocalActivityManager().startActivity("activity03",
				new Intent(this, Tab03Activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
		View view04 = getLocalActivityManager().startActivity("activity04",
				new Intent(this, Tab04Activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();

		mPagerViews.add(view01);
		mPagerViews.add(view02);
		mPagerViews.add(view03);
		mPagerViews.add(view04);

		mPagerAdapter = new MyPagerAdapter(this, mPagerViews);
		mViewpager.setAdapter(mPagerAdapter);
		mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				bottomMenuSelected(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
		bottomMenuSelected(0);
	}

	/**
	 * 当ViewPager页选中时,底部菜单选中项颜色变化
	 * 
	 * @param arg0
	 *            选中项
	 */
	protected void bottomMenuSelected(int position) {
		// 设置ViewPager当前选中项
		mViewpager.setCurrentItem(position);
		// 初始化底部菜单颜色都为黑色
		bottomMenuInit();
		// 设置当前选中的底部菜单颜色为蓝色
		switch (position) {
		case 0:
			// 顶部标题栏
			mTabTopTitleMiddleTV.setText(R.string.top_title01);
			// 底部菜单
			mTabBottomMenuLL01.setBackgroundResource(R.color.lightblue);
			mTabBottomMenuIV01.setImageResource(R.mipmap.pic_tab_bottom_eyes_blue);
			mTabBottomMenuTV01.setTextColor(ContextCompat.getColor(this, R.color.blue));
			break;
		case 1:
			// 顶部标题栏
			mTabTopTitleMiddleTV.setText(R.string.top_title02);
			// 底部菜单
			mTabBottomMenuLL02.setBackgroundResource(R.color.lightblue);
			mTabBottomMenuIV02.setImageResource(R.mipmap.pic_tab_bottom_gis_blue);
			mTabBottomMenuTV02.setTextColor(ContextCompat.getColor(this, R.color.blue));
			break;
		case 2:
			// 顶部标题栏
			mTabTopTitleMiddleTV.setText(R.string.top_title03);
			// 底部菜单
			mTabBottomMenuLL03.setBackgroundResource(R.color.lightblue);
			mTabBottomMenuIV03.setImageResource(R.mipmap.pic_tab_bottom_alert_blue);
			mTabBottomMenuTV03.setTextColor(ContextCompat.getColor(this, R.color.blue));
			break;
		case 3:
			// 顶部标题栏
			mTabTopTitleMiddleTV.setText(R.string.top_title04);
			// 底部菜单
			mTabBottomMenuLL04.setBackgroundResource(R.color.lightblue);
			mTabBottomMenuIV04.setImageResource(R.mipmap.pic_tab_bottom_info_blue);
			mTabBottomMenuTV04.setTextColor(ContextCompat.getColor(this, R.color.blue));
			break;
		}
	}

	/**
	 * 当ViewPager页选中时,初始化底部菜单选中项颜色
	 */
	private void bottomMenuInit() {
		mTabBottomMenuLL01.setBackgroundResource(R.color.beangreenwhite);
		mTabBottomMenuLL02.setBackgroundResource(R.color.beangreenwhite);
		mTabBottomMenuLL03.setBackgroundResource(R.color.beangreenwhite);
		mTabBottomMenuLL04.setBackgroundResource(R.color.beangreenwhite);

		mTabBottomMenuIV01.setImageResource(R.mipmap.pic_tab_bottom_eyes_black);
		mTabBottomMenuIV02.setImageResource(R.mipmap.pic_tab_bottom_gis_black);
		mTabBottomMenuIV03.setImageResource(R.mipmap.pic_tab_bottom_alert_black);
		mTabBottomMenuIV04.setImageResource(R.mipmap.pic_tab_bottom_info_black);

		mTabBottomMenuTV01.setTextColor(ContextCompat.getColor(this, R.color.black));
		mTabBottomMenuTV02.setTextColor(ContextCompat.getColor(this, R.color.black));
		mTabBottomMenuTV03.setTextColor(ContextCompat.getColor(this, R.color.black));
		mTabBottomMenuTV04.setTextColor(ContextCompat.getColor(this, R.color.black));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_tab_top_title_left_back:// 顶部菜单左一
			break;
		case R.id.btn_tab_top_title_right_01:// 顶部菜单右一
			break;
		case R.id.ll_tab_bottom_01:// 底部菜单01
			bottomMenuSelected(0);
			break;
		case R.id.ll_tab_bottom_02:// 底部菜单02
			bottomMenuSelected(1);
			break;
		case R.id.ll_tab_bottom_03:// 底部菜单03
			bottomMenuSelected(2);
			break;
		case R.id.ll_tab_bottom_04:// 底部菜单04
			bottomMenuSelected(3);
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tab, menu);
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

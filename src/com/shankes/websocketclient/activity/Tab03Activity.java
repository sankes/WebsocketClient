package com.shankes.websocketclient.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;

import com.shankes.app.ExitActivity;
import com.shankes.customview.dashboardview.DashboardView;
import com.shankes.customview.dashboardview.HighlightCR;
import com.shankes.customview.roundprogress.RoundProgressBar;
import com.shankes.customview.satellitemenu.SatelliteMenu;
import com.shankes.customview.satellitemenu.SatelliteMenu.SateliteClickedListener;
import com.shankes.customview.satellitemenu.SatelliteMenuItem;
import com.shankes.customview.waveview.WaveView;
import com.shankes.util.LogUtil;
import com.shankes.util.ToastUtil;
import com.shankes.websocketclient.R;
import com.shankes.websocketclient.activity.grid.sample.MenuStaggeredActivity;
import com.shankes.websocketclient.activity.wheelview.WheelViewActivity;
import com.shankes.websocketclient.flingswipe.sample.FlingSwipeActivity;

public class Tab03Activity extends ExitActivity implements OnClickListener {

	private static final String TAG = "TAB";

	private SeekBar seekBar;
	private WaveView waveView;

	private Button initBtn;

	private SatelliteMenu menu;

	// 圆形进度条
	private RoundProgressBar roundProgressBar;

	// 仪表盘
	private DashboardView dashboardView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initSeekBar();// 初始化滑动控件
		initWaveView();// 初始化波纹
		initSatelliteMenu();// 初始化卫星菜单
		initDashboard();// 初始化仪表盘
	}

	@Override
	protected int getContentView() {
		return R.layout.activity_tab03;
	}

	@Override
	protected void initView() {
		seekBar = (SeekBar) findViewById(R.id.seek_bar);
		waveView = (WaveView) findViewById(R.id.wave_view);
		initBtn = (Button) findViewById(R.id.init_btn);

		menu = (SatelliteMenu) findViewById(R.id.menu);
		roundProgressBar = (RoundProgressBar) findViewById(R.id.roundProgressBar);
		dashboardView = (DashboardView) findViewById(R.id.dashboard_view);
	}

	@Override
	protected void initEventClick() {
		initBtn.setOnClickListener(this);

	}

	@Override
	protected void initEventTouch() {

	}

	/**
	 * 初始化波纹
	 */
	private void initWaveView() {
		// waveView.setProgress(80);
		seekBar.setProgress(seekBar.getProgress() - 1);
		ToastUtil.showShort(this, String.valueOf(seekBar.getProgress()));
		LogUtil.i(TAG, String.valueOf(seekBar.getProgress()));
	}

	/**
	 * 初始化滑动控件
	 */
	private void initSeekBar() {
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				waveView.setProgress(progress);
				roundProgressBar.setProgress(progress);// 圆形进度条
				setDashboardValue(progress);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});
		seekBar.setProgress(seekBar.getProgress() - 1);
	}

	/**
	 * 初始化卫星菜单
	 */
	private void initSatelliteMenu() {
		// Set from XML, possible to programmatically set
		// float distance =
		// TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 170,
		// getResources().getDisplayMetrics());
		// menu.setSatelliteDistance((int) distance);
		// menu.setExpandDuration(500);
		// menu.setCloseItemsOnClick(false);
		// menu.setTotalSpacingDegree(60);
		menu.setMainImage(R.drawable.btn_menu_main_cute_smiley_yellow);// 设置主菜单按钮图片
		List<SatelliteMenuItem> items = new ArrayList<SatelliteMenuItem>();
		items.add(new SatelliteMenuItem(R.drawable.btn_menu_01_star, R.drawable.btn_menu_01_star));
		items.add(new SatelliteMenuItem(R.drawable.btn_menu_02_eat, R.drawable.btn_menu_02_eat));
		items.add(new SatelliteMenuItem(R.drawable.btn_menu_03_alert, R.drawable.btn_menu_03_alert));
		items.add(new SatelliteMenuItem(R.drawable.btn_menu_04_snow, R.drawable.btn_menu_04_snow));
		items.add(new SatelliteMenuItem(R.drawable.btn_menu_05_find, R.drawable.btn_menu_05_find));
		items.add(new SatelliteMenuItem(R.drawable.btn_menu_06_exit, R.drawable.btn_menu_06_exit));
		// items.add(new SatelliteMenuItem(5, R.drawable.sat_item));
		menu.addItems(items);

		menu.setOnItemClickedListener(new SateliteClickedListener() {

			public void eventOccured(int id) {
				Log.i("sat", "Clicked on " + id);
				switch (id) {
				case R.drawable.btn_menu_01_star:
					intent.setClass(Tab03Activity.this, FlingSwipeActivity.class);
					startActivity(intent);
					break;
				case R.drawable.btn_menu_02_eat:
					intent.setClass(Tab03Activity.this, MenuStaggeredActivity.class);
					startActivity(intent);
					break;
				case R.drawable.btn_menu_03_alert:
					intent.setClass(Tab03Activity.this, RadarActivity.class);
					startActivity(intent);
					break;
				case R.drawable.btn_menu_04_snow:
					intent.setClass(Tab03Activity.this, MoveParentActivity.class);
					startActivity(intent);
					break;
				case R.drawable.btn_menu_05_find:
					intent.setClass(Tab03Activity.this, SelectActivity.class);
					startActivity(intent);
					break;
				case R.drawable.btn_menu_06_exit:
					intent.setClass(Tab03Activity.this, ChartActivity.class);
					startActivity(intent);
					break;

				default:
					break;
				}
			}
		});
	}

	/**
	 * 初始化仪表盘
	 */
	private void initDashboard() {
		List<HighlightCR> highlight = new ArrayList<>();
		highlight.add(new HighlightCR(150, 100, Color.GREEN));
		highlight.add(new HighlightCR(250, 80, Color.BLUE));
		highlight.add(new HighlightCR(330, 60, Color.RED));
		dashboardView.setStripeHighlightColorAndRange(highlight);
		dashboardView.setStripeMode(DashboardView.StripeMode.OUTER);
		dashboardView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				taskControl();
			}
		});
	}

	@Override
	public void onClick(View v) {
		// Intent intent = null;
		switch (v.getId()) {
		case R.id.init_btn:
			// waveView.setProgress(80);
			// seekBar.setProgress(seekBar.getProgress() - 1);
			intent.setClass(Tab03Activity.this, WheelViewActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		return false;
	}

	/**
	 * 控制定时任务开启关闭(仪表盘)
	 */
	private void taskControl() {
		if (timer == null) {// 开启定时任务
			timer = new Timer();
			task = new TimerTask() {
				@Override
				public void run() {
					Message message = Message.obtain();
					message.what = 0x11;
					handler.sendMessage(message);
				}
			};
			timer.schedule(task, 0, 530);
		} else {// 取消定时任务
			timer.cancel();
			timer = null;
			task.cancel();
		}
	}

	// (仪表盘)
	private Timer timer = null;
	private TimerTask task = null;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0x11:
				setDashboardRandomValue();
				break;

			default:
				break;
			}
		}
	};

	/**
	 * 在取值范围内随机产生一个数,设置仪表盘的数值为该数(仪表盘)
	 */
	private void setDashboardRandomValue() {
		Random random = new Random();
		int from = dashboardView.getMinValue();
		int to = dashboardView.getMaxValue();
		int ranValue = random.nextInt(to - from) + from; // 在取值范围内随机产生一个数
		dashboardView.setRealTimeValue(ranValue, true, 500);
	}

	/**
	 * 设置仪表盘的数值(仪表盘)
	 * 
	 * @param progress
	 *            1-100
	 */
	private void setDashboardValue(int progress) {
		int from = dashboardView.getMinValue();
		int to = dashboardView.getMaxValue();
		float value = (float) ((to - from) * progress * 0.01 + from);
		dashboardView.setRealTimeValue(value);
	}
}

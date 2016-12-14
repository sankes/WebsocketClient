package com.shankes.websocketclient.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;

import com.shankes.app.ExitActivity;
import com.shankes.customview.roundprogress.RoundProgressBar;
import com.shankes.customview.satellitemenu.SatelliteMenu;
import com.shankes.customview.satellitemenu.SatelliteMenuItem;
import com.shankes.customview.satellitemenu.SatelliteMenu.SateliteClickedListener;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initSeekBar();
		initWaveView();
		initSatelliteMenu();
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
	}

	@Override
	protected void initEventClick() {
		initBtn.setOnClickListener(this);

	}

	@Override
	protected void initEventTouch() {

	}

	private void initWaveView() {
		// waveView.setProgress(80);
		seekBar.setProgress(seekBar.getProgress() - 1);
		ToastUtil.showShort(this, String.valueOf(seekBar.getProgress()));
		LogUtil.i(TAG, String.valueOf(seekBar.getProgress()));
	}

	private void initSeekBar() {
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				waveView.setProgress(progress);
				roundProgressBar.setProgress(progress);// 圆形进度条
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

package com.shankes.websocketclient.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.shankes.customview.chartview.MyChartView;
import com.shankes.websocketclient.R;

public class ChartActivity extends Activity {

	private MyChartView myChartView;
	private List<Integer> list = new ArrayList<>();
	private RelativeLayout relativeLayout;
	private TextView showText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart);
		init();
	}

	private void init() {
		showText = new TextView(getApplicationContext());

		myChartView = (MyChartView) findViewById(R.id.my_chart_view);
		relativeLayout = (RelativeLayout) findViewById(R.id.linearLayout);
		Random random = new Random();
		while (list.size() < 24) {
			int randomInt = random.nextInt(100);
			list.add(randomInt);
		}
		myChartView.setList(list);
		myChartView.setListener(new MyChartView.getNumberListener() {
			@SuppressLint("NewApi")
			@Override
			public void getNumber(int number, int x, int y) {
				relativeLayout.removeView(showText);
				RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
				params.leftMargin = x - 100;
				if (x - 100 < 0) {
					params.leftMargin = 0;
				} else if (x - 100 > relativeLayout.getWidth() - showText.getWidth()) {
					params.leftMargin = relativeLayout.getWidth() - showText.getWidth();
				}
				params.topMargin = 100;
				showText.setLayoutParams(params);
				showText.setTextColor(getResources().getColor(R.color.white));
				showText.setTextSize(10);
				showText.setText("选择的数字为:" + list.get(number * 2) + "," + list.get(number * 2 + 1));
				showText.setBackground(getResources().getDrawable(R.drawable.xml_shape_show_text_bg));
				relativeLayout.addView(showText);

			}
		});

	}

}

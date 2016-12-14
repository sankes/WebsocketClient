package com.shankes.websocketclient.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shankes.customview.selectview.MyFlowLayout;
import com.shankes.customview.selectview.MySelectView;
import com.shankes.websocketclient.R;

public class SelectActivity extends Activity implements MySelectView.getNumberListener {
	private MySelectView mySelectView;
	private MyFlowLayout myFlowLayout;
	private List<String> stringList = new ArrayList<>();
	private TextView numberTxt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select);

		init();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mySelectView.setmStartWidth(0);
	}

	@SuppressLint("NewApi")
	public void init() {

		stringList.add("数据库");
		stringList.add("移动开发");
		stringList.add("前端开发");
		stringList.add("微信小程序");
		stringList.add("服务器开发");
		stringList.add("PHP");
		stringList.add("人工智能");
		stringList.add("大数据");
		mySelectView = (MySelectView) findViewById(R.id.my_selectview);
		myFlowLayout = (MyFlowLayout) findViewById(R.id.my_flowlayout);
		for (String textView : stringList) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			params.setMargins(40, 40, 40, 40);
			TextView showText = new TextView(this);
			showText.setLayoutParams(params);
			showText.setTextColor(getResources().getColor(R.color.green));
			showText.setTextSize(20);
			showText.setText(textView);
			showText.setBackground(getResources().getDrawable(R.drawable.xml_shape_show_text_bg));
			myFlowLayout.addView(showText);
		}
		numberTxt = (TextView) findViewById(R.id.number_txt);
		mySelectView.setListener(this);
	}

	@Override
	public void getNumber(int number) {
		numberTxt.setText("选择的数字为:" + number);
	}
}

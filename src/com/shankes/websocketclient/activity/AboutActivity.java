package com.shankes.websocketclient.activity;

import com.shankes.websocketclient.R;
import com.shankes.websocketclient.R.drawable;
import com.shankes.websocketclient.R.id;
import com.shankes.websocketclient.R.layout;
import com.shankes.websocketclient.R.menu;
import com.shankes.websocketclient.afinal.util.AfinalUtil;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;

public class AboutActivity extends Activity {
	private WebView runWebView = null;
	private ImageView image_view = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);

		// 初始化控件
		initView();
		initWebView();
		initViewData();
	}

	private void initView() {
		runWebView = (WebView) findViewById(R.id.runWebView);
		image_view = (ImageView) findViewById(R.id.image_view);
	}

	private void initWebView() {
		runWebView.setBackgroundColor(1);
		runWebView
				.loadDataWithBaseURL(
						null,
						"<IMG src='http://f.hiphotos.baidu.com/zhidao/pic/item/1e30e924b899a901f13830bc1f950a7b0208f52f.jpg'/>",
						"text/html", "UTF-8", null);
	}

	private void initViewData() {
		String uri = "http://f.hiphotos.baidu.com/zhidao/pic/item/1e30e924b899a901f13830bc1f950a7b0208f52f.gif";
		Bitmap loadingBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		Bitmap loadFailBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		AfinalUtil.getFinalBitmap().display(image_view, uri, 50, 50, loadingBitmap, loadFailBitmap);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about, menu);
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

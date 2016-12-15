package com.shankes.websocketclient.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shankes.app.ExitActivity;
import com.shankes.util.glide.transform.GlideRoundTransform;
import com.shankes.volley.customview.CircleNetworkImageView;
import com.shankes.volley.util.ImageLoaderUtil;
import com.shankes.websocketclient.R;
import com.shankes.websocketclient.control.user.domain.UserInfo;

public class Tab04Activity extends ExitActivity {

	private CircleNetworkImageView mTab04UserIconCNIV;// 头像
	private TextView mTab04UserNameTV;// 用户名

	private RelativeLayout turingRelativeLayout;
	private RelativeLayout settingRelativeLayout;
	private RelativeLayout aboutRelativeLayout;

	private ImageView mImgGlideTest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 加载头像
		String netWorkImageViewUrl = "https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/skin/207.jpg?2";
		UserInfo userInfo = new UserInfo(null, "小雪", netWorkImageViewUrl, 23, "girl");
		loadUserInfo(userInfo);

		initGlideLoadImg();
	}

	@Override
	protected int getContentView() {
		return R.layout.activity_tab04;
	}

	@Override
	protected void initView() {
		mTab04UserIconCNIV = (CircleNetworkImageView) findViewById(R.id.cniv_tab_04_top_user_icon);// 头像
		mTab04UserNameTV = (TextView) findViewById(R.id.tv_tab_04_top_user_name);// 用户名

		turingRelativeLayout = (RelativeLayout) findViewById(R.id.rl_tab04_middle_turing);
		settingRelativeLayout = (RelativeLayout) findViewById(R.id.rl_tab04_middle_setting);
		aboutRelativeLayout = (RelativeLayout) findViewById(R.id.rl_tab04_middle_about);

		mImgGlideTest = (ImageView) findViewById(R.id.img_glide_test);
	}

	private void initGlideLoadImg() {
		// TODO Auto-generated method stub
		// 动态图一张,奔跑的小蓝人
		String urlGif = "http://f.hiphotos.baidu.com/zhidao/pic/item/1e30e924b899a901f13830bc1f950a7b0208f52f.gif";
		String urlYangmi = "https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/skin/207.jpg?2";
		Glide.with(this)// 1.)设置绑定生命周期
				// 2.)简单的加载图片实例
				.load(urlYangmi)
				// 3.)设置动态GIF加载方式
				// .asBitmap()// 显示gif静态图片
				// .asGif()// 显示gif动态图片
				// 4.)设置加载中以及加载失败图片
				.placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher)// 以及加载失败图片
				// 5.)设置跳过内存缓存
				// .skipMemoryCache(true)
				// 6.)设置下载优先级
				.priority(Priority.NORMAL)
				// 7.)设置缓存策略,all:缓存源资源和转换后的资源
				.diskCacheStrategy(DiskCacheStrategy.ALL)
				// 8.)设置加载动画,api也提供了几个常用的动画：比如crossFade()
				// 也可自定义,如 .animate(R.anim.item_alpha_in)
				.crossFade()
				// 9.)设置缩略图支持,先加载缩略图 再加载全图
				.thumbnail(0.1f)
				// 10.)设置动态转换
				// api提供了比如：centerCrop()、fitCenter()等函数
				// 也可以通过自定义Transformation，举例说明：比如一个人圆角转化器
				.transform(new GlideRoundTransform(this, 80))// 动态转换(圆角转化器)
				// .centerCrop()
				// .fitCenter()
				// 11.)加载到ImageView容器中
				.into(mImgGlideTest);
	}

	@Override
	protected void initEventClick() {
		mTab04UserIconCNIV.setOnClickListener(this);
		mTab04UserNameTV.setOnClickListener(this);

		turingRelativeLayout.setOnClickListener(this);
		settingRelativeLayout.setOnClickListener(this);
		aboutRelativeLayout.setOnClickListener(this);
	}

	@Override
	protected void initEventTouch() {

	}

	/**
	 * 加载用户信息
	 * 
	 * @param userInfo
	 *            用户信息
	 */
	private void loadUserInfo(UserInfo userInfo) {
		ImageLoaderUtil.setNetWorkImageView(this, userInfo.getUserIcon(), mTab04UserIconCNIV, R.drawable.ic_launcher,
				R.drawable.ic_launcher);
		mTab04UserNameTV.setText(userInfo.getUserName());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cniv_tab_04_top_user_icon:
		case R.id.tv_tab_04_top_user_name:
			intent.setClass(this, LoginActivity.class);
			startActivity(intent);
			break;
		case R.id.rl_tab04_middle_turing:
			intent.setClass(this, TuringChatActivity.class);
			startActivity(intent);
			break;
		case R.id.rl_tab04_middle_setting:
			intent.setClass(this, SettingActivity.class);
			startActivity(intent);
			break;
		case R.id.rl_tab04_middle_about:
			intent.setClass(this, AboutActivity.class);
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
		getMenuInflater().inflate(R.menu.tab04, menu);
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

package com.shankes.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.shankes.websocketclient.R;

/**
 * 2015/12/06 22:49
 * 
 * @author ITjianghuxiaoxiong http://blog.csdn.net/itjianghuxiaoxiong
 */
@SuppressLint("DrawAllocation")
public class RadarImageView extends ImageView {
	private int w, h;// 获取控件宽高
	private Matrix matrix;
	private int degrees;
	private Handler mHandler = new Handler();
	private Runnable mRunnable = new Runnable() {
		@Override
		public void run() {
			degrees++;
			matrix.postRotate(degrees, w / 2, h / 2);
			RadarImageView.this.invalidate();// 重绘
			mHandler.postDelayed(mRunnable, 5);
		}
	};

	public RadarImageView(Context context) {
		this(context, null);
	}

	public RadarImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RadarImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		w = getMeasuredWidth();// 获取view的宽度
		h = getMeasuredHeight();// 获取view的高度
	}

	/**
	 * 初始化
	 */
	private void init() {
		setBackgroundResource(R.mipmap.pic_radar_bg);
		matrix = new Matrix();
		mHandler.postDelayed(mRunnable, 500);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.setMatrix(matrix);
		super.onDraw(canvas);
		matrix.reset();
	}
}
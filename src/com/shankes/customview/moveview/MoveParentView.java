package com.shankes.customview.moveview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * @author shankes
 *
 * @data 2016-12-9
 */
public class MoveParentView extends LinearLayout {

	private int mMove;
	private int yDown, yMove;
	private boolean isIntercept;
	private int i = 0;

	public MoveParentView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {

		int y = (int) event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			yDown = y;
			break;
		case MotionEvent.ACTION_MOVE:
			yMove = y;
			if (yMove - yDown < 0) {
				isIntercept = false;
			} else if (yMove - yDown > 0) {
				isIntercept = true;
			}
			break;
		case MotionEvent.ACTION_UP:
			break;

		}
		return isIntercept;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int y = (int) event.getY();
		switch (event.getAction()) {

		case MotionEvent.ACTION_DOWN:
			yDown = y;
			break;
		case MotionEvent.ACTION_MOVE:
			yMove = y;
			if (isIntercept && (yMove - yDown) > 0) {
				mMove = yMove - yDown;
				i += mMove;
				layout(getLeft(), getTop() + mMove, getRight(), getBottom() + mMove);
			}
			break;
		case MotionEvent.ACTION_UP:
			layout(getLeft(), getTop() - i, getRight(), getBottom() - i);
			i = 0;
			isIntercept = false;
			break;
		}

		return true;
	}

}

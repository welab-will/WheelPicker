package quan.will.wheelpicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by welab on 2016/10/17.
 * 基于ViewDragHelper实现
 * ViewDragHelper是拖动视图的方法
 * ViewFlipper GestureDetector Animation
 */

public class ScrollerLayout4 extends ViewGroup {

	private static final String TAG = "ScrollerLayout";
	private ViewDragHelper mViewDragHelper;


	public ScrollerLayout4(Context context) {
		super(context);
		init();
	}

	public ScrollerLayout4(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ScrollerLayout4(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	@SuppressLint("NewApi")
	public ScrollerLayout4(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	private void init() {
		mViewDragHelper = ViewDragHelper.create(this, new MyViewDragHelper());
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		measureChildren(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int left = l, top = t;
		if (changed) {
			for (int i = 0; i < getChildCount(); i++) {
				View childView = getChildAt(i);

				int right = left + childView.getMeasuredWidth();
				int bottom = top + childView.getMeasuredHeight();

				childView.layout(left, top, right, bottom);

				left += childView.getWidth();
			}
		}
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		mViewDragHelper.shouldInterceptTouchEvent(ev);
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mViewDragHelper.processTouchEvent(event);
		return true;
	}

	private static class MyViewDragHelper extends ViewDragHelper.Callback {
		@Override
		public boolean tryCaptureView(View child, int pointerId) {
			return true;
		}

		@Override
		public int clampViewPositionHorizontal(View child, int left, int dx) {
			return left;
		}

		@Override
		public int clampViewPositionVertical(View child, int top, int dy) {
			return top;
		}
	}


}

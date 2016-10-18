package quan.will.wheelpicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Scroller;

/**
 * Created by welab on 2016/10/18.
 * 尝试用getRawX() getRawY
 */

public class ScrollerLayout2 extends ViewGroup {

	private static final String TAG = "ScrollerLayout";

	private int mTouchSlop;
	private Scroller mScroller;
	private int mDownX;
	private int mLastX;

	public ScrollerLayout2(Context context) {
		super(context);
		init();
	}

	public ScrollerLayout2(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ScrollerLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	@SuppressLint("NewApi")
	public ScrollerLayout2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	private void init() {
		ViewConfiguration vc = ViewConfiguration.get(getContext());
		mTouchSlop = vc.getScaledTouchSlop();

		mScroller = new Scroller(getContext(), new AccelerateDecelerateInterpolator());
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
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.d(TAG, "onInterceptTouchEvent ACTION_DOWN");
				mLastX = mDownX = (int) ev.getRawX();
				break;
			case MotionEvent.ACTION_MOVE:
				Log.d(TAG, "onInterceptTouchEvent ACTION_MOVE");
				// 开始滑动
				if (Math.abs(ev.getRawX() - mDownX) > mTouchSlop) return true;
				break;
			case MotionEvent.ACTION_UP:
				Log.d(TAG, "onInterceptTouchEvent ACTION_UP");
				break;
		}
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.d(TAG, "onTouchEvent ACTION_DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.d(TAG, "onTouchEvent ACTION_MOVE");
				scrollBy(mLastX - (int) event.getRawX(), 0);
				mLastX = (int) event.getRawX();
				break;
			case MotionEvent.ACTION_UP:
				Log.d(TAG, "onTouchEvent ACTION_UP");
				float ratio = (float)getScrollX() / (float)getWidth();
				int currentItemIndex = (int) ratio;
				int targetItemIndex = 0;
//				滚动超过一半并且不是最后一个
				if (ratio - currentItemIndex >= 0.5) {
					targetItemIndex = currentItemIndex + 1;
				} else {
					targetItemIndex = currentItemIndex;
				}

				if (targetItemIndex < 0 || targetItemIndex >= getChildCount()) {
					targetItemIndex = currentItemIndex;
				}

				View targetChild = getChildAt(targetItemIndex);
				int dx = getScrollX() - targetChild.getLeft();
				int dy = 0;
				mScroller.startScroll(getScrollX(), 0, -dx, dy);
				invalidate();
				break;
		}
		return true;
	}

	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			invalidate();
		}
	}
}

package quan.will.wheelpicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Scroller;

/**
 * Created by welab on 2016/10/19.
 * Gesture实现
 */

public class ScrollerLayout5 extends ViewGroup implements GestureDetector.OnGestureListener {

	private static final String TAG = "ScrollerLayout";

	private int mTouchSlop;
	private Scroller mScroller;
	private int mDownX;
	private int mLastX;
	private GestureDetector mGestureDetector;

	public ScrollerLayout5(Context context) {
		super(context);
		init();
	}

	public ScrollerLayout5(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ScrollerLayout5(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	@SuppressLint("NewApi")
	public ScrollerLayout5(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	private void init() {
		ViewConfiguration vc = ViewConfiguration.get(getContext());
		mTouchSlop = vc.getScaledTouchSlop();

		mScroller = new Scroller(getContext(), new AccelerateInterpolator());
//		mScroller = new Scroller(getContext(), new AccelerateDecelerateInterpolator());

		mGestureDetector = new GestureDetector(getContext(), this);
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
				mLastX = mDownX = (int) ev.getX();
				break;
			case MotionEvent.ACTION_MOVE:
				Log.d(TAG, "onInterceptTouchEvent ACTION_MOVE");
				// 开始滑动
				if (Math.abs(ev.getX() - mDownX) > mTouchSlop) return true;
				break;
			case MotionEvent.ACTION_UP:
				Log.d(TAG, "onInterceptTouchEvent ACTION_UP");
				break;
		}
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean handled = mGestureDetector.onTouchEvent(event);
		if (!handled) {
			switch (event.getAction()) {
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

					showItemAt(targetItemIndex);
					break;
			}
		}
		return true;
	}

	private void showItemAt(int targetItemIndex) {
		if (targetItemIndex < 0 || targetItemIndex >= getChildCount()) return;
		View targetChild = getChildAt(targetItemIndex);
		int dx = getScrollX() - targetChild.getLeft();
		int dy = 0;
		mScroller.startScroll(getScrollX(), 0, -dx, dy);
		invalidate();
	}

	private void showNextItem() {

		int currentItemIndex = getScrollX() / getWidth();
		int targetItemIndex = currentItemIndex + 1;

		if (targetItemIndex < 0 || targetItemIndex >= getChildCount()) {
			targetItemIndex = currentItemIndex;
		}
		showItemAt(targetItemIndex);
	}

	private void showLastItem() {
		int currentItemIndex = getScrollX() / getWidth() + 1;
		int targetItemIndex = currentItemIndex - 1;

		if (targetItemIndex < 0 || targetItemIndex >= getChildCount()) {
			targetItemIndex = currentItemIndex;
		}
		showItemAt(targetItemIndex);
	}

	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			invalidate();
		}
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		Log.d("onScroll", e1.toString() + e2.toString());
		scrollBy((int) distanceX, 0);
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		if(velocityX < 0)
			showNextItem();
		else
			showLastItem();
		return true;
	}
}

package quan.will.wheelpicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Scroller;

/**
 * Created by txr on 16/10/15.
 */

public class ScrollLayout extends ViewGroup {

    private int mDownX = 0;
    private int mLastX = 0;
    private Scroller mScroller;
    private int mCurrentItemIndex;

    public ScrollLayout(Context context) {
        super(context);
        init();
    }

    public ScrollLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("NewApi")
    public ScrollLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext(), new AccelerateDecelerateInterpolator());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = l, top = t, right, bottom;
        if (changed) {
            for (int i = 0; i < getChildCount(); i++) {
                View childView = getChildAt(i);

                int width = childView.getMeasuredWidth();
                int height = childView.getMeasuredHeight();

                right = left + width;
                bottom = top + height;

                childView.layout(left, top, right, bottom);
                left += width;
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("onInterceptTouchEvent:", "ACTION_DOWN");
                mDownX = mLastX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("onInterceptTouchEvent:", "ACTION_MOVE");
                result = true;
                break;
            case MotionEvent.ACTION_UP:
                Log.d("onInterceptTouchEvent:", "ACTION_UP");
                break;
        }
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("onTouchEvent:", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                scrollBy(mLastX - (int) event.getX(), 0);
                mLastX = (int) event.getX();
                break;
            case MotionEvent.ACTION_UP:
                Log.d("onTouchEvent:", "ACTION_UP");
                int leftBorder, rightBorder;

//              触摸越界处理
                if (getChildCount() > 0) {

                    boolean isNeedReDraw = false;

                    leftBorder = getChildAt(0).getLeft();
                    rightBorder = getChildAt(getChildCount() - 1).getRight() - getWidth();
//                    负向移动
                    if (getScrollX() < leftBorder) {
                        mScroller.startScroll(getScrollX(), 0, leftBorder - getScrollX(), 0);
                        isNeedReDraw = true;
                    } else if(getScrollX() > rightBorder) {
                        mScroller.startScroll(getScrollX(), 0, rightBorder - getScrollX(), 0);
                        isNeedReDraw = true;
                    }
                    if(Math.abs(event.getX() - mDownX) > 100) {
                        boolean isForward = event.getX() < mDownX;
                        //执行切换
                        if (isForward && mCurrentItemIndex < getChildCount() - 1) {
                            mCurrentItemIndex++;
                            mScroller.startScroll(getScrollX(), 0, getChildAt(mCurrentItemIndex).getLeft() - getScrollX(), 0);
                            isNeedReDraw = true;
                        } else if (!isForward && mCurrentItemIndex > 1) {
                            mCurrentItemIndex--;
                            mScroller.startScroll(getScrollX(), 0, getChildAt(mCurrentItemIndex).getLeft() - getScrollX(), 0);
                            isNeedReDraw = true;
                        }

                    }
                    if (isNeedReDraw) {
                        invalidate();
                    }
                }

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

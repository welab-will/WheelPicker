package quan.will.wheelpicker;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.BounceInterpolator;
import android.widget.OverScroller;
import android.widget.TextView;

public class JellyTextView extends TextView {

	private OverScroller mScroller;

	private float lastX;
	private float lastY;

	private float startX;
	private float startY;

	public JellyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mScroller = new OverScroller(context, new BounceInterpolator());
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				lastX = event.getRawX();
				lastY = event.getRawY();
				break;
			case MotionEvent.ACTION_MOVE:
				float disX = event.getRawX() - lastX;
				float disY = event.getRawY() - lastY;

				offsetLeftAndRight((int) disX);
				offsetTopAndBottom((int) disY);
				lastX = event.getRawX();
				lastY = event.getRawY();
				break;
			case MotionEvent.ACTION_UP:
				mScroller.startScroll((int) ViewCompat.getX(this), (int) ViewCompat.getY(this), -(int) (ViewCompat.getX(this) - startX),
						-(int) (ViewCompat.getY(this) - startY));
				invalidate();
				break;
		}

		return super.onTouchEvent(event);
	}


	@Override
	public void computeScroll() {

		if (mScroller.computeScrollOffset()) {
			ViewCompat.setX(this, mScroller.getCurrX());
			ViewCompat.setY(this, mScroller.getCurrY());
			invalidate();
		}

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int ldh) {
		super.onSizeChanged(w, h, oldw, ldh);
		startX = ViewCompat.getX(this);
		startY = ViewCompat.getY(this);
	}

	public void spingBack() {

		if (mScroller.springBack((int) ViewCompat.getX(this), (int) ViewCompat.getY(this), 0, (int) ViewCompat.getX(this), 0,
				(int) ViewCompat.getY(this) - 100)) {
			Log.d("TAG", "ViewCompat.getX(this)=" + ViewCompat.getX(this) + "__getY()=" + ViewCompat.getY(this));
			invalidate();
		}

	}
}
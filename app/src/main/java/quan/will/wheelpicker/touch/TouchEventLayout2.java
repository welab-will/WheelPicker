package quan.will.wheelpicker.touch;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by welab on 2016/11/.
 */

public class TouchEventLayout2 extends RelativeLayout {

	private static final String TAG = "TouchEvent Layout2";

	public TouchEventLayout2(Context context) {
		super(context);
	}

	public TouchEventLayout2(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TouchEventLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public TouchEventLayout2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.d(TAG, "onInterceptHoverEvent : ACTION_DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.d(TAG, "onInterceptHoverEvent : ACTION_MOVE");
				break;
			case MotionEvent.ACTION_UP:
				Log.d(TAG, "onInterceptHoverEvent : ACTION_UP");
				break;
		}
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.d(TAG, "onTouchEvent : ACTION_DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.d(TAG, "onTouchEvent : ACTION_MOVE");
				break;
			case MotionEvent.ACTION_UP:
				Log.d(TAG, "onTouchEvent : ACTION_UP");
				break;
		}
		return super.onTouchEvent(event);
	}
}

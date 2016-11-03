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

public class TouchEventLayout4 extends RelativeLayout {

	private static final String TAG = "TouchEvent Layout4";

	public TouchEventLayout4(Context context) {
		super(context);
	}

	public TouchEventLayout4(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TouchEventLayout4(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public TouchEventLayout4(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		boolean result = super.dispatchTouchEvent(ev);
		Log.d(TAG, "dispatchTouchEvent : " + result);
		return result;
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
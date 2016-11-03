package quan.will.wheelpicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
/**
 * 全部返回
 *
 **/
public class TouchEventActivity extends AppCompatActivity {

	private RelativeLayout layout1, layout2, layout3, layout4;
	private static final String TAG = "TouchEvent Activity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_touch_event);

		layout1 = (RelativeLayout) findViewById(R.id.layout1);
		layout2 = (RelativeLayout) findViewById(R.id.layout2);
		layout3 = (RelativeLayout) findViewById(R.id.layout3);
		layout4 = (RelativeLayout) findViewById(R.id.layout4);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
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
		return super.onTouchEvent(event);
	}
}

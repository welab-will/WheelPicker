package quan.will.wheelpicker.impl;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;

/**
 * 滚轮选择器.
 */
public class WheelPicker extends LinearLayoutCompat {

	public WheelPicker(Context context) {
		super(context);
		init();
	}

	public WheelPicker(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public WheelPicker(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {

	}
}

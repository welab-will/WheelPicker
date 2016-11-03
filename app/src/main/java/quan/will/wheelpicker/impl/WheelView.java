package quan.will.wheelpicker.impl;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * 滚轮
 */

public class WheelView extends RecyclerView {
	public WheelView(Context context) {
		super(context);
		init();
	}

	public WheelView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public WheelView(Context context, @Nullable AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		LinearLayoutManager manager = new LinearLayoutManager(getContext());
		manager.setOrientation(LinearLayoutManager.VERTICAL);

		this.setLayoutManager(manager);

//		this.addItemDecoration(new ItemDivider());
		this.addItemDecoration(new SelectedItemCover());
		this.setAdapter(new TextWheelAdapter());
	}


}

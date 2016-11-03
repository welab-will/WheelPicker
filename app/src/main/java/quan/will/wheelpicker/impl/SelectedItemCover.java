package quan.will.wheelpicker.impl;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;

/**
 * Created by welab on 2016/11/1.
 */

public class SelectedItemCover extends RecyclerView.ItemDecoration {

	private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

	SelectedItemCover() {
		mPaint.setColor(Color.GRAY);
	}

	@Override
	public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
		int width = parent.getWidth(); //默认值
		int height = parent.getHeight(); //默认值

		int itemWidth = 200, itemHeight = 100;

//		计算每个item的高度，假设等高
		if (parent.getChildCount() > 0) {
			itemWidth = parent.getLayoutManager().getDecoratedMeasuredWidth(parent.getChildAt(0));
			itemHeight = parent.getLayoutManager().getDecoratedMeasuredHeight(parent.getChildAt(0));
		}

		int left = parent.getLeft();
		int top = parent.getTop() + height / 2 - itemHeight / 2;
		int right = parent.getRight();
		int bottom = top + itemHeight;
		c.drawLine(left, top, right, top, mPaint);
		c.drawLine(left, bottom, right, bottom, mPaint);
	}
}

package quan.will.wheelpicker.impl;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 分割线
 */

public class ItemDivider extends RecyclerView.ItemDecoration {

	private Drawable mDivider = new DividerDrawable();

	public ItemDivider() {
		super();
	}

	@Override
	public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
		super.onDraw(c, parent, state);
		final int left = parent.getPaddingLeft();
		final int right = parent.getWidth() - parent.getPaddingRight();

		final int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			final View child = parent.getChildAt(i);
			final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
			final int top = parent.getLayoutManager().getDecoratedBottom(child) + params.bottomMargin;
			final int bottom = top + mDivider.getIntrinsicHeight();
			mDivider.setBounds(left, top, right, bottom);
			mDivider.draw(c);
		}
	}

	@Override
	public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
		super.onDrawOver(c, parent, state);
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

	}

	class DividerDrawable extends ShapeDrawable {

		DividerDrawable() {
			RectShape rectShape = new RectShape();
			setShape(rectShape);
			getPaint().setColor(Color.GRAY);
			setIntrinsicHeight(2);
		}
	}
}

package quan.will.wheelpicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 滚轮选择器.
 */
public class WheelPickerTest extends View {
	private int mExampleColor = Color.RED; // TODO: use a default from R.color...
	private float mExampleDimension = 0; // TODO: use a default from R.dimen...
	private Drawable mExampleDrawable;

	private TextPaint mTextPaint;
	private float mTextWidth;
	private float mTextHeight;

	private static final String TAG = WheelPickerTest.class.getSimpleName();
	private float mTextPadding = 15.0f;
	private float mYOffset = 0f;

	private Paint mLinePaint;

	public WheelPickerTest(Context context) {
		super(context);
		init(null, 0);
	}

	public WheelPickerTest(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs, 0);
	}

	public WheelPickerTest(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs, defStyle);
	}

	private void init(AttributeSet attrs, int defStyle) {
		// Load attributes
		final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.WheelPicker, defStyle, 0);

		mExampleColor = a.getColor(R.styleable.WheelPicker_exampleColor, mExampleColor);
		// Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
		// values that should fall on pixel boundaries.
		mExampleDimension = a.getDimension(R.styleable.WheelPicker_exampleDimension, mExampleDimension);

		if (a.hasValue(R.styleable.WheelPicker_exampleDrawable)) {
			mExampleDrawable = a.getDrawable(R.styleable.WheelPicker_exampleDrawable);
			mExampleDrawable.setCallback(this);
		}

		a.recycle();

		// Set up a default TextPaint object
		mTextPaint = new TextPaint();
		mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		mTextPaint.setTextAlign(Paint.Align.LEFT);

		mLinePaint = new Paint();
		mLinePaint.setColor(Color.RED);

		// Update TextPaint and text measurements from attributes
		invalidateTextPaintAndMeasurements();
	}

	private void invalidateTextPaintAndMeasurements() {
		mTextPaint.setTextSize(mExampleDimension);
		mTextPaint.setColor(mExampleColor);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// TODO: consider storing these as member variables to reduce
		// allocations per draw cycle.
		int paddingLeft = getPaddingLeft();
		int paddingTop = getPaddingTop();
		int paddingRight = getPaddingRight();
		int paddingBottom = getPaddingBottom();

		int contentWidth = getWidth() - paddingLeft - paddingRight;
		int contentHeight = getHeight() - paddingTop - paddingBottom;
		/*
		// Draw the text.
		canvas.drawText(mExampleString, paddingLeft + (contentWidth - mTextWidth) / 2, paddingTop + (contentHeight + mTextHeight) / 2, mTextPaint);

		// Draw the example drawable on top of the text.
		if (mExampleDrawable != null) {
			mExampleDrawable.setBounds(paddingLeft, paddingTop, paddingLeft + contentWidth, paddingTop + contentHeight);
			mExampleDrawable.draw(canvas);
		}
		*/

		int y = 0;
		for(int i = 2016; i >= 2000; i--) {
			String str = Integer.toString(i);

			Rect strBounds = new Rect();
			mTextPaint.getTextBounds(str, 0, str.length(), strBounds);

			mTextHeight = strBounds.height();
			mTextWidth = strBounds.width();

			canvas.drawText(str, paddingLeft + (contentWidth - mTextWidth) / 2, y, mTextPaint);

			y += (mTextHeight + mTextPadding);
		}
		/*测试代码
//		abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ
		String textString = "2016Jajmrg";
//		测量文字高度
		mTextWidth = mTextPaint.measureText(textString);


		Rect rect = new Rect();
		mTextPaint.getTextBounds(textString, 0, textString.length(), rect);

//		rect.offset(50, 200);
		rect.offsetTo(50, 200);

		mLinePaint.setStyle(Paint.Style.STROKE);
//		canvas.drawRect(rect, mLinePaint);

		Paint.FontMetrics pfm = mTextPaint.getFontMetrics();

		canvas.drawLine(50, 200 + pfm.top, 50 + rect.width(), 200 + pfm.top, mLinePaint);
		canvas.drawLine(50, 200 + pfm.ascent, 50 + rect.width(), 200 + pfm.ascent, mLinePaint);
		canvas.drawLine(50, 200, 50 + rect.width(), 200, mLinePaint);
		canvas.drawLine(50, 200 + pfm.descent, 50 + rect.width(), 200 + pfm.descent, mLinePaint);
		canvas.drawLine(50, 200 + pfm.bottom, 50 + rect.width(), 200 + pfm.bottom, mLinePaint);
		canvas.drawLine(50, 200 + pfm.leading, 50 + rect.width(), 200 + pfm.leading, mLinePaint);

//		canvas.drawRect(50 + rect.left, 200 + rect.top, 50 + rect.left + rect.width(), 200 + rect.top + rect.height(), mLinePaint);
//		左
		canvas.drawLine(50 + rect.left, 200 + rect.top, 50 + rect.left, 200 + rect.top + rect.height(), mLinePaint);
//		上
		canvas.drawLine(50 + rect.left, 200 + rect.top, 50 + rect.left + rect.width(), 200 + rect.top, mLinePaint);
//		下
		canvas.drawLine(50 + rect.left, 200 + rect.top + rect.height(), 50 + rect.left + rect.width(), 200 + rect.top + rect.height(), mLinePaint);
//		右
		canvas.drawLine(50 + rect.left + rect.width(), 200 + rect.top, 50 + rect.left + rect.width(), 200 + rect.top + rect.height(), mLinePaint);

		canvas.drawText(textString, 50, 200, mTextPaint);
		*/
	}

	private float lastY;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();

		switch (action)
		{
			case MotionEvent.ACTION_DOWN:
				Log.e(TAG, "onTouchEvent ACTION_DOWN");
				lastY = event.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				mYOffset =event.getY() - lastY;
				scrollBy(0, -(int) mYOffset);
				lastY = event.getY();
				break;
			case MotionEvent.ACTION_UP:
				Log.e(TAG, "onTouchEvent ACTION_UP");
				lastY = 0f;
				break;
			default:
				break;
		}
		return true;
	}
}

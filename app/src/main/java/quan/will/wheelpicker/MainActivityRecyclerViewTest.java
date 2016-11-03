package quan.will.wheelpicker;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityRecyclerViewTest extends AppCompatActivity {

	private RecyclerView mRecyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

		LinearLayoutManager manager = new LinearLayoutManager(this);
		manager.setOrientation(LinearLayoutManager.VERTICAL);
		mRecyclerView.setLayoutManager(manager);

		mRecyclerView.addItemDecoration(new IItemDecoration());

		mRecyclerView.setAdapter(new IAdapter(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivityRecyclerViewTest.this, "clicked", Toast.LENGTH_SHORT).show();
			}
		}));

		mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
			@Override
			public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
				return false;
			}

			@Override
			public void onTouchEvent(RecyclerView rv, MotionEvent e) {

			}

			@Override
			public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

			}
		});

	}

	class IAdapter extends RecyclerView.Adapter<IAdapter.IViewHolder> {

		private View.OnClickListener mListener;
		
		IAdapter(View.OnClickListener l) {
			this.mListener = l;
		}
		
		@Override
		public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wp_list_item, parent, false);
			view.setOnClickListener(mListener);
			return new IViewHolder(view);
		}

		@Override
		public void onBindViewHolder(IViewHolder holder, int position) {
			holder.textView.setText("hello world!");
		}

		@Override
		public int getItemCount() {
			return 100;
		}
		
		class IViewHolder extends RecyclerView.ViewHolder {

			TextView textView;

			public IViewHolder(View itemView) {
				super(itemView);
				textView = (TextView) itemView.findViewById(R.id.tv);
			}
		}
	}

	class IItemDecoration extends RecyclerView.ItemDecoration {

		private Drawable mDivider = new ItemDivider();

		public IItemDecoration() {
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
				int height = parent.getLayoutManager().getDecoratedMeasuredHeight(child);
				int db = parent.getLayoutManager().getTopDecorationHeight(child);
				int dt = parent.getLayoutManager().getBottomDecorationHeight(child);
				final int top = parent.getLayoutManager().getDecoratedBottom(child) + params.bottomMargin;
				final int bottom = top + mDivider.getIntrinsicHeight();
				mDivider.setBounds(left, top, right, bottom);
				mDivider.draw(c);
			}
		}

		@Override
		public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
			super.onDrawOver(c, parent, state);
//            c.drawRect(0, 0, 200, 200, new Paint());
		}

		@Override
		public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//			outRect.bottom = 100;
//            outRect.left = 100;
//            outRect.right = 100;
//			outRect.top = 100;
//            super.getItemOffsets(outRect, view, parent, state);
		}
	}

	class ItemDivider extends ShapeDrawable {

		ItemDivider() {
			RectShape rectShape = new RectShape();
			setShape(rectShape);
			getPaint().setColor(Color.GRAY);
			setIntrinsicHeight(2);
		}
	}
}

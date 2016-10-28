package quan.will.wheelpicker;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.addItemDecoration(new IItemDecoration());

        mRecyclerView.setAdapter(new IAdapter());

    }

    class IAdapter extends RecyclerView.Adapter<IAdapter.IViewHolder> {
        @Override
        public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new IViewHolder(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, null, false));
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
                textView = (TextView) itemView;
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
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();

                final int top = child.getBottom() + 2;
                final int bottom = top + 10;
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
            c.drawRect(0, 0, 200, 200, new Paint());
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
        }
    }

    class ItemDivider extends ShapeDrawable {

        ItemDivider() {
            RectShape rectShape = new RectShape();
            setShape(rectShape);
            getPaint().setColor(Color.GRAY);
        }
    }
}

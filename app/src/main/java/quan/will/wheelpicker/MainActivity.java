package quan.will.wheelpicker;

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

    }

}

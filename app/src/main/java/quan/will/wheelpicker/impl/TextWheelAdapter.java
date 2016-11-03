package quan.will.wheelpicker.impl;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import quan.will.wheelpicker.R;

/**
 * 数据适配器
 */

public class TextWheelAdapter extends RecyclerView.Adapter<TextWheelAdapter.IViewHolder> {


	TextWheelAdapter() {
	}

	@Override
	public TextWheelAdapter.IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wp_list_item, parent, false);
		return new TextWheelAdapter.IViewHolder(view);
	}

	@Override
	public void onBindViewHolder(TextWheelAdapter.IViewHolder holder, int position) {
		holder.textView.setText("hello world!" + position);
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

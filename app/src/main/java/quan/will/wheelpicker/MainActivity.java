package quan.will.wheelpicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import quan.will.wheelpicker.impl.WheelView;

public class MainActivity extends AppCompatActivity {

	private WheelView ww;
	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ww = (WheelView) findViewById(R.id.recycler_view);
		btn = (Button) findViewById(R.id.btn);

		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ww.smoothScrollToPosition(60);
			}
		});
	}
}

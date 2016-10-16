package quan.will.wheelpicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_layout);
    }

    public void clicked(View view) {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
    }
}

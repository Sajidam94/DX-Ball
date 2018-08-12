package et.sajid.dxball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(new DrawCanvas(this));

    }
}

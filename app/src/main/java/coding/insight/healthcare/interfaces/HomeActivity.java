package coding.insight.healthcare.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import coding.insight.healthcare.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
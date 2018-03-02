package com.wakakusa.soba_ver1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class time_result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_result);
        long[] result = getIntent().getLongArrayExtra("recode_time");
        TextView text = (TextView) findViewById(R.id.result1);
        long mm = result[1]*100 / 1000 / 60;
        long ss = result[1] / 1000 % 60;
        long ms = (result[1]*100 - ss * 1000 - mm * 1000 * 60)/100;
        text.setText(String.format("%1$02d:%2$02d.%3$01d", mm, ss, ms));
    }
}

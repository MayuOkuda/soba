package com.wakakusa.soba_ver1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class result_comment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_comment);

        //
        RelativeLayout screen = (RelativeLayout) findViewById(R.id.activity_result_comment);
        screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), time_result.class);
                intent.putExtra("recode_time",getIntent().getLongArrayExtra("recode_time"));
                startActivity(intent);
            }
        });

    }
}

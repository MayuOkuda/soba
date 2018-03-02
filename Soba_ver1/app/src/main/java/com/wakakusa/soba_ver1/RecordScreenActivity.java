package com.wakakusa.soba_ver1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecordScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_screen);

         /*新規登録*/
        Button button1 = (Button) findViewById(R.id.new_record);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), RecordActivity.class);
                intent.putExtra("recode_time",getIntent().getLongArrayExtra("recode_time"));
                startActivity(intent);
            }
        });

         /*追加登録*/
        Button button2 = (Button) findViewById(R.id.add_record);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MeasureActivity.class);
                startActivity(intent);
            }
        });

        /*最初に戻る*/
        Button button3 = (Button) findViewById(R.id.to_home);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

    }
}

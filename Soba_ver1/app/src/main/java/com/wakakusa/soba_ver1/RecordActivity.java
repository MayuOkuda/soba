package com.wakakusa.soba_ver1;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RecordActivity extends AppCompatActivity {


    boolean write_flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        final long[] recode = getIntent().getLongArrayExtra("recode_time");

   /*記録するボタン*/
        Button button1 = (Button) findViewById(R.id.button5);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getApplication(), RecordScreenActivity.class);
                //startActivity(intent);
                if(write_flag) {
                    //日時をidとして設定
                    Date data = new Date();  //
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyMMddHHmmss");
                    long id = Long.parseLong(sdf1.format(data));  //(3)Dateオブジェクトを表示
                    DatabaseWriter userdata_w = new DatabaseWriter(RecordActivity.this, 0);
                    userdata_w.deleteDB();
                    userdata_w.user_dataWrite("山田", id);
                    DatabaseWriter recodedata_w = new DatabaseWriter(RecordActivity.this, 1);
                    recodedata_w.deleteDB();
                    recodedata_w.recode_datawrite(recode, id);
                    Toast toast = Toast.makeText(RecordActivity.this, "データを追加したよ!", Toast.LENGTH_LONG);
                    toast.show();
                    write_flag = false;
                }
            }
        });

         /*戻る*/
        Button button2 = (Button) findViewById(R.id.button4);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(RecordActivity.this, "Toastのテストだよ!", Toast.LENGTH_LONG);
                toast.show();

            }
        });
    }
}

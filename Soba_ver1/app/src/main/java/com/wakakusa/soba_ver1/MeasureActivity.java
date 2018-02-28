package com.wakakusa.soba_ver1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MeasureActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.menu_background);
        layout.setBackgroundResource(R.drawable.challenge_back);

        Button button1 = (Button) findViewById(R.id.enjoy);
        Button button2 = (Button) findViewById(R.id.challenge);
        //button
         ActivityIntent(button1, TimerActivity.class);


        final AlertDialog.Builder eatbutton_builder = new AlertDialog.Builder(this);
        eatbutton_builder.setTitle("お家に帰りたいけど,帰れない悲しい")
                        .setMessage(" 大変眠たい");


        DialogApp(button2, eatbutton_builder);


        }


    /*jumpの処理*/
    void ActivityIntent(View view, final Class activityName){

        /*お気楽モード*/
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), activityName);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }

    /*dialogの処理*/
    void DialogApp(View view, final AlertDialog.Builder dialog){

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }
}

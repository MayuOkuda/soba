package com.wakakusa.soba_ver1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity {

    private TextView timerText, processText;
    private Button startButton, stopButton;
    private Timer timer;
    private final long delay = 0;       //タスクが実行されるまでの時間(mm)
    private final long period = 100;      //連続するタスクの時間間隔(mm)
    private long count;
    private Handler handler = new Handler();
    private CountUpTimerTask timerTask = null;
    private long[] recode = new long[12];
    private int t;
    final SobaText process_text = new SobaText(); //そば工程名を保持させている

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        desplay();

    }

    //timerの表示
    void desplay(){

        processText = (TextView) findViewById(R.id.name);
        processText.setText(process_text.timer_comment);


        //計測した時間を入れる配列の番号
        t = 0;

        timerText = (TextView) findViewById(R.id.timer);
        timerText.setText("00:00.0");

        startButton = (Button) findViewById(R.id.start);
        startButton.setText(process_text.start_text);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // タイマーが走っている最中にボタンをタップされたケース
                if(null != timer) {
                    //工程名を表示
                    recode[t] = count;
                    t++;
                    if (t < 12) processText.setText(process_text.process[t]);
                    if (t == 12) {
                        Intent intent = new Intent(getApplication(), result_comment.class);
                        intent.putExtra("recode_time",recode);
                        intent.putExtra("aa","わからん");
                        startActivity(intent);

                    }

                }else {
                    processText.setText(process_text.process[t]);
                    // Timer インスタンスを生成
                    timer = new Timer();

                    // TimerTask インスタンスを生成
                    timerTask = new CountUpTimerTask();

                    // スケジュールを設定 100msec
                    // public void schedule (TimerTask task, long delay, long period)
                    timer.schedule(timerTask, 0, 100);
                    // ボタンの文字変更
                    startButton.setText(process_text.comp_text);
                    stopButton.setText(process_text.pose_text);
                }
            }
        });

        final AlertDialog.Builder pose_builder= new AlertDialog.Builder(this);
        //中断ボタンの処理
        stopButton = (Button) findViewById(R.id.stop);
        stopButton.setText(process_text.back_text);
        stopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // タイマーが走っている最中にボタンをタップされたケース
                if(null != timer){
                    // Cancel
                    timer.cancel();
                    timer = null;

                    pose_builder.setMessage("計測を中断しますか？")
                                .setTitle("中断")
                                .setPositiveButton("はい",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            finish();
                                        }})
                                .setNegativeButton("いいえ", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        timer = new Timer();
                                        // TimerTask インスタンスを生成
                                        timerTask = new CountUpTimerTask();
                                        timer.schedule(timerTask, 0, 100);

                                    }}).show();

                } else {
                    finish();

                }
            }
        });

    }


    //timerを動かす処理
    class CountUpTimerTask extends TimerTask {
        @Override
        public void run() {
            // handlerを使って処理をキューイングする
            handler.post(new Runnable() {
                public void run() {
                    count++;
                    long mm = count*100 / 1000 / 60;
                    long ss = count*100 / 1000 % 60;
                    long ms = (count*100 - ss * 1000 - mm * 1000 * 60)/100;
                    // 桁数を合わせるために02d(2桁)を設定
                    timerText.setText(String.format("%1$02d:%2$02d.%3$01d", mm, ss, ms));
                }
            });
        }
    }

}

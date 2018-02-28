package com.wakakusa.soba_ver1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;
import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

import java.util.logging.LogRecord;

public class TimerActivity extends AppCompatActivity {

    private TextView timerText;
    private Button startButton, stopButton;
    private Timer timer;
    private final long delay = 0;       //タスクが実行されるまでの時間(mm)
    private final long period = 100;      //連続するタスクの時間間隔(mm)
    private long count;
    private Handler handler = new Handler();
    private CountUpTimerTask timerTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        desplay();

    }

    //timerの表示
    void desplay(){
        timerText = (TextView) findViewById(R.id.timer);
        timerText.setText("00:00.0");

        startButton = (Button) findViewById(R.id.start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // タイマーが走っている最中にボタンをタップされたケース
                if(null != timer){
                    timer.cancel();
                    timer = null;
                }

                // Timer インスタンスを生成
                timer = new Timer();

                // TimerTask インスタンスを生成
                timerTask = new CountUpTimerTask();

                // スケジュールを設定 100msec
                // public void schedule (TimerTask task, long delay, long period)
                timer.schedule(timerTask, 0, 100);

                // カウンター
                count = 0;
                timerText.setText("00:00.0");

            }
        });

        stopButton = (Button) findViewById(R.id.stop);
        stopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(null != timer){
                    // Cancel
                    timer.cancel();
                    timer = null;
                    timerText.setText("00:00.0");
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

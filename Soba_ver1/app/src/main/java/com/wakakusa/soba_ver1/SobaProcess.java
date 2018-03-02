package com.wakakusa.soba_ver1;

/**
 * Created by okuda on 2018/03/01.
 */
public class SobaProcess {

    final static String[] process = {"計量","水回し", "捏ね","地延ばし", "丸出し", "四つ出し",
            "肉分け", "延し", "たたみ", "切り用意", "切り" ,"片付け"};

    //Timerが動く前の表示内容
    static String timer_coment = "準備が整ったら\n開始をタップ";
    //Timerが動く前のボタン
    static String start_left = "開始";
    static String start_right = "戻る";

    //Timerが動いている最中のボタン
    static String run_left = "完了";
    static String run_ringht = "中断";

    static String setTime(long i){
        long mm = i*100 / 1000 / 60;
        long ss = i*100 / 1000 % 60;
        long ms = (i*100 - ss * 1000 - mm * 1000 * 60)/100;
        return String.format("%1$02d:%2$02d.%3$01d", mm, ss, ms);
    }

}

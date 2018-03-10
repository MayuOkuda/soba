package com.wakakusa.soba_ver1;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * SQLiteデータベース作成用アクティビティ
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    static final int DB_ver = 1;                //DBのバージョン

    //SQL文をString型に保持
    static String DB_name = "soba.db";

    //テーブル作成のSQL文
    //登録ユーザ情報
    final String userData = "CREATE TABLE user(" +
            "name      TEXT," +
            "id        INTEGER)";

    //登録データ情報
    final String  timeData = "CREATE TABLE recode(" +
            "id        INTEGER,"+
            "time1      INTEGER,"+
            "time2      INTEGER,"+
            "time3      INTEGER,"+
            "time4      INTEGER,"+
            "time5      INTEGER,"+
            "time6      INTEGER,"+
            "time7      INTEGER,"+
            "time8      INTEGER,"+
            "time9      INTEGER,"+
            "time10     INTEGER,"+
            "time11     INTEGER,"+
            "time12     INTEGER,"+
            "time13     INTEGER,"+
            "time14     INTEGER,"+
            "time15     INTEGER,"+
            "time16     INTEGER,"+
            "time17     INTEGER,"+
            "time18     INTEGER,"+
            "time19     INTEGER,"+
            "time20     INTEGER,"+
            "time21     INTEGER )";

    //登録データ情報
    final String  processName = "CREATE TABLE process_name(" +
            "naem1      TEXT,"+
            "name2      TEXT,"+
            "name3      TEXT,"+
            "name4      TEXT,"+
            "name5      TEXT,"+
            "name6      TEXT,"+
            "name7      TEXT,"+
            "name8      TEXT,"+
            "name9      TEXT,"+
            "name10     TEXT,"+
            "name11     TEXT,"+
            "name12     TEXT,"+
            "name13     TEXT,"+
            "name14     TEXT,"+
            "name15     TEXT,"+
            "name16     TEXT,"+
            "name17     TEXT,"+
            "name18     TEXT,"+
            "name19     TEXT,"+
            "name20     TEXT )";

    //登録データ情報
    final String  processData = "CREATE TABLE process_data(" +
            "time1      INTEGER,"+
            "time2      INTEGER,"+
            "time3      INTEGER,"+
            "time4      INTEGER,"+
            "time5      INTEGER,"+
            "time6      INTEGER,"+
            "time7      INTEGER,"+
            "time8      INTEGER,"+
            "time9      INTEGER,"+
            "time10     INTEGER,"+
            "time11     INTEGER,"+
            "time12     INTEGER,"+
            "time13     INTEGER,"+
            "time14     INTEGER,"+
            "time15     INTEGER,"+
            "time16     INTEGER,"+
            "time17     INTEGER,"+
            "time18     INTEGER,"+
            "time19     INTEGER,"+
            "time20     INTEGER )";

    //コンストラクタ
    // (コンテクスト[入力は getApplicationContext()もしくはthis],データベースファイル名)
    public MySQLiteHelper(Context context){
        //コンテクスト,データベースの名前, ??? , バージョン
        super(context, DB_name, null, DB_ver);
    }

    //データベースの作成（自動で起動）
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(userData);
        db.execSQL(timeData);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


}

class DatabaseWriter {
    //private SQLiteDatabase read;
    SQLiteDatabase write;
    static MySQLiteHelper helper = null;
    String[] property;
    String Table_name;

    final static String[] tableName = {"user", "recode","time"};
    final static String[] user_property = {"name", "id"};
    final static String[] recode_property = {"id", "data", "keiryo", "mizu", "kone", "zinosi", "marudasi","yotudasi"
                                        ,"nikuzuke", "nosi", "tatami", "kiriyoi", "kiri", "katazuke", "sum"};
    final static String[] time_property = {"id", "keiryo", "mizu", "kone", "zinosi", "marudasi","yotudasi"
                                        ,"nikuzuke", "nosi", "tatami", "kiriyoi", "kiri", "katazuke", "sum"};

    //コンストラクタ
    public DatabaseWriter(Context context, int i) {
        Table_name = tableName[i];
        helper = new MySQLiteHelper(context);
        write = helper.getWritableDatabase();
        if(i==0) property = user_property;
        else if(i==1) property = recode_property;
        else if(i==2) property = time_property;

    }

    //データベースに入れる値を順番に入れる
    public void user_dataWrite(String name, long id){
        ContentValues cvalue = new ContentValues();
        cvalue.put("name", name);
        cvalue.put("id", id);
        write.insert(this.Table_name, null, cvalue);
    }

    //データベースに入れる値を順番に入れる
    public void recode_datawrite(long[] recode, long id, long data){
        ContentValues cvalue = new ContentValues();
        long sum = 0;
        cvalue.put("id", id);
        cvalue.put("data", data);
        for(int i = 0; i < recode.length; i++ ){
            cvalue.put(property[i], recode[i]);
            sum += recode[i];
        }
        cvalue.put("sum", sum );
        write.insert(this.Table_name, null, cvalue);
    }

    public void deleteDB() {
        //データベースの削除
        write.delete(this.Table_name, null, null);
    }


    //str1属性がold_wordの時、その場所のstr2属性値をnew_wordに書き換える
    public void update(String str1, String str2, String old_word, String new_word) {
        ContentValues updateValues = new ContentValues();
        updateValues.put(str2, new_word);
        write.update(this.Table_name, updateValues, str1 + "=?", new String[]{old_word});

    }
}

//データベースの中に格納されているデータを画面に表示されるためのクラス
class DatabaseReader {
    private SQLiteDatabase read;
    private String Table_name;
    static MySQLiteHelper helper = null;

    public DatabaseReader(Context context, String table) {
        Table_name = table;
        helper = new MySQLiteHelper(context);
        read = helper.getWritableDatabase();
    }

    //属性名、数
    public String readDB(String[] table, int n) {
        String num = null;
        if(n != 0) num = String.valueOf(n);

        //データの検索結果はCursor型で返される
        // queryメソッドの実行例
        Cursor c = read.query(this.Table_name, table, null,
                null, null, null, num);
        //データベースのデータを読み取って表示する。
        //startManagingCursor(c);
        String str = "";
        while (c.moveToNext()) {
            for(String i : table)
                str += c.getString(c.getColumnIndex(i)) + "\n";
        }
        c.close();
        return str;

    }

    //table=取り出す属性　where=条件内容 param=条件内容
    public String readDB2(String[] table, String where, String[] param) {

        //データの検索結果はCursor型で返される
        // queryメソッドの実行例
        Cursor c =read.query(this.Table_name,table,where,param,
                null, null, null,null);

        //データベースのデータを読み取って表示する。
        //startManagingCursor(c);

        String str = "";
        while (c.moveToNext()) {
            for(String i : table) str += c.getString(c.getColumnIndex(i))  + "\n";

        }
        c.close();
        return str;
    }


    //table=取り出す属性　where=条件内容 param=条件内容
    public String readDB2au(String[] table, String where, String[] param) {

        //データの検索結果はCursor型で返される
        // queryメソッドの実行例
        Cursor c =read.query(this.Table_name,table,where,param,
                null, null, "newscode desc",null);

        //データベースのデータを読み取って表示する。
        //startManagingCursor(c);

        String str = "";
        while (c.moveToNext()) {
            for(String i : table) str += c.getString(c.getColumnIndex(i))  + "axb4cy13u";

        }
        c.close();
        return str;
    }


    //item=取り出す属性　where=条件内容 param=条件内容の値　table = "テーブル"
    public String readDB3(String[] item, String where, String[] param, String table) {

        //データの検索結果はCursor型で返される
        // queryメソッドの実行例
        Cursor c =read.query(table,item, where,param,
                null, null, null,null);

        //データベースのデータを読み取って表示する。
        //startManagingCursor(c);

        String str = "";
        while (c.moveToNext()) {
            for(String i : item) str += c.getString(c.getColumnIndex(i))  + "\n";

        }
        c.close();
        return str;
    }

}

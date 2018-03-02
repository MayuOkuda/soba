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
    final String  userData = "CREATE TABLE userData(" +
            "name TEXT not null, " +
            "id   INTEGER,"   +
            "data      INTEGER,"+
            "keiryo    INTEGER,"+
            "mizu      INTEGER,"+
            "kone      INTEGER,"+
            "zinosi    INTEGER,"+
            "marudasi  INTEGER,"+
            "yotudasi  INTEGER,"+
            "nikuzuke  INTEGER,"+
            "nosi      INTEGER,"+
            "tatami    INTEGER,"+
            "kiriyoi   INTEGER,"+
            "kiri      INTEGER,"+
            "katazuke  INTEGER,"+
            "result    INTEGER )";

    final String  timeData = "CREATE TABLE time(" +
            "name TEXT," +
            "data      INT,"+
            "keiryo    INT,"+
            "mizu      INT,"+
            "kone      INT,"+
            "zinosi    INT,"+
            "marudasi  INT,"+
            "yotudasi  INT,"+
            "nikuzuke  INT,"+
            "nosi      INT,"+
            "tatami    INT,"+
            "kiriyoi   INT,"+
            "kiri      INT,"+
            "katazuke  INT,"+
            "result    INT )";



    //コンストラクタ
    // (コンテクスト[入力は getApplicationContext()もしくはthis],データベースファイル名)
    public MySQLiteHelper(Context context){
        //コンテクスト,データベースの名前, ??? , バージョン
        super(context, DB_name, null, DB_ver);
    }

    //データベースの作成（自動で起動）
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(userData);

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

    final String[] tableName = {"student", "course","score", "test", "news","loginData"};
    final String[] user_property = {"id","name", "birth","adm","ug","dpm","grade","mjr","sub1","sub2","teacher","address","mailaddress"};

    //コンストラクタ
    public DatabaseWriter(Context context, String table) {
        Table_name = table;
        helper = new MySQLiteHelper(context);
        write = helper.getWritableDatabase();
        if(Table_name.equals(tableName[0])) property = property;

    }

    //データベースに入れる値を順番に入れる
    public void writeDB(int [] recode_time) throws JSONException {
        ContentValues cvalue = new ContentValues();
        for(int i = 0; i < recode_time.length; i++){
            cvalue.put(property[i], recode_time[i]);
        }
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

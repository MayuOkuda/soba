package com.wakakusa.soba_ver1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecodeCheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recode_check);


        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        MySQLiteHelper helper = new MySQLiteHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        //Cursor c = db.query(DatabaseWriter.tableName[0], DatabaseWriter.user_property, null,
          //      null, null, null, null);
        Cursor c = db.query(DatabaseWriter.tableName[1], DatabaseWriter.recode_property, null,
              null, null, null, null);

        boolean mov = c.moveToFirst();
        while (mov) {
            TextView textView = new TextView(this);
            //textView.setText(String.format("%s : id = %d", c.getString(0),
              //      c.getLong(1)));
           // mov = c.moveToNext();
            for(int i = 0; i <3; i++) {
            View view = getLayoutInflater().inflate(R.layout.data_test, null);
            //layout.addView(view);
            TextView text = (TextView) view.findViewById(R.id.textView4);
                layout.addView(view);
                text.setText(SobaProcess.setTime(c.getLong(1)));
            }
            layout.addView(textView);
            mov = c.moveToNext();
        }
        c.close();
        db.close();
    }

        void setlayout(){}

}

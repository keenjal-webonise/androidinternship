package com.example.webonise.tab;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class ActivityUserDetails extends AppCompatActivity {


    private static final String TAG = ActivityUserDetails.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "oncreate");
        setContentView(R.layout.content_activity_user_details);

        int id = getIntent().getIntExtra("id",0);
        MySQLiteHelper mydb = new MySQLiteHelper(this);
        Cursor cursor = mydb.getRecordByID(id);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                String fname = cursor.getString(cursor.getColumnIndex("FirstName"));
                String lname = cursor.getString(cursor.getColumnIndex("LastName"));
                String emailId = cursor.getString(cursor.getColumnIndex("EmailID"));
                String password = cursor.getString(cursor.getColumnIndex("Password"));


                TextView tvFirstName = (TextView) findViewById(R.id.tvFirstName);
                TextView tvLastName = (TextView) findViewById(R.id.tvLastName);
                TextView tvEmailID = (TextView) findViewById(R.id.tvEmailID);
                TextView tvPassword = (TextView) findViewById(R.id.tvPassword);

                tvFirstName.setText(fname);
                tvLastName.setText(lname);
                tvEmailID.setText(emailId);
                tvPassword.setText(password);
            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onresume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onpause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onstop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }
}

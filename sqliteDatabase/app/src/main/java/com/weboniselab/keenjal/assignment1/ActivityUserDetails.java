package com.weboniselab.keenjal.assignment1;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.webonise.sqlitedatabase.R;

public class ActivityUserDetails extends AppCompatActivity {


    private static final String TAG = ActivityUserDetails.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"oncreate");
        setContentView(R.layout.content_activity_user_details);
      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);


       Bundle bundle = getIntent().getExtras();
        TextView tvFirstName = (TextView) findViewById(R.id.tvFirstName);
        TextView tvLastName = (TextView) findViewById(R.id.tvLastName);
        TextView tvEmailID = (TextView) findViewById(R.id.tvEmailID);
        TextView tvPassword = (TextView) findViewById(R.id.tvPassword);

        tvFirstName.setText(bundle.getString("FirstName"));
        tvLastName.setText(bundle.getString("LastName"));
        tvEmailID.setText(bundle.getString("EmailId"));
        tvPassword.setText(bundle.getString("Password"));

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

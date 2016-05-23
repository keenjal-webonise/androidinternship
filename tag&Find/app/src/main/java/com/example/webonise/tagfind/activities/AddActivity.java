package com.example.webonise.tagfind.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.webonise.tagfind.R;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        goToMainActivity();
    }
    private void goToMainActivity()
    {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }

}
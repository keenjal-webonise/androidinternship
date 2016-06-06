package com.example.webonise.tagfind.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.webonise.tagfind.R;
import com.example.webonise.tagfind.models.Data;
import com.example.webonise.tagfind.utilities.Constants;

public class DescriptionActivity extends AppCompatActivity {

   private TextView tvTitle,tvTag;
   private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTag = (TextView) findViewById(R.id.tvTag);
        imageView = (ImageView) findViewById(R.id.imageView);

        Intent intent = getIntent();
        String data = intent.getStringExtra(Constants.BUNDLE_KEY_TITLE);
        String data1 = intent.getStringExtra(Constants.BUNDLE_KEY_TAG);
        String imagePath = intent.getStringExtra(Constants.BUNDLE_KEY_IMAGE);
        tvTitle.setText(data);
        tvTag.setText(data1);
        imageView.setImageBitmap(BitmapFactory.decodeFile(imagePath));
    }
}

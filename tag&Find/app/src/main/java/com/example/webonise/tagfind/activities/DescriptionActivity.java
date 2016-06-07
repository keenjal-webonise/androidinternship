package com.example.webonise.tagfind.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webonise.tagfind.R;
import com.example.webonise.tagfind.models.Data;
import com.example.webonise.tagfind.utilities.Constants;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

public class DescriptionActivity extends AppCompatActivity {

    private TextView tvTag;
    private ImageView imageView;
    private Button btnGetDirection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvTag = (TextView) findViewById(R.id.tvTag);
        imageView = (ImageView) findViewById(R.id.imageView);
        btnGetDirection = (Button) findViewById(R.id.btnGetDirection);

        btnGetDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri intentUri = Uri.parse("google.navigation:q=Taronga+Zoo,+Sydney+Australia");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW,intentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        Intent intent = getIntent();
        String data = intent.getStringExtra(Constants.BUNDLE_KEY_TITLE);
        String data1 = intent.getStringExtra(Constants.BUNDLE_KEY_TAG);
        String imagePath = intent.getStringExtra(Constants.BUNDLE_KEY_IMAGE);

        tvTag.setText(data1);
        imageView.setImageBitmap(BitmapFactory.decodeFile(imagePath));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(data);
        }
    }
}

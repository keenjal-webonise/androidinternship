package com.example.webonise.tagfind.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.ExifInterface;
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
import android.util.Log;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DescriptionActivity extends AppCompatActivity {

    private TextView tvTag;
    private ImageView imageView;
    private Button btnGetDirection;
    Float Latitude, Longitude ,title;


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


        Intent intent = getIntent();
        String data = intent.getStringExtra(Constants.BUNDLE_KEY_TITLE);
        String data1 = intent.getStringExtra(Constants.BUNDLE_KEY_TAG);
        final String imagePath = intent.getStringExtra(Constants.BUNDLE_KEY_IMAGE);

        markGeoTagImage(imagePath);
        if (Longitude != null && Latitude != null) {
            btnGetDirection.setVisibility(View.VISIBLE);
        } else {
            btnGetDirection.setVisibility(View.INVISIBLE);
        }
        btnGetDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                Location loc = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                        double logi = loc.getLongitude();
//                        double lat = loc.getLatitude();
                    String address ="http://maps.google.com/maps?q="+ Latitude +"," + Longitude +"("+ title + ")&iwloc=A&hl=es";
                    Intent maps = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + address));
                    startActivity(maps);
                } catch (Exception e) {
                    Log.e("", e.toString());
                }
                    }
                });
        tvTag.setText(data1);
        try {
            imageView.setImageBitmap(BitmapFactory.decodeFile(imagePath));

        }catch (OutOfMemoryError e)
        {
            e.printStackTrace();
        }
        finally {

        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(data);
        }
    }
    public void markGeoTagImage(String imagePath) {
        try {
            ExifInterface exifInterface = new ExifInterface(imagePath);
            exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
            exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
            exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
            exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);

            String LATITUDE = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
            String LATITUDE_REF = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
            String LONGITUDE = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
            String LONGITUDE_REF = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);

            if ((LATITUDE != null)
                    && (LATITUDE_REF != null)
                    && (LONGITUDE != null)
                    && (LONGITUDE_REF != null)) {

                if (LATITUDE_REF.equals("N")) {
                    Latitude = convertToDegree(LATITUDE);
                } else {
                    Latitude = 0 - convertToDegree(LATITUDE);
                }

                if (LONGITUDE_REF.equals("E")) {
                    Longitude = convertToDegree(LONGITUDE);
                } else {
                    Longitude = 0 - convertToDegree(LONGITUDE);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Float convertToDegree(String stringDMS) {
        Float result = null;
        String[] DMS = stringDMS.split(",", 3);

        String[] stringD = DMS[0].split("/", 2);
        Double D0 = new Double(stringD[0]);
        Double D1 = new Double(stringD[1]);
        Double FloatD = D0 / D1;

        String[] stringM = DMS[1].split("/", 2);
        Double M0 = new Double(stringM[0]);
        Double M1 = new Double(stringM[1]);
        Double FloatM = M0 / M1;

        String[] stringS = DMS[2].split("/", 2);
        Double S0 = new Double(stringS[0]);
        Double S1 = new Double(stringS[1]);
        Double FloatS = S0 / S1;

        result = new Float(FloatD + (FloatM / 60) + (FloatS / 3600));

        return result;
    };

    @Override
    public String toString() {
        return (String.valueOf(Latitude)
                + ", "
                + String.valueOf(Longitude));
    }

    public int getLatitudeE6() {
        return (int) (Latitude * 1000000);
    }

    public int getLongitudeE6() {
        return (int) (Longitude * 1000000);
    }
}


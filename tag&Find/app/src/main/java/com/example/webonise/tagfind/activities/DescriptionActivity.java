package com.example.webonise.tagfind.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.webonise.tagfind.R;
import com.example.webonise.tagfind.utilities.Constants;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;

public class DescriptionActivity extends AppCompatActivity {

    private TextView tvtag;
    private ImageView imageView;
    private Button btnGetDirection;
    private Float latitude, longitude;

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

        tvtag = (TextView) findViewById(R.id.tvTag);
        imageView = (ImageView) findViewById(R.id.imageView);
        btnGetDirection = (Button) findViewById(R.id.btnGetDirection);


        Intent intent = getIntent();
        String data = intent.getStringExtra(Constants.BUNDLE_KEY_TITLE);
        String data1 = intent.getStringExtra(Constants.BUNDLE_KEY_TAG);
        final String imagePath = intent.getStringExtra(Constants.BUNDLE_KEY_IMAGE);

        markGeoTagImage(imagePath);
        if (longitude != null && latitude != null) {
            btnGetDirection.setVisibility(View.VISIBLE);
        } else {
            btnGetDirection.setVisibility(View.INVISIBLE);
        }
        btnGetDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String address ="http://maps.google.com/maps?q="+ latitude +"," + longitude +"";
                    Intent maps = new Intent(Intent.ACTION_VIEW, Uri.parse(address));
                    startActivity(maps);
                } catch (Exception e) {
                    Log.e("", e.toString());
                }
                    }
                });
        tvtag.setText(data1);
        try {
            ImageLoader.getInstance().displayImage("file://" + imagePath, imageView);
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
                    latitude = convertToDegree(LATITUDE);
                } else {
                    latitude = 0 - convertToDegree(LATITUDE);
                }

                if (LONGITUDE_REF.equals("E")) {
                    longitude = convertToDegree(LONGITUDE);
                } else {
                    longitude = 0 - convertToDegree(LONGITUDE);
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
        return (String.valueOf(latitude)
                + ", "
                + String.valueOf(longitude));
    }

    public int getLatitudeE6() {
        return (int) (latitude * 1000000);
    }

    public int getLongitudeE6() {
        return (int) (longitude * 1000000);
    }
}


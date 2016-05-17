package com.example.webonise.progressbardemo;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    Button btnStartProgress;
    ProgressDialog progressBar;
    private int progressStatus = 0;
    private Handler progressBarHandler = new Handler();
    private long fileSize = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButtonClick();
    }
    public void addListenerOnButtonClick()
    {
        btnStartProgress = (Button) findViewById(R.id.button);
        btnStartProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar = new ProgressDialog(v.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("File Downloading...");
                progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressBar.setProgress(0);
                progressBar.setMax(100);
                progressBar.show();
                progressStatus = 0;
                fileSize = 0;
                 new Thread(new Runnable() {
                     @Override
                     public void run() {
                         while (progressStatus < 100)
                         {
                             progressStatus = doOperation();
                             try
                             {
                                 Thread.sleep(1000);
                             }
                             catch (InterruptedException e)
                             {
                                 e.printStackTrace();
                             }
                             progressBarHandler.post(new Runnable() {
                                 @Override
                                 public void run() {
                                     progressBar.setProgress(progressStatus);
                                 }
                             });
                         }
                         if (progressStatus >= 100)
                         {
                             try
                             {
                                 Thread.sleep(1000);
                             }
                             catch (InterruptedException e)
                             {
                                 e.printStackTrace();
                             }
                             progressBar.dismiss();
                         }
                     }
                 }).start();
            }
        });

    }
    public int doOperation()
    {
        while (fileSize <= 10000)
        {
            fileSize ++;
            if (fileSize == 10000)
            {
                return 10;
            }else if(fileSize == 20000)
            {
                return 20;
            }else if (fileSize == 30000)
            {
                return 30;
            }
            else if(fileSize == 40000)
            {
                return 40;
            }
            else
            {
                return 100;
            }
        }
        return 100;
    }
}

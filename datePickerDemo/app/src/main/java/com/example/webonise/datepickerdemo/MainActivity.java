package com.example.webonise.datepickerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DatePicker picker;
    Button displayDate;
   // TextView textview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  textview1=(TextView)findViewById(R.id.textView1);
        picker=(DatePicker)findViewById(R.id.datePicker1);
        displayDate=(Button)findViewById(R.id.button1);

            getCurrentDate();
        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentDate();
            }
        });
    }
    public String getCurrentDate()
    {
        StringBuilder bulider = new StringBuilder();
        bulider.append("Current Date:");
        bulider.append((picker.getMonth() + 1)+"/");
        bulider.append(picker.getDayOfMonth()+"/");
        bulider.append(picker.getYear());
        return bulider.toString();
    }
}

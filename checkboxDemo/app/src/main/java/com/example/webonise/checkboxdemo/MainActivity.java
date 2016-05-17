package com.example.webonise.checkboxdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox pizza,Choclate,burger;
    Button buttonOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();

    }
    public void addListenerOnButton()
    {
        pizza = (CheckBox) findViewById(R.id.checkbox1);
        Choclate = (CheckBox) findViewById(R.id.checkbox2);
        burger = (CheckBox) findViewById(R.id.checkbox3);
        buttonOrder = (Button) findViewById(R.id.Button);

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalamount = 0;
                StringBuilder result =  new StringBuilder();
                result.append("Select Items:");
                if(pizza.isChecked()){
                    result.append("\n pizza 100Rs");
                    totalamount+=100;
                }
                if(Choclate.isChecked()){
                    result.append("\n Choclate 50Rs");
                    totalamount+=50;
                }
                if (burger.isChecked()){
                    result.append("\n burger 120Rs");
                    totalamount+=120;
                }
                result.append("\n Total:" +totalamount+"Rs");
                Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

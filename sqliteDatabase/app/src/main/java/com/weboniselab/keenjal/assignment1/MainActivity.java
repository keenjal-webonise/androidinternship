package com.weboniselab.keenjal.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.webonise.sqlitedatabase.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = ActivityUserDetails.class.getName();

    MySQLiteHelper mydb;
    EditText etFirstName,etLastName,etEmailID,etPassword;
    Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"oncreate");

        mydb = new MySQLiteHelper(this);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etEmailID = (EditText) findViewById(R.id.etEmailID);
        etPassword = (EditText) findViewById(R.id.etPassword);
        save = (Button) findViewById(R.id.btnSave);
        AddData();


    }


    public void AddData()
    {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String emailID = etEmailID.getText().toString();
                String password = etPassword.getText().toString();

                if (TextUtils.isEmpty(firstName))
                {
                    etFirstName.setError("Required....");
                    etFirstName.requestFocus();
                }
                else if (TextUtils.isEmpty(lastName))
                {
                    etLastName.setError("Required....");
                    etLastName.requestFocus();

                }
                else if (TextUtils.isEmpty(emailID))
                {

                    etEmailID.setError("Required....");
                    etEmailID.requestFocus();
                    isValidEmail(emailID);
                 //   isValidEmail(emailID);

                }
                else if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(getApplicationContext(),"Incomplete Form First Fillup the form", Toast.LENGTH_LONG).show();
                }
                else {
                    boolean isInserted = mydb.insertData(etFirstName.getText().toString(), etLastName.getText().toString(), etEmailID.getText().toString(), etPassword.getText().toString());
                    if (isInserted == true) {
                        Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this,ActivityUserDetails.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("FirstName", etFirstName.getText().toString());
                        bundle.putString("LastName", etLastName.getText().toString());
                        bundle.putString("EmailID",etEmailID.getText().toString());
                        bundle.putString("Password", etPassword.getText().toString());
                        intent.putExtras(bundle);
                        startActivity(intent);

                    } else {
                        Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }

    public static boolean isValidEmail(String emailID) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emailID);
        return matcher.matches();
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


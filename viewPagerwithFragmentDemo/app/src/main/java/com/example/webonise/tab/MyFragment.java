package com.example.webonise.tab;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MyFragment extends Fragment{

    EditText etFirstName,etLastName,etEmailID,etPassword;
    Button save;
    MySQLiteHelper mydb;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mydb = new MySQLiteHelper(getActivity());
        etFirstName = (EditText) view.findViewById(R.id.etFirstName);
        etLastName = (EditText) view.findViewById(R.id.etLastName);
        etEmailID = (EditText)view.findViewById(R.id.etEmailID);
        etPassword = (EditText) view.findViewById(R.id.etPassword);
        save = (Button) view.findViewById(R.id.btnSave);

        save.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View v) {
                addData();
            }
        });
        return view;
    }

    public void addData() {
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String emailID = etEmailID.getText().toString();
        String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(firstName)) {
            etFirstName.setError("Required....");
            etFirstName.requestFocus();
        }
        else if (TextUtils.isEmpty(lastName)) {
            etLastName.setError("Required....");
            etLastName.requestFocus();

        }
        else if (TextUtils.isEmpty(emailID)) {

            etEmailID.setError("Required....");
            etEmailID.requestFocus();

        }
        else if (TextUtils.isEmpty(password))
        {

            Toast.makeText(getActivity(), "Incomplete Form First Fillup the form", Toast.LENGTH_LONG).show();
        } else
        {
            long id= mydb.insertData(etFirstName.getText().toString(), etLastName.getText().toString(), etEmailID.getText().toString(), etPassword.getText().toString());
            if (id != -1)
            {
                Toast.makeText(getActivity(), "Data Inserted", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), ActivityUserDetails.class);
                intent.putExtra("id",id);
                startActivity(intent);

            }
            else
            {
                Toast.makeText(getActivity(), "Data Not Inserted", Toast.LENGTH_LONG).show();
            }
        }

    }
}

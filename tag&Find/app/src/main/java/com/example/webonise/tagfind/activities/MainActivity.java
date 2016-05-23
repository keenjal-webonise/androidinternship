package com.example.webonise.tagfind.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.example.webonise.tagfind.models.Data;
import com.example.webonise.tagfind.R;
import com.example.webonise.tagfind.adapters.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getApplication());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
       fill_with_data();
    }
    public List<Data> fill_with_data()
    {
        List<Data> data = new ArrayList<>();
        data.add(new Data("pen","pen are in drawer",R.drawable.download));
        data.add(new Data("pen","pen are in drawer",R.drawable.download1));
        data.add(new Data("pen","pen are in drawer",R.drawable.download));
        data.add(new Data("pen","pen are in drawer",R.drawable.download1));
        return data;

    }
}

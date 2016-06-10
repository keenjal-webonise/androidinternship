package com.example.webonise.tagfind.activities;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.webonise.tagfind.R;
import com.example.webonise.tagfind.adapters.MyViewAdapter;
import com.example.webonise.tagfind.database.MySQLiteHelper;
import com.example.webonise.tagfind.models.Data;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvData;
    private MyViewAdapter adapter;
    private MySQLiteHelper mySQLiteHelper;
    private List<Data> arryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySQLiteHelper = new MySQLiteHelper(MainActivity.this);
        Log.e("Insert:","Inserting....");
        arryList = mySQLiteHelper.getAllData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        rvData = (RecyclerView) findViewById(R.id.rvData);
        rvData.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvData.setLayoutManager(linearLayoutManager);
        rvData.setItemAnimator(new DefaultItemAnimator());
        Log.i("",arryList.size()+"");
        adapter = new MyViewAdapter(arryList,this);
        rvData.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        SearchableInfo info = searchManager.getSearchableInfo(getComponentName());
        searchView.setSearchableInfo(info);
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setDrawingCacheBackgroundColor(getResources().getColor(R.color.black));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i("onQueryTextSubmit",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i("onQueryTextChange",newText);
                if (newText.isEmpty())
                {
                    return true;
                }
                else
                {
                    adapter.filter(newText);
                    return false;
                }
            }
        });
        return true;
    }
        public boolean onOptionsItemSelected(MenuItem item)
        {
            switch (item.getItemId())
            {
                 case R.id.action_search:
                 return true;
                case R.id.action_add:
                    Intent intent = new Intent(MainActivity.this,AddActivity.class);
                    startActivity(intent);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
}
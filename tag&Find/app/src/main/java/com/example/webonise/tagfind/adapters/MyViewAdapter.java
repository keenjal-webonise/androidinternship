package com.example.webonise.tagfind.adapters;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webonise.tagfind.R;
import com.example.webonise.tagfind.activities.DescriptionActivity;
import com.example.webonise.tagfind.activities.MainActivity;
import com.example.webonise.tagfind.activities.MySQLiteHelper;
import com.example.webonise.tagfind.models.Data;
import com.example.webonise.tagfind.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by webonise on 23/5/16.
 */
public class MyViewAdapter extends RecyclerView.Adapter<MyViewAdapter.MyViewHolder>{

    private List<Data> mListData;
    private int position;
    Data data;


    public MyViewAdapter(List<Data> dataList, MainActivity mainActivity) {
        this.mListData = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return  viewHolder;

    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Data data = mListData.get(position);
        if (data != null) {
            holder.tvTag.setText(data.getTag());
            holder.tvTitle.setText(data.getTitle());
            Bitmap thumbnail = (BitmapFactory.decodeFile(data.getImage()));
            holder.imageView.setImageBitmap(thumbnail);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),DescriptionActivity.class);
                    intent.putExtra(Constants.BUNDLE_KEY_TITLE,data.getTitle());
                    intent.putExtra(Constants.BUNDLE_KEY_TAG,data.getTag());
                    intent.putExtra(Constants.BUNDLE_KEY_IMAGE,data.getImage());
                    v.getContext().startActivity(intent);

                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return mListData!=null ? mListData.size() : 0;
    }

    public void filter(String query)
    {
        if (query.isEmpty())
        {
            mListData.clear();
            mListData.addAll(mListData);
        }
        else
        {
            ArrayList<Data> result = new ArrayList<>();
            query = query.toLowerCase();
            for (Data item : mListData)
            {
                if (item.tag.toLowerCase().contains(query) || item.title.toLowerCase().contains(query))
                {
                    result.add(item);
                }
            }
            mListData.clear();
            mListData.addAll(result);
        }
        notifyDataSetChanged();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView tvTag;
        TextView tvTitle;
        ImageView imageView;

        public MyViewHolder(View v)
        {
            super(v);
            cardView = (CardView)v.findViewById(R.id.cardView);
            tvTag = (TextView)v.findViewById(R.id.tvTag);
            tvTitle = (TextView)v.findViewById(R.id.tvTitle);
            imageView = (ImageView)v.findViewById(R.id.imageView);
        }
    }
}

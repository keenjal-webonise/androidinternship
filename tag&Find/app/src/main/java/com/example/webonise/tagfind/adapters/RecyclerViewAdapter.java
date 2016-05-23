package com.example.webonise.tagfind.adapters;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.webonise.tagfind.R;
import com.example.webonise.tagfind.models.Data;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by webonise on 23/5/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{


    private ArrayList<Data> title;
    private ArrayList<Data> tag;
    private ArrayList<Data> imageId;



    public RecyclerViewAdapter(ArrayList<Data> title, ArrayList<Data> tag, ArrayList<Data> imageId) {
        this.title = title;
        this.tag = tag;
        this.imageId = imageId;
    }

    public RecyclerViewAdapter(Application application) {

    }


    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return  viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {

        Data data = Data.get(position);
        if (data != null) {
            holder.tvTitle.setText(title.get(position).getTitle());
            holder.tvTag.setText(tag.get(position).getTag());
            holder.imageView.setImageResource(imageId.get(position).getImageId());
        }

    }

    @Override
    public int getItemCount() {
        return Data.length;
    }
    public void insert(int position,Data data)
    {
        title.add(position,data);
        tag.add(position,data);
        imageId.add(position,data);
        notifyItemInserted(position);
    }
    public void remove(Data data)
    {
        int position = tag.indexOf(data);
        tag.remove(position);
        notifyItemRemoved(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView tvTitle;
        TextView tvTag;
        ImageView imageView;

        public ViewHolder(View v)
        {
            super(v);
            cardView = (CardView)v.findViewById(R.id.cardView);
            tvTitle = (TextView)v.findViewById(R.id.tvTitle);
            tvTag = (TextView)v.findViewById(R.id.tvTag);
            imageView = (ImageView)v.findViewById(R.id.imageView);

        }
    }
}

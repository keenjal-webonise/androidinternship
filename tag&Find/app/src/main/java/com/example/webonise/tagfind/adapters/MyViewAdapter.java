package com.example.webonise.tagfind.adapters;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.webonise.tagfind.R;
import com.example.webonise.tagfind.activities.DescriptionActivity;
import com.example.webonise.tagfind.activities.MainActivity;
import com.example.webonise.tagfind.models.Data;
import com.example.webonise.tagfind.utilities.Constants;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.List;

public class MyViewAdapter extends RecyclerView.Adapter<MyViewAdapter.MyViewHolder>{

    private List<Data> mListData;


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyViewAdapter(List<Data> dataList, MainActivity mainActivity) {
        this.mListData = dataList;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // set the view's size, margins, paddings and layout parameters
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_tag_find,null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return  viewHolder;

    }

    // Replace the contents of a view
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Data data = mListData.get(position);
        if (data != null) {
            holder.tvTag.setText(data.getTag());
            holder.tvTitle.setText(data.getTitle());

            ImageLoader.getInstance().displayImage("file://" + data.getImage(), holder.imageView);

            Log.d("&&&&&&&&&&&&&&&", data.getImage());

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
//
//    web = (FragmentWebview) manager.findFragmentById(R.id.fragment2);
//    if (web!= null && web.isVisible())
//    {
//        web.setNewPage(i);
//    }
//    else
//    {
//        Intent intent = new Intent(this , FragmentSupport.class);
//        intent.putExtra("index", i);
//        startActivity(intent);
//    }

    // Return the size of your dataset
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

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        // each data item is just a string in this case
        public CardView cardView;
        public TextView tvTag;
        public TextView tvTitle;
        public ImageView imageView;

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

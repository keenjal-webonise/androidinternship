package com.example.webonise.tagfind.models;

import android.net.Uri;

/**
 * Created by webonise on 23/5/16.
 */
public class Data {

    public String title;
    public String tag;
    public String image;
   // private Uri data;


    public Data() {
        this.title = title;
        this.tag = tag;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

 //   public Uri getData() {
      //  return data;
   // }
}

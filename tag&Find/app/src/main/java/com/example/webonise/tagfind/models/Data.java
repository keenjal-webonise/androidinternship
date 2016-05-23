package com.example.webonise.tagfind.models;

/**
 * Created by webonise on 23/5/16.
 */
public class Data {
    public static int length;
    private String title;
    private String tag;
    private int imageId;

    public Data(String title, String tag, int imageId) {
        this.title = title;
        this.tag = tag;
        this.imageId = imageId;
    }

    public Data(int position) {

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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public static Data get(int position) {
        return null;
    }
}

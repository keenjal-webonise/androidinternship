package com.example.webonise.tagfind.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.webonise.tagfind.models.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by webonise on 24/5/16.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    private List<Data> mListData = null;
    public static final String DATABASE_NAME  = "FinderImage.db";
    public static final String TABLE_NAME = "Image_table";
    public static final String ID = "_id";
    public static final String Image = "Image";
    public static final String Title = "Title";
    public static final String Tag = "Tag";

    public MySQLiteHelper(Context context) {
        super(context,DATABASE_NAME, null,1);
    }

    public long insertData(String image, String title, String tag)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Image, image);
        contentValues.put(Title,title);
        contentValues.put(Tag,tag);
        return db.insert(TABLE_NAME,null,contentValues);
    }
    public List<Data> getAllData() {
        List<Data> dataList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Data data = new Data();
                    data.setTag(cursor.getString(cursor.getColumnIndex(Tag)));
                    data.setImage(cursor.getString(cursor.getColumnIndex(Image)));
                    data.setTitle(cursor.getString(cursor.getColumnIndex(Title)));
                    dataList.add(data);
                } while (cursor.moveToNext());
            }
        }catch (Exception e)
        {
            Log.d(Tag,"Error while trying to get posts from database ");
        }finally {
            if (cursor != null && !cursor.isClosed())
            {
                cursor.close();
            }
        }
        return dataList;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY, " + Image + " TEXT, " + Title + " TEXT, " + Tag + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXIETS" + TABLE_NAME);
        onCreate(db);
    }
}

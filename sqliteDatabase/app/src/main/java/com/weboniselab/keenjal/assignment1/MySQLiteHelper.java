package com.weboniselab.keenjal.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;


public class MySQLiteHelper extends SQLiteOpenHelper{


    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String ID = "_id";
    public static final String FirstName = "FirstName";
    public static final String LastName = "LastName";
    public static final String EmailID = "EmailID";
    public static final String Password = "Password";

    public MySQLiteHelper(Context context) {
        super(context,DATABASE_NAME, null, 1);
    }

    public long insertData(String firstName, String lastName, String emailID, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FirstName,firstName);
        contentValues.put(LastName,lastName);
        contentValues.put(EmailID,emailID);
        contentValues.put(Password,password);
        return db.insert(TABLE_NAME, null ,contentValues);
    }

    public Cursor getRecordByID(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where " + ID + " = " + "_id" ,null);
        return res;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "  +  TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY, " + FirstName + " TEXT, " + LastName + " TEXT, " + EmailID + " TEXT," + Password + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIETS" + TABLE_NAME);
        onCreate(db);

    }
}

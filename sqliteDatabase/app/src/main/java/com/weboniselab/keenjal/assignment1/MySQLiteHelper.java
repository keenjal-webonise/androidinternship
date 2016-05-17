package com.weboniselab.keenjal.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by webonise on 16/5/16.
 */
public class MySQLiteHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "FirstName";
    public static final String COL_2 = "LastName";
    public static final String COL_3 = "EmailID";
    public static final String COL_4 = "Password";


    public MySQLiteHelper(Context context) {
        super(context,DATABASE_NAME, null, 1);
    }




    public boolean insertData(String firstName, String lastName, String emailID, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,firstName);
        contentValues.put(COL_2,lastName);
        contentValues.put(COL_3,emailID);
        contentValues.put(COL_4,password);
        long result = db.insert(TABLE_NAME, null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "  +  TABLE_NAME + "(FIRSTNAME TEXT, LASTNAME TEXT, EMAILID TEXT,PASSWORD TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIETS" + TABLE_NAME);
        onCreate(db);

    }
}

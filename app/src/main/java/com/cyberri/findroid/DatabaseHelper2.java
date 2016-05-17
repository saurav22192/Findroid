package com.cyberri.findroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by It's me! on 17-03-2016.
 */
public class DatabaseHelper2 extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contact.db";//mistake
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID= "id";
    private static final String COLUMN_NAME= "name";
    private static final String COLUMN_EMAIL= "email";

    private static final String COLUMN_MOBILE= "mobile";
    private static final String COLUMN_ADDRESS= "address";
    private static final String COLUMN_GENDER= "gender";
    SQLiteDatabase db;
    private static final String TABLE_CREATE="create table contacts(id integer primary key autoincrement,name text not null,email text,mobile text not null,address text, gender text);";

    public DatabaseHelper2(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.db = db;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(TABLE_CREATE);
        this.db=db;

    }
    public void insertContact(contact c)
    {
        db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        String query="select * from contacts";
        Cursor cursor=db.rawQuery(query,null);
        int count= cursor.getCount();
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_MOBILE, c.getMobile());
        values.put(COLUMN_ADDRESS, c.getAddress());
        values.put(COLUMN_GENDER, c.getGender());
        db.insert(TABLE_NAME, null, values);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
    {
        String query="DROP TABLE IF EXISTS"+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}

package com.cyberri.findroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.telephony.TelephonyManager;

/**
 * Created by It's me! on 16-03-2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "member.db";
    private static final String TABLE_NAME = "member";
    private static final String COLUMN_ID= "id";
    private static final String COLUMN_NAME= "name";
    private static final String COLUMN_EMAIL= "email";
    private static final String COLUMN_PASS= "pass";
    private static final String COLUMN_MOBILE= "mobile";
    private static final String COLUMN_ADDRESS= "address";
    private static final String COLUMN_STATUS= "status";
    private static final String COLUMN_SIMNO= "simno";
//    private  TelephonyManager telemamanger;

    SQLiteDatabase db;
    private static final String TABLE_CREATE="create table member(id integer primary key autoincrement,name text not null,email text not null unique,mobile text not null,address text, pass text not null, status text default 'active',simno text default '0');";
public DatabaseHelper(Context context)
{
    super(context,DATABASE_NAME,null,DATABASE_VERSION);
}

    @Override
    public void onCreate(SQLiteDatabase db){
    db.execSQL(TABLE_CREATE);
    this.db=db;

    }
    public void insertMember(member m,Context context)
    {
        TelephonyManager telemamanger= (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String getSimSerialNumber = telemamanger.getSimSerialNumber();

        db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
       String query="select * from member";
        Cursor cursor=db.rawQuery(query,null);
        int count= cursor.getCount();
        values.put(COLUMN_NAME,m.getName());
        values.put(COLUMN_EMAIL,m.getEmail());
        values.put(COLUMN_MOBILE,m.getMobile());
        values.put(COLUMN_ADDRESS,m.getAddress());
        values.put(COLUMN_PASS, m.getPassword());
        values.put(COLUMN_STATUS, "active");
        values.put(COLUMN_SIMNO,getSimSerialNumber);

        db.insert(TABLE_NAME, null, values);
        cursor.close();
    }
    member getmember(String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from member where email='" + email + "'", null);
        if (cursor != null)
            cursor.moveToFirst();

        member m=new member();
        m.setName(cursor.getString(1));
        m.setEmail(cursor.getString(2));
        m.setAddress(cursor.getString(4));
        m.setMobile(cursor.getString(3));
        m.setStatus(cursor.getString(4));

        // (cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        // return contact
        cursor.close();
        return m;
    }
    public void updateMember(member m)
    {
        db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COLUMN_NAME, m.getName());
        values.put(COLUMN_MOBILE, m.getMobile());
        values.put(COLUMN_ADDRESS, m.getAddress());
       // values.put(COLUMN_STATUS, m.getStatus());

        String x=m.getEmail();
        Cursor cursor = db.rawQuery("select id from member where email='" + x + "'", null);

        if (cursor != null)
            cursor.moveToFirst();
        int idd=cursor.getInt(0);
        db.update(TABLE_NAME, values, COLUMN_ID + "=" + idd, null);
    cursor.close();
    }

    public void updatesimno(String s)
    {
        db=this.getWritableDatabase();
        db.execSQL("update `member` set `simno`='"+s+"'");

    }



    public void updateStatus(String s,String t)
    {
        db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COLUMN_STATUS, s);

        Cursor cursor = db.rawQuery("select id from member where email='" + t + "'", null);

        if (cursor != null)
            cursor.moveToFirst();
        int idd=cursor.getInt(0);
        db.update(TABLE_NAME, values, COLUMN_ID + "=" + idd, null);
        cursor.close();
    }

    public String searchPass(String email)
{
db=this.getReadableDatabase();
String query="select email,pass from "+TABLE_NAME;
    Cursor cursor=db.rawQuery(query,null);
    String a,b;
    b="not found";
    if(cursor.moveToFirst())
    {
        do{
            a=cursor.getString(0);
            if(a.equals(email))
            {
              b=cursor.getString(1);
            break;
            }
        }
    while(cursor.moveToNext());
    }
    return b;

}
    public long idcheck()
    {
        db = this.getReadableDatabase();
        long cnt=0;
       // db.execSQL("delete from "+ TABLE_NAME);
     //   db.close();
      // db = this.getReadableDatabase();
       // db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        cnt  = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return cnt;



    }
    public String check(String a) {
        db = this.getReadableDatabase();
        String query = "select `"+a+"` from `" + TABLE_NAME + "`";
        Cursor cursor = db.rawQuery(query, null);
        String c;
        c = "not found";
        if (cursor.moveToFirst()) {
            c = cursor.getString(0);

        }

        return c;
    }




    public int checkuser(String emailstr)
    {
        db=this.getReadableDatabase();
        String queryy="select * from "+TABLE_NAME+" where email ='"+emailstr+"'";
        Cursor cursor1 = db.rawQuery(queryy, null);
       int cnt1 = cursor1.getCount();
        cursor1.close();
        return cnt1;

    }
@Override
 public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
    {
        String query="DROP TABLE IF EXISTS"+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}

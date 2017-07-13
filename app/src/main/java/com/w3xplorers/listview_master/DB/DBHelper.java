package com.w3xplorers.listview_master.DB;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by DELL on 7/10/2017.
 */

public class DBHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;


    private static final String CREATE_CONTACT_TABLE = "create table " + Config.KEY_TABLE +
            "(id integer primary key autoincrement," +
            Config.KEY_CONTACTNAME + " text," +
            Config.KEY_CONTACTNO + " text);";
    private static final String DROP_CONTACT_TABLE = "drop table if exists "+Config.KEY_TABLE;

    public DBHelper(Context context) {
        super(context, Config.DBNAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_CONTACT_TABLE);
        onCreate(db);
    }


    public void saveToDatabase(Context context,String contact_name,String contact_no,
                                        SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.KEY_CONTACTNAME,contact_name);
        contentValues.put(Config.KEY_CONTACTNO,contact_no);


        long rowInserted = database.insert(Config.KEY_TABLE,null,contentValues);
        if(rowInserted != -1)
            Toast.makeText(context, "New row added, row id: " + rowInserted, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show();
    }

    public Cursor getContactList() {
        //Open connection to read only
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery =  "SELECT  rowid as " +
                Config.KEY_ROWID + "," +
                Config.KEY_ID + "," +
                Config.KEY_CONTACTNAME + "," +
                Config.KEY_CONTACTNO +
                " FROM " + Config.KEY_TABLE;


        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;


    }
}

package com.ymg.personaldictionary.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * to create this I use https://www.youtube.com/watch?v=kDZES1wtKUY
 */
public class DBhelper extends SQLiteOpenHelper {
    SQLiteDatabase db = null;
    public final static String DATABASE_NAME = "database.db";
    public final static String TABLE_NAME = "dictionary";
    //the columns:
    public final static String COL_1 = "ID";
    public final static String COL_2 = "SOURCE_WORD";
    public final static String COL_3 = "MEANING_WORD";
    public final static String COL_4 = "SCORE";
    public final static String COL_5 = "DESCRIPTION";

    public DBhelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME +" ( "+
                        COL_1+  " INTEGER PRIMARY KEY AUTOINCREMENT,   "+
                        COL_2+  " TEXT,                                "+
                        COL_3+  " TEXT,                                "+
                        COL_4+  " INTEGER,                             "+
                        COL_5+  " TEXT                                 "+
                        " ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public boolean doInsert(ContentValues theVal){// how fill the ContentValues in the 31:47
        long result = db.insert(TABLE_NAME,null, theVal);

        if(result == -1){return false;}
        else{return true;}
    }

    public Cursor doSelect(String selectQuary){
        Cursor res = db.rawQuery(selectQuary, null);
        return res;
    }

    public int doUpdate(String table,ContentValues values,String whereClause){
        int res = db.update(table,values, whereClause, null);
        return res;
    }
    public int doDelete(String table,String whereClause) {
        int res = db.delete(table, whereClause,null);
        return res;
    }

}

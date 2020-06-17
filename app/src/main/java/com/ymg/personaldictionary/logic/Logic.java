package com.ymg.personaldictionary.logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.ymg.personaldictionary.DB.DBhelper;
import com.ymg.personaldictionary.ui.home.HomeFragment;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

public class Logic {

    //todo: how fill the ContentValues in the 31:47   https://www.youtube.com/watch?v=kDZES1wtKUY
    public static String insertNewWord(Context context,String sourch,String meaning,int score,String description ){
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.COL_2,sourch);
        cv.put(DBhelper.COL_3,meaning);
        cv.put(DBhelper.COL_4,score);
        cv.put(DBhelper.COL_5,description);
        DBhelper myDB = new DBhelper(context);
        if(myDB.doInsert(cv)){
            return "successes";
        }
        else {
            return "fail";
        }
    }

    public static void getAllRow(HomeFragment theHomeFragment){
        String select = "select * from "+DBhelper.TABLE_NAME;

        DBhelper myDB = new DBhelper(((Fragment)(theHomeFragment)).getContext());
        Cursor res = myDB.doSelect(select);
        while (res.moveToNext()) {
            String id = ""+res.getInt(0);
            String source = res.getString(1);
            String meaning =res.getString(2);
            String score =""+res.getInt(3);
            String description =res.getString(4) ;
            theHomeFragment.rows.add(new Word(id,source,meaning,description,score));
        }
        theHomeFragment.creatTheRecyclerView();
    }

    public static Word getOneRowByID(Context theContext, int theId){
        String select = "select * from "+DBhelper.TABLE_NAME+" WHERE "+DBhelper.COL_1+" = "+theId;

        DBhelper myDB = new DBhelper(theContext);
        Cursor res = myDB.doSelect(select);

        res.moveToNext();//point at the beginning to nothing
        String id = ""+res.getInt(0);
        String source = res.getString(1);
        String meaning =res.getString(2);
        String score =""+res.getInt(3);
        String description =res.getString(4) ;
        return new Word(id,source,meaning,description,score);
    }

    public static boolean changeScore(Context theContext, int newScore, String id){
        //String update = "UPDATE "+DBhelper.TABLE_NAME+" SET "+DBhelper.COL_4+" ="+newScore+" WHERE "+DBhelper.COL_1+" ="+id;
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.COL_4,newScore);
        String where = DBhelper.COL_1+" ="+id;
        DBhelper myDB = new DBhelper(theContext);
        int res = myDB.doUpdate(DBhelper.TABLE_NAME, cv,where);
        if(res == 1){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean editWord(Context context,String id,String sourch,String meaning,String score,String description ){
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.COL_2,sourch);
        cv.put(DBhelper.COL_3,meaning);
        cv.put(DBhelper.COL_4,score);
        cv.put(DBhelper.COL_5,description);
        String where = DBhelper.COL_1+" ="+id;
        DBhelper myDB = new DBhelper(context);
        if(myDB.doUpdate(DBhelper.TABLE_NAME,cv,where)==1){
            return true;
        }
        else {
            return false;
        }
    }
    public static  boolean deleteWord(){


        return true;
    }

}

package com.thud.thpt_dh.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.thud.thpt_dh.datas.DBHelper;
import com.thud.thpt_dh.utils.interfaces.Def;

/**
 * Created by khanh on 5/11/2016.
 */
public class AllDAL {
    private Context context;

    /*sqlite*/
    private DBHelper db_helper;
    private SQLiteDatabase database;
    public static String path_document = Def.STRING_EMPTY;

    public AllDAL(Context current){
        this.context = current;
        db_helper = new DBHelper(context);
        database = db_helper.getWritableDatabase();
    }

    public boolean dropAllTable(){
        try{
            db_helper.DropAllTable(database);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

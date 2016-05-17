package com.thud.thpt_dh.datas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.thud.thpt_dh.R;
import com.thud.thpt_dh.model.BaiHoc;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.Def;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khanh on 5/12/2016.
 */
public class BaiHocDAL {
    private Context context;
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public BaiHocDAL(Context current){
        this.context = current;

        dbHelper =  new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    //get all bai hoc from server
    public Result<ArrayList<BaiHoc>> getAllBaiHoc() {
        final ArrayList<BaiHoc> arr_BaiHoc = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(""+BaiHoc.TENBANG);
        query.setLimit(1000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if ( e ==  null){
                    for (ParseObject ob : objects){
                        BaiHoc baiHoc = new BaiHoc(ob.getObjectId(),
                                ob.getString(""+BaiHoc.MACHUONG),
                                ob.getString(""+BaiHoc.TENBH));
                        arr_BaiHoc.add(baiHoc);
                    }

                    if (arr_BaiHoc.size() > 0){
                        Result<String> result = new AllDAL(context).saveAll(arr_BaiHoc);
                    }
                }
            }
        });

        return new Result<ArrayList<BaiHoc>>(ResultStatus.TRUE, arr_BaiHoc);
    }

    /*
    * insert all data from server
    * */
    public Result<String> insertBaiHocFromLocal(ArrayList<BaiHoc> baiHocs){
        try {
            database = dbHelper.getWritableDatabase();
            if (database!=null) {
                try {
                    database.beginTransaction();

                    for (BaiHoc baiHoc : baiHocs) {
                        ContentValues baihocDb = DbModel.getContentValueBaiHoc(baiHoc);
                        database.insert(BaiHoc.TENBANG, null, baihocDb);
                    }

                    database.setTransactionSuccessful();
                    database.endTransaction();
                    database.close();
                }catch (SQLiteException e)
                {
                    e.printStackTrace();
                    Log.e(Def.ERROR, e.getMessage());
                }
            }
            return new Result<String>(ResultStatus.TRUE, context.getResources().getString(R.string.msg_data_has_been_saved));
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(Def.ERROR, e.getMessage());
        }

        return new Result<String>(ResultStatus.FALSE, null);
    }

    public Result<ArrayList<BaiHoc>> getAllBaiHocFromLocal(String machuong){
        database = dbHelper.getReadableDatabase();
        ArrayList<BaiHoc> baiHocs = new ArrayList<>();
        try {
            String query_baihoc = "SELECT * FROM " + BaiHoc.TENBANG + " WHERE " + BaiHoc.MACHUONG + " = '" +machuong +"'";
            Cursor cursor = database.rawQuery(query_baihoc, null);
            if(cursor != null && cursor.moveToFirst()){
                do{
                    BaiHoc baiHoc = DbModel.getBaiHoc(cursor);
                    baiHocs.add(baiHoc);
                }while (cursor.moveToNext());
            }

            database.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
            Log.e(Def.ERROR, ex.getMessage());
        }
        return  new Result<ArrayList<BaiHoc>>(ResultStatus.TRUE, baiHocs);
    }
}

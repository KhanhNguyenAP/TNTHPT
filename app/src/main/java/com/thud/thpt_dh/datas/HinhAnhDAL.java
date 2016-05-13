package com.thud.thpt_dh.datas;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.thud.thpt_dh.R;
import com.thud.thpt_dh.model.HinhAnh;
import com.thud.thpt_dh.model.HinhAnhChiTiet;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.Def;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by khanh on 5/12/2016.
 */
public class HinhAnhDAL {
    private Context context;
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public HinhAnhDAL(Context current){
        this.context = current;
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    //get all Hinh Anh from server
    public Result<ArrayList<HinhAnh>> getAllHinhAnh() {
        final ArrayList<HinhAnh> arr_HinhAnh = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(""+HinhAnh.TENBANG);
        query.setLimit(1000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if ( e ==  null){
                    for (ParseObject ob : objects){
                        HinhAnh hinhAnh = new HinhAnh(ob.getObjectId(),
                                ob.getParseFile(""+HinhAnh.HINHANH),
                                ob.getString(""+HinhAnh.MABAIGIAI),
                                ob.getString(""+HinhAnh.MACHITIETBAIHOC));
                        arr_HinhAnh.add(hinhAnh);
                    }
                }
            }
        });

        return new Result<ArrayList<HinhAnh>>(ResultStatus.TRUE, arr_HinhAnh);
    }

    /*
    * insert all data from server
    * */
    public Result<String> insertHinhAnhFromLocal(ArrayList<HinhAnh> hinhAnhs){
        try {
            database = dbHelper.getWritableDatabase();
            database.beginTransaction();

            for(HinhAnh hinhAnh : hinhAnhs){
                ContentValues HinhAnhDb = DbModel.getContentValueHinhAnh(hinhAnh);

                //insert database
                database.insert(dbHelper.TABLE_HINHANH, null, HinhAnhDb);
            }

            database.setTransactionSuccessful();
            database.endTransaction();
            database.close();

            return new Result<String>(ResultStatus.TRUE, context.getResources().getString(R.string.msg_data_has_been_saved));
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(Def.ERROR, e.getMessage());
        }

        return new Result<String>(ResultStatus.FALSE, null);
    }
}

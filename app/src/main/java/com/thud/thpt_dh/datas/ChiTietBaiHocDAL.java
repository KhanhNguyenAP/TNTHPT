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
import com.thud.thpt_dh.model.ChiTietBaiHoc;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.Def;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khanh on 5/12/2016.
 */
public class ChiTietBaiHocDAL {
    private Context context;
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public ChiTietBaiHocDAL(Context current){
        this.context = current;
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    //get all chi tiet bai hoc from server
    public Result<ArrayList<ChiTietBaiHoc>> getAllChiTietBaiHoc() {
        final ArrayList<ChiTietBaiHoc> arr_CTBH = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(""+ChiTietBaiHoc.TENBANG);
        query.setLimit(1000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if ( e ==  null){
                    for (ParseObject ob : objects){
                        ChiTietBaiHoc CTBH = new ChiTietBaiHoc(ob.getObjectId(),
                                ob.getString(""+ChiTietBaiHoc.MABAIHOC),
                                ob.getString(""+ChiTietBaiHoc.TENCTBH),
                                ob.getString(""+ChiTietBaiHoc.NOIDUNGCTBH));
                        arr_CTBH.add(CTBH);
                    }
                }
            }
        });

        return new Result<ArrayList<ChiTietBaiHoc>>(ResultStatus.TRUE, arr_CTBH);
    }

    /*
    * insert all data from server
    * */
    public Result<String> insertChiTietBaiHocFromLocal(ArrayList<ChiTietBaiHoc> chiTietBaiHocs){
        try {
            database = dbHelper.getWritableDatabase();
            database.beginTransaction();

            for(ChiTietBaiHoc chiTietBaiHoc : chiTietBaiHocs){
                ContentValues CTBHDb = DbModel.getContentValueChiTietBaiHoc(chiTietBaiHoc);

                //insert database
                database.insert(dbHelper.TABLE_CHITIETBAIHOC, null, CTBHDb);
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

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
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    //get all bai hoc from server
    public Result<ArrayList<BaiHoc>> getAllBaiHoc() {
        final ArrayList<BaiHoc> arr_BaiHoc = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(""+BaiHoc.TENBANG);
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
            database.beginTransaction();

            for(BaiHoc baiHoc : baiHocs){
                ContentValues baihocDb = DbModel.getContentValueBaiHoc(baiHoc);

                //insert database
                database.insert(dbHelper.TABLE_BAIHOC, null, baihocDb);
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

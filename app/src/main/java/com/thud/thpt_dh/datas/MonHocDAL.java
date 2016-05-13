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
import com.thud.thpt_dh.model.MonHoc;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.Def;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khanh on 5/12/2016.
 */
public class MonHocDAL {
    private Context context;
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public MonHocDAL(Context current){
        this.context = current;
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    //get all Mon Hoc from server
    public Result<ArrayList<MonHoc>> getAllMonHoc() {
        final ArrayList<MonHoc> arr_MonHoc = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(""+MonHoc.TENBANG);
        query.setLimit(1000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if ( e ==  null){
                    for (ParseObject ob : objects){
                        MonHoc monHoc = new MonHoc(ob.getObjectId(),
                                ob.getString(""+MonHoc.TENMON));
                        arr_MonHoc.add(monHoc);
                    }
                }
            }
        });

        return new Result<ArrayList<MonHoc>>(ResultStatus.TRUE, arr_MonHoc);
    }

    /*
    * insert all data from server
    * */
    public Result<String> insertMonHocFromLocal(ArrayList<MonHoc> monHocs){
        try {
            database = dbHelper.getWritableDatabase();
            database.beginTransaction();

            for(MonHoc monHoc : monHocs){
                ContentValues MonHocDb = DbModel.getContentValueMonHoc(monHoc);

                //insert database
                database.insert(dbHelper.TABLE_MONHOC, null, MonHocDb);
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

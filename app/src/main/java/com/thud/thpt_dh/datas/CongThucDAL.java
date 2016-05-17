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
import com.thud.thpt_dh.model.CongThuc;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.Def;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khanh on 5/12/2016.
 */
public class CongThucDAL {
    private Context context;
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public CongThucDAL(Context current){
        this.context = current;
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    //get all cong thuc from server
    public Result<ArrayList<CongThuc>> getAllCongThuc() {
        final ArrayList<CongThuc> arr_CongThuc = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(""+CongThuc.TENBANG);
        query.setLimit(1000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if ( e ==  null){
                    for (ParseObject ob : objects){
                        CongThuc congthuc = new CongThuc(ob.getObjectId(),
                                ob.getString(""+CongThuc.MACTBH),
                                ob.getString(""+CongThuc.TENCT),
                                ob.getString(""+CongThuc.NOIDUNGCT));
                        arr_CongThuc.add(congthuc);
                    }

                    if (arr_CongThuc.size() >0){
                        Result<String> result = new AllDAL(context).saveAll(arr_CongThuc);
                    }
                }
            }
        });

        return new Result<ArrayList<CongThuc>>(ResultStatus.TRUE, arr_CongThuc);
    }

    /*
    * insert all data from server
    * */
    public Result<String> insertCongThucFromLocal(ArrayList<CongThuc> congThucs){
        try {
            database = dbHelper.getWritableDatabase();
            database.beginTransaction();

            for(CongThuc congThuc : congThucs){
                ContentValues CongThucDb = DbModel.getContentValueCongThuc(congThuc);

                //insert database
                database.insert(CongThuc.TENBANG, null, CongThucDb);
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

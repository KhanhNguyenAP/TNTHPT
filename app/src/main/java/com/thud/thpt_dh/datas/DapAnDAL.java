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
import com.thud.thpt_dh.model.DapAn;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.Def;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khanh on 5/12/2016.
 */
public class DapAnDAL {
    private Context context;
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public DapAnDAL(Context current){
        this.context = current;
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    //get all dapan from server
    public Result<ArrayList<DapAn>> getAllDapAn() {
        final ArrayList<DapAn> arr_DapAn = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(""+DapAn.TENBANG);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if ( e ==  null){
                    for (ParseObject ob : objects){
                        DapAn dapAn = new DapAn(ob.getObjectId(),
                                ob.getInt(""+DapAn.DUNGSAI),
                                ob.getString(""+DapAn.MACAUHOI),
                                ob.getString(""+DapAn.NOIDUNGDA));
                        arr_DapAn.add(dapAn);
                    }
                }
            }
        });

        return new Result<ArrayList<DapAn>>(ResultStatus.TRUE, arr_DapAn);
    }

    /*
    * insert all data from server
    * */
    public Result<String> insertDapAnFromLocal(ArrayList<DapAn> dapAns){
        try {
            database = dbHelper.getWritableDatabase();
            database.beginTransaction();

            for(DapAn dapAn : dapAns){
                ContentValues DapAnDb = DbModel.getContentValueDapAn(dapAn);

                //insert database
                database.insert(dbHelper.TABLE_DAPAN, null, DapAnDb);
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

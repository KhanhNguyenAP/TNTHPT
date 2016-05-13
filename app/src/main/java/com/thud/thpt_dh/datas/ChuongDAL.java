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
import com.thud.thpt_dh.model.Chuong;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.Def;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khanh on 5/12/2016.
 */
public class ChuongDAL {
    private Context context;
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public ChuongDAL(Context current){
        this.context = current;
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    //get all chuong from server
    public Result<ArrayList<Chuong>> getAllChuong() {
        final ArrayList<Chuong> arr_Chuong = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(""+Chuong.TENBANG);
        query.setLimit(1000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if ( e ==  null){
                    for (ParseObject ob : objects){
                        Chuong chuong = new Chuong(ob.getObjectId(),
                                ob.getString(""+Chuong.MAMON),
                                ob.getString(""+Chuong.TENCHUONG));
                        arr_Chuong.add(chuong);
                    }
                }
            }
        });

        return new Result<ArrayList<Chuong>>(ResultStatus.TRUE, arr_Chuong);
    }

    /*
    * insert all data from server
    * */
    public Result<String> insertChuongFromLocal(ArrayList<Chuong> chuongs){
        try {
            database = dbHelper.getWritableDatabase();
            database.beginTransaction();

            for(Chuong chuong : chuongs){
                ContentValues ChuongDb = DbModel.getContentValueChuong(chuong);

                //insert database
                database.insert(dbHelper.TABLE_CHUONG, null, ChuongDb);
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

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
import com.thud.thpt_dh.model.CauHoi;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.Def;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khanh on 5/12/2016.
 */
public class CauHoiDAL {
    private Context context;
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public CauHoiDAL(Context current){
        this.context = current;
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    //get all cau hoi from server
    public Result<ArrayList<CauHoi>> getAllCauHoi() {
        final ArrayList<CauHoi> arr_CauHoi = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(""+CauHoi.TENBANG);
        query.setLimit(1000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if ( e ==  null){
                    for (ParseObject ob : objects){
                        CauHoi cauHoi = new CauHoi(ob.getObjectId(),
                                ob.getInt(""+CauHoi.LOAI),
                                ob.getString(""+CauHoi.MADETHI),
                                ob.getString(""+CauHoi.NOIDUNGCH));
                        arr_CauHoi.add(cauHoi);
                    }

                    if (arr_CauHoi.size() >0){
                        Result<String> result = new AllDAL(context).saveAll(arr_CauHoi);
                    }
                }
            }
        });

        return new Result<ArrayList<CauHoi>>(ResultStatus.TRUE, arr_CauHoi);
    }

    /*
    * insert all data from server
    * */
    public Result<String> insertCauHoiFromLocal(ArrayList<CauHoi> cauHois){
        try {
            database = dbHelper.getWritableDatabase();
            database.beginTransaction();

            for(CauHoi cauHoi : cauHois){
                ContentValues cauHoiDb = DbModel.getContentValueCauHoi(cauHoi);

                //insert database
                database.insert(CauHoi.TENBANG, null, cauHoiDb);
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

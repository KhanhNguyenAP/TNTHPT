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
import com.thud.thpt_dh.model.DieuKien;
import com.thud.thpt_dh.model.DinhLy;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.Def;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khanh on 5/12/2016.
 */
public class DinhLyDAL {
    private Context context;
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public DinhLyDAL(Context current){
        this.context = current;
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    //get all Dinh Ly from server
    public Result<ArrayList<DinhLy>> getAllDinhLy() {
        final ArrayList<DinhLy> arr_DinhLy = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(""+DinhLy.TENBANG);
        query.setLimit(1000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if ( e ==  null){
                    for (ParseObject ob : objects){
                        DinhLy dinhLy = new DinhLy(ob.getObjectId(),
                                ob.getString(""+DinhLy.MABAIHOC),
                                ob.getString(""+DinhLy.TENDL),
                                ob.getString(""+DinhLy.NOIDUNGDL));
                        arr_DinhLy.add(dinhLy);
                    }
                }
            }
        });

        return new Result<ArrayList<DinhLy>>(ResultStatus.TRUE, arr_DinhLy);
    }

    /*
    * insert all data from server
    * */
    public Result<String> insertDinhLyFromLocal(ArrayList<DinhLy> dinhLies){
        try {
            database = dbHelper.getWritableDatabase();
            database.beginTransaction();

            for(DinhLy dinhLy : dinhLies){
                ContentValues DinhLyDb = DbModel.getContentValueDinhLy(dinhLy);

                //insert database
                database.insert(dbHelper.TABLE_DINHLY, null, DinhLyDb);
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

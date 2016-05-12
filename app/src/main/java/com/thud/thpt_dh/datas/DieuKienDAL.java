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
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.Def;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khanh on 5/12/2016.
 */
public class DieuKienDAL {
    private Context context;
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public DieuKienDAL(Context current){
        this.context = current;
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    //get all dieu kien from server
    public Result<ArrayList<DieuKien>> getAllDieuKien() {
        final ArrayList<DieuKien> arr_DieuKien = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(""+DieuKien.TENBANG);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if ( e ==  null){
                    for (ParseObject ob : objects){
                        DieuKien dieuKien = new DieuKien(ob.getObjectId(),
                                ob.getString(""+DieuKien.TENDK),
                                ob.getString(""+DieuKien.NOIDUNGDK));
                        arr_DieuKien.add(dieuKien);
                    }
                }
            }
        });

        return new Result<ArrayList<DieuKien>>(ResultStatus.TRUE, arr_DieuKien);
    }

    /*
    * insert all data from server
    * */
    public Result<String> insertDieuKienFromLocal(ArrayList<DieuKien> dieuKiens){
        try {
            database = dbHelper.getWritableDatabase();
            database.beginTransaction();

            for(DieuKien dieuKien : dieuKiens){
                ContentValues DieuKienDb = DbModel.getContentValueDieuKien(dieuKien);

                //insert database
                database.insert(dbHelper.TABLE_DIEUKIEN, null, DieuKienDb);
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

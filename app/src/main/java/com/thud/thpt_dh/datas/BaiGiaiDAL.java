package com.thud.thpt_dh.datas;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.preference.Preference;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.thud.thpt_dh.R;
import com.thud.thpt_dh.model.BaiGiai;
import com.thud.thpt_dh.model.BaiHoc;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.Def;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khanh on 5/12/2016.
 */
public class BaiGiaiDAL {
    private Context context;

    /* SQLite */
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public BaiGiaiDAL(Context current) {
        this.context = current;
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public Result<ArrayList<BaiGiai>> getAllBaiGiai() {
        final ArrayList<BaiGiai> arr_BaiGiai = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery(""+BaiGiai.TENBANG);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if ( e ==  null){
                    for (ParseObject ob : objects){
                        BaiGiai baiGiai = new BaiGiai(ob.getObjectId(),
                                                    ob.getString(""+BaiGiai.TENBAIGIAI),
                                                    ob.getString(""+BaiGiai.NOIDUNGBG));
                        arr_BaiGiai.add(baiGiai);
                    }
                }
            }
        });

        return new Result<ArrayList<BaiGiai>>(ResultStatus.TRUE, arr_BaiGiai);
    }

    /*
    * insert all data from server
    * */
    public Result<String> insertBaiGiaiFromLocal(ArrayList<BaiGiai> baiGiais){
        try {
            database = dbHelper.getWritableDatabase();
            database.beginTransaction();

            for(BaiGiai baiGiai : baiGiais){
                ContentValues baigiaiDb = DbModel.getContentValueBaiGiai(baiGiai);

                //insert database
                database.insert(dbHelper.TABLE_BAIGIAI, null, baigiaiDb);
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
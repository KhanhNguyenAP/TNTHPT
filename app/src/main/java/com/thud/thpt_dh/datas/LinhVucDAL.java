package com.thud.thpt_dh.datas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.thud.thpt_dh.R;
import com.thud.thpt_dh.model.Chuong;
import com.thud.thpt_dh.model.LinhVuc;
import com.thud.thpt_dh.model.MonHoc;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.Def;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khanh on 5/12/2016.
 */
public class LinhVucDAL {
    private Context context;
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public LinhVucDAL(Context current){
        this.context = current;
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    //get all chuong from server
    public Result<ArrayList<LinhVuc>> getAllLinhVuc() {
        final ArrayList<LinhVuc> arr_LinhVuc = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(""+LinhVuc.TENBANG);
        query.setLimit(1000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if ( e ==  null){
                    for (ParseObject ob : objects){
                        LinhVuc linhVuc = new LinhVuc(ob.getObjectId(),
                                ob.getString(""+LinhVuc.MAMON),
                                ob.getString(""+LinhVuc.TENLINHVUC));
                        arr_LinhVuc.add(linhVuc);
                    }

                    if (arr_LinhVuc.size() > 0){
                        Result<String> result = new AllDAL(context).saveAll(arr_LinhVuc);
                    }
                }
            }
        });


        return new Result<ArrayList<LinhVuc>>(ResultStatus.TRUE, arr_LinhVuc);
    }

    /*
    * insert all data from server
    * */
    public Result<String> insertLinhVucFromLocal(ArrayList<LinhVuc> linhVucs){
        try {
            database = dbHelper.getWritableDatabase();
            database.beginTransaction();

            for(LinhVuc linhVuc : linhVucs){
                ContentValues LinhVucDb = DbModel.getContentValueLinhVuc(linhVuc);

                //insert database
                database.insert(LinhVuc.TENBANG, null, LinhVucDb);
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

    /*-------------------SQLite--------------------*/

    public Result<ArrayList<LinhVuc>> getAllLinhVucFromLocal(int mamon){
        database = dbHelper.getReadableDatabase();
        MonHoc monHoc = new MonHoc();
        ArrayList<LinhVuc> linhVucs = new ArrayList<>();
        try {
            String query_mon = "SELECT * FROM " + MonHoc.TENBANG + " WHERE " + MonHoc.MAMON + " = " +mamon;
            Cursor cursor_mon = database.rawQuery(query_mon, null);

            if(cursor_mon != null && cursor_mon.moveToFirst()){
                monHoc = DbModel.getMonHoc(cursor_mon);
            }

            String query = "SELECT * FROM " + LinhVuc.TENBANG + " WHERE "
                    + LinhVuc.MAMON + " = '" + monHoc.getId() +"'";

            Cursor cursor = database.rawQuery(query, null);
            if(cursor != null && cursor.moveToFirst()){
                do{
                    LinhVuc linhVuc = DbModel.getLinhVuc(cursor);
                    linhVucs.add(linhVuc);
                }while (cursor.moveToNext());
            }

            database.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
            Log.e(Def.ERROR, ex.getMessage());
        }
        return  new Result<ArrayList<LinhVuc>>(ResultStatus.TRUE, linhVucs);
    }
}

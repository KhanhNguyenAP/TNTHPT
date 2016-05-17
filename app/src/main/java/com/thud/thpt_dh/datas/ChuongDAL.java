package com.thud.thpt_dh.datas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.thud.thpt_dh.R;
import com.thud.thpt_dh.model.BaiHoc;
import com.thud.thpt_dh.model.CauHoi;
import com.thud.thpt_dh.model.ChiTietBaiHoc;
import com.thud.thpt_dh.model.Chuong;
import com.thud.thpt_dh.model.CongThuc;
import com.thud.thpt_dh.model.DapAn;
import com.thud.thpt_dh.model.DeThiThu;
import com.thud.thpt_dh.model.DieuKien;
import com.thud.thpt_dh.model.DinhLy;
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

                    if (arr_Chuong.size() > 0){
                        Result<String> result = new AllDAL(context).saveAll(arr_Chuong);
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
                database.insert(Chuong.TENBANG, null, ChuongDb);
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

    public Result<ArrayList<Chuong>> getAllChuongFromLocal(int mamon){
        database = dbHelper.getReadableDatabase();
        MonHoc monHoc = new MonHoc();
        ArrayList<Chuong> chuongs = new ArrayList<>();
        try {
            String query_mon = "SELECT * FROM " + MonHoc.TENBANG + " WHERE " + MonHoc.MAMON + " = " +mamon;
            Cursor cursor_mon = database.rawQuery(query_mon, null);

            if(cursor_mon != null && cursor_mon.moveToFirst()){
                monHoc = DbModel.getMonHoc(cursor_mon);
            }

            String query = "SELECT * FROM " + Chuong.TENBANG + " WHERE "
                    + Chuong.MAMON + " = '" + monHoc.getId() +"'";

            Cursor cursor = database.rawQuery(query, null);
            if(cursor != null && cursor.moveToFirst()){
                do{
                    Chuong chuong = DbModel.getChuong(cursor);
                    chuongs.add(chuong);
                }while (cursor.moveToNext());
            }

            database.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
            Log.e(Def.ERROR, ex.getMessage());
        }
        return  new Result<ArrayList<Chuong>>(ResultStatus.TRUE, chuongs);
    }
}

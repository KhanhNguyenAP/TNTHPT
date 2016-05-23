package com.thud.thpt_dh.datas;

import android.content.Context;
import android.content.Loader;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.thud.thpt_dh.model.BaiGiai;
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
import com.thud.thpt_dh.model.ViDu;
import com.thud.thpt_dh.utils.interfaces.Def;

/**
 * Created by KhanhNguyen on 10/20/2015.
 */
public class DBHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "onthithpt_db1.sqlite";
    protected static String db_path = DATABASE_NAME;
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase database;

    public static final String sql_baigiai = "CREATE TABLE IF NOT EXISTS " + BaiGiai.TENBANG + "(" +
           BaiGiai.ID + " TEXT PRIMARY KEY, " +
           BaiGiai.TENBAIGIAI + " TEXT, " +
           BaiGiai.NOIDUNGBG + " TEXT )";

    public static final String sql_baihoc = "CREATE TABLE IF NOT EXISTS " + BaiHoc.TENBANG + "(" +
            BaiHoc.ID + " TEXT PRIMARY KEY, " +
            BaiHoc.TENBH + " TEXT, " +
            BaiHoc.MACHUONG + " TEXT )";

    public static final String sql_cauhoi = "CREATE TABLE IF NOT EXISTS " + CauHoi.TENBANG + "(" +
            CauHoi.ID + " TEXT PRIMARY KEY, " +
            CauHoi.LOAI + " TEXT, " +
            CauHoi.MADETHI + " TEXT, " +
            CauHoi.NOIDUNGCH + " TEXT )";

    public static final String sql_chitietbaihoc = "CREATE TABLE IF NOT EXISTS " + ChiTietBaiHoc.TENBANG + "(" +
            ChiTietBaiHoc.ID + " TEXT PRIMARY KEY, " +
            ChiTietBaiHoc.MABAIHOC + " TEXT, " +
            ChiTietBaiHoc.NOIDUNGCTBH + " TEXT, " +
            ChiTietBaiHoc.NOIDUNGCT + " TEXT, " +
            ChiTietBaiHoc.TENCTBH + " TEXT )";

    public static final String sql_chuong = "CREATE TABLE IF NOT EXISTS " + Chuong.TENBANG + "(" +
            Chuong.ID + " TEXT PRIMARY KEY, " +
            Chuong.TENCHUONG + " TEXT, " +
            Chuong.MAMON + " TEXT )";

    public static final String sql_congthuc = "CREATE TABLE IF NOT EXISTS " + CongThuc.TENBANG + "(" +
            CongThuc.ID + " TEXT PRIMARY KEY, " +
            CongThuc.MACTBH + " TEXT, " +
            CongThuc.NOIDUNGCT + " TEXT, " +
            CongThuc.TENCT + " TEXT )";

    public static final String sql_dapan = "CREATE TABLE IF NOT EXISTS " + DapAn.TENBANG + "(" +
            DapAn.ID + " TEXT PRIMARY KEY, " +
            DapAn.MACAUHOI + " TEXT, " +
            DapAn.DUNGSAI + " INTEGER, " +
            DapAn.NOIDUNGDA + " TEXT )";

    public static final String sql_dethithu = "CREATE TABLE IF NOT EXISTS " + DeThiThu.TENBANG + "(" +
            DeThiThu.ID + " TEXT PRIMARY KEY, " +
            DeThiThu.MAMONHOC + " TEXT, " +
            DeThiThu.SOLUONG + " INTEGER, " +
            DeThiThu.LOAI + " INTEGER, " +
            DeThiThu.TENDE + " TEXT )";

    public static final String sql_dieukien = "CREATE TABLE IF NOT EXISTS " + DieuKien.TENBANG + "(" +
            DieuKien.ID + " TEXT PRIMARY KEY, " +
            DieuKien.NOIDUNGDK + " TEXT, " +
            DieuKien.TENDK + " TEXT )";

    public static final String sql_dinhly = "CREATE TABLE IF NOT EXISTS " + DinhLy.TENBANG + "(" +
            DinhLy.ID + " TEXT PRIMARY KEY, " +
            DinhLy.TENDL + " TEXT, " +
            DinhLy.MABAIHOC + " TEXT, " +
            DinhLy.NOIDUNGDL + " TEXT )";

    public static final String sql_hinhanh = "CREATE TABLE IF NOT EXISTS " + HinhAnh.TENBANG + "(" +
            HinhAnh.ID + " TEXT PRIMARY KEY, " +
            HinhAnh.MABAIGIAI + " TEXT, " +
            HinhAnh.HINHANH + " TEXT, " +
            HinhAnh.MACHITIETBAIHOC + " TEXT )";

    public static final String sql_monhoc = "CREATE TABLE IF NOT EXISTS " + MonHoc.TENBANG + "(" +
            MonHoc.ID + " TEXT PRIMARY KEY, " +
            MonHoc.MAMON + "  INTEGER, " +
            MonHoc.TENMON + " TEXT )";

    public static final String sql_vidu = "CREATE TABLE IF NOT EXISTS " + ViDu.TENBANG + "(" +
            ViDu.ID + " TEXT PRIMARY KEY, " +
            ViDu.NOIDUNGVD + " TEXT, " +
            ViDu.MACONGTHUC + " TEXT, " +
            ViDu.BAIGIAIVD + " TEXT )";

    public static DBHelper DBHelperInstance = null;
    public DBHelper(Context context){
        super(context, db_path, null, DATABASE_VERSION);
        try {
            database = this.getWritableDatabase();
        }
        catch (Exception e){e.printStackTrace();
            Log.e(Def.ERROR, e.getMessage());
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try{
            db.execSQL(sql_baigiai);
            db.execSQL(sql_baihoc);
            db.execSQL(sql_cauhoi);
            db.execSQL(sql_chitietbaihoc);
            db.execSQL(sql_chuong);
            db.execSQL(sql_congthuc);
            db.execSQL(sql_dapan);
            db.execSQL(sql_dethithu);
            db.execSQL(sql_dieukien);
            db.execSQL(sql_dinhly);
            db.execSQL(sql_monhoc);
            db.execSQL(sql_hinhanh);
            db.execSQL(sql_vidu);
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(Def.ERROR, e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + BaiHoc.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + BaiGiai.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + CauHoi.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + ChiTietBaiHoc.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + Chuong.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + CongThuc.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + DapAn.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + DeThiThu.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + DieuKien.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + DinhLy.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + MonHoc.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + HinhAnh.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + ViDu.TENBANG);
            onCreate(db);
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(Def.ERROR, e.getMessage());
        }
    }

    /*
    * Drop all table
    * */
    public void DropAllTable(SQLiteDatabase db){
        try {
            db.execSQL("DROP TABLE IF EXISTS " + BaiHoc.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + BaiGiai.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + CauHoi.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + ChiTietBaiHoc.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + Chuong.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + CongThuc.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + DapAn.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + DeThiThu.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + DieuKien.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + DinhLy.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + MonHoc.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + HinhAnh.TENBANG);
            db.execSQL("DROP TABLE IF EXISTS " + ViDu.TENBANG);
            onCreate(db);
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(Def.ERROR, e.getMessage());
        }
    }

    /**
     * Drop and Create table
     */
    public void DropAndCreateTable(SQLiteDatabase db, String oldTable, String newTable){
        try{
            db.execSQL("DROP TABLE IF EXISTS " + oldTable);
            db.execSQL(newTable);
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(Def.ERROR, e.getMessage());
        }
    }//--end DropAllTable
}

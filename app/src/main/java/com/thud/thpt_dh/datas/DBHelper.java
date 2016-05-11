package com.thud.thpt_dh.datas;

import android.content.Context;
import android.content.Loader;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
    public static final String DATABASE_NAME = "ONTHITHPT.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_BAIGIAI = "baigiai";
    public static final String TABLE_BAIHOC = "baihoc";
    public static final String TABLE_CAUHOI = "cauhoi";
    public static final String TABLE_CHITIETBAIHOC = "chitietbaihoc";
    public static final String TABLE_CHUONG = "chuong";
    public static final String TABLE_CONGTHUC = "congthuc";
    public static final String TABLE_DAPAN = "dapan";
    public static final String TABLE_DETHITHU = "dethithu";
    public static final String TABLE_DIEUKIEN = "dieukien";
    public static final String TABLE_DINHLY = "dinhly";
    public static final String TABLE_HINHANH = "hinhanh";
    public static final String TABLE_MONHOC = "monhoc";
    public static final String TABLE_VIDU = "vidu";

    private SQLiteDatabase database;

    private static final String sql_baigiai = "CREATE TABLE IF NOT EXISTS " + TABLE_BAIGIAI + "(" +
           BaiGiai.ID + " TEXT PRIMARY KEY, " +
           BaiGiai.TENBAIGIAI + " TEXT, " +
           BaiGiai.NOIDUNGBG + " TEXT )";

    private static final String sql_baihoc = "CREATE TABLE IF NOT EXISTS " + TABLE_BAIHOC + "(" +
            BaiHoc.ID + " TEXT PRIMARY KEY, " +
            BaiHoc.TENBH + " TEXT, " +
            BaiHoc.MACHUONG + " TEXT )";

    private static final String sql_cauhoi = "CREATE TABLE IF NOT EXISTS " + TABLE_CAUHOI + "(" +
            CauHoi.ID + " TEXT PRIMARY KEY, " +
            CauHoi.TENCH + " TEXT, " +
            CauHoi.LOAI + " TEXT, " +
            CauHoi.MADETHI + " TEXT, " +
            CauHoi.NOIDUNGCH + " TEXT )";

    private static final String sql_chitietbaihoc = "CREATE TABLE IF NOT EXISTS " + TABLE_CHITIETBAIHOC + "(" +
            ChiTietBaiHoc.ID + " TEXT PRIMARY KEY, " +
            ChiTietBaiHoc.MABAIHOC + " TEXT, " +
            ChiTietBaiHoc.NOIDUNGCTBH + " TEXT, " +
            ChiTietBaiHoc.TENCTBH + " TEXT )";

    private static final String sql_chuong = "CREATE TABLE IF NOT EXISTS " + TABLE_CHUONG + "(" +
            Chuong.ID + " TEXT PRIMARY KEY, " +
            Chuong.TENCHUONG + " TEXT, " +
            Chuong.MAMON + " TEXT )";

    private static final String sql_congthuc = "CREATE TABLE IF NOT EXISTS " + TABLE_CONGTHUC + "(" +
            CongThuc.ID + " TEXT PRIMARY KEY, " +
            CongThuc.MACTBH + " TEXT, " +
            CongThuc.NOIDUNGCT + " TEXT, " +
            CongThuc.TENCT + " TEXT )";

    private static final String sql_dapan = "CREATE TABLE IF NOT EXISTS " + TABLE_DAPAN + "(" +
            DapAn.ID + " TEXT PRIMARY KEY, " +
            DapAn.MACAUHOI + " TEXT, " +
            DapAn.DUNGSAI + " INTEGER, " +
            DapAn.NOIDUNGDA + " TEXT )";

    private static final String sql_dethithu = "CREATE TABLE IF NOT EXISTS " + TABLE_DETHITHU + "(" +
            DeThiThu.ID + " TEXT PRIMARY KEY, " +
            DeThiThu.MAMONHOC + " TEXT, " +
            DeThiThu.SOLUONG + " INTEGER, " +
            DeThiThu.TENDE + " TEXT )";

    private static final String sql_dieukien = "CREATE TABLE IF NOT EXISTS " + TABLE_DIEUKIEN + "(" +
            DieuKien.ID + " TEXT PRIMARY KEY, " +
            DieuKien.NOIDUNGDK + " TEXT, " +
            DieuKien.TENDK + " TEXT )";

    private static final String sql_dinhly = "CREATE TABLE IF NOT EXISTS " + TABLE_DINHLY + "(" +
            DinhLy.ID + " TEXT PRIMARY KEY, " +
            DinhLy.TENDL + " TEXT, " +
            DinhLy.MABAIHOC + " TEXT, " +
            DinhLy.NOIDUNGDL + " TEXT )";

    private static final String sql_hinhanh = "CREATE TABLE IF NOT EXISTS " + TABLE_HINHANH + "(" +
            HinhAnh.ID + " TEXT PRIMARY KEY, " +
            HinhAnh.MABAIGIAI + " TEXT, " +
            HinhAnh.HINHANH + " TEXT, " +
            HinhAnh.MACHITIETBAIHOC + " TEXT )";

    private static final String sql_monhoc = "CREATE TABLE IF NOT EXISTS " + TABLE_MONHOC + "(" +
            MonHoc.ID + " TEXT PRIMARY KEY, " +
            MonHoc.TENMON + " TEXT )";

    private static final String sql_vidu = "CREATE TABLE IF NOT EXISTS " + TABLE_VIDU + "(" +
            ViDu.ID + " TEXT PRIMARY KEY, " +
            ViDu.NOIDUNGVD + " TEXT, " +
            ViDu.MACONGTHUC + " TEXT, " +
            ViDu.BAIGIAIVD + " TEXT )";


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        try {
            database = this.getWritableDatabase();
        }
        catch (Exception e){
            e.printStackTrace();
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
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_BAIHOC);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_BAIGIAI);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAUHOI);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHITIETBAIHOC);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHUONG);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONGTHUC);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAPAN);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETHITHU);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIEUKIEN);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DINHLY);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONHOC);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_HINHANH);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIDU);
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
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_BAIHOC);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_BAIGIAI);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAUHOI);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHITIETBAIHOC);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHUONG);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONGTHUC);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAPAN);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETHITHU);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIEUKIEN);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DINHLY);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONHOC);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_HINHANH);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIDU);
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(Def.ERROR, e.getMessage());
        }
    }
}

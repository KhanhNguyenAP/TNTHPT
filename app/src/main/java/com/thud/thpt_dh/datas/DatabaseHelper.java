package com.thud.thpt_dh.datas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by KhanhNguyen on 10/20/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "ONTHITHPT.db";
    private static final int DATABASE_VERSION = 1;
    private Context mcontext;

    /*public static final String TABLE_BACHOC = "bachoc";
    public static final String BACHOC_MABACHOC = "mabachoc";
    public static final String BACHOC_TENBACHOC = "tenbachoc";
    public static final String BACHOC_TOMTATBH = "tomtatbh";

    public static final String TABLE_BAIGIAI = "baigiai";
    public static final String BAIGIAI_MABAIGIAI = "mabaigiai";
    public static final String BAIGIAI_MACAUHOI = "macauhoi";
    public static final String BAIGIAI_TENBAIGIAI = "tenbaigiai";
    public static final String BAIGIAI_NOIDUNGBG = "noidungbg";

    public static final String TABLE_BAIHOC = "baihoc";
    public static final String BAIHOC_MABAIHOC = "mabaihoc";
    public static final String BAIHOC_MACHUONG = "machuong";
    public static final String BAIHOC_TENBAIHOC = "tenbaihoc";
    public static final String BAIHOC_TOMTATBAIHOC = "tomtatbh";
    public static final String BAIHOC_NOIDUNGBH = "noidungbh";

    public static final String TABLE_BAN = "ban";
    public static final String BAN_MABAN = "maban";
    public static final String BAN_TENBAN = "tenban";
    public static final String BAN_TOMTATBAN = "tomtatban";

    public static final String TABLE_CHUONG = "chuong";
    public static final String CHUONG_MACHUONG = "machuong";
    public static final String CHUONG_MAMONHOC = "mamonhoc";
    public static final String CHUONG_TENCHUONG = "tenchuong";
    public static final String CHUONG_NOIDUNGCHUONG = "noidungchuong";

    public static final String TABLE_CAUHOI = "cauhoi";
    public static final String CAUHOI_MACAUHOI = "macauhoi";
    public static final String CAUHOI_MABAN = "maban";
    public static final String CAUHOI_MAPHAN = "maphan";
    public static final String CAUHOI_MALOAI = "maloai";
    public static final String CAUHOI_TENCAUHOI = "tencauhoi";
    public static final String CAUHOI_NOIDUNGCAU = "noidungcau";
    public static final String CAUHOI_DIEM = "diem";

    public static final String TABLE_CHITIETCAUHOI = "chitietcauhoi";
    public static final String CHITIETCAUHOI_MACAUHOI = "macauhoi";
    public static final String CHITIETCAUHOI_MADE = "made";

    public static final String TABLE_CHITIETHANGSO = "chitiethangso";
    public static final String CHITIETHANGSO_MACONGTHUC = "macongthuc";
    public static final String CHITIETHANGSO_MAHANGSO = "mahangso";

    public static final String TABLE_CHITIETTHAMSO = "chitietthamso";
    public static final String CHITIETTHAMSO_MACONGTHUC = "macongthuc";
    public static final String CHITIETTHAMSO_MATHAMSO = "mathamso";

    public static final String TABLE_CONGTHUC = "congthuc";
    public static final String CONGTHUC_MACONGTHUC = "macongthuc";
    public static final String CONGTHUC_MABAIHOC = "mabaihoc";
    public static final String CONGTHUC_TENCONGTHUC = "tencongthuc";
    public static final String CONGTHUC_NOIDUNGCT = "noidungct";

    public static final String TABLE_DANGDETHI = "dangdethi";
    public static final String DANGDETHI_MADANG = "madang";
    public static final String DANGDETHI_TENDANG = "tendang";
    public static final String DANGDETHI_NOIDUNGDANG = "noidungdang";

    public static final String TABLE_DAPAN = "dapan";
    public static final String DAPAN_MADAPAN = "madapan";
    public static final String DAPAN_MACAUHOI = "macauhoi";
    public static final String DAPAN_TENDAPAN = "tendapan";
    public static final String DAPAN_NOIDUNGDA = "noidungda";
    public static final String DAPAN_DUNG = "dung";

    public static final String TABLE_DETHITHU = "dethithu";
    public static final String DETHITHU_MADETHI = "made";
    public static final String DETHITHU_MADANG = "madang";
    public static final String DETHITHU_MAMON = "mamon";
    public static final String DETHITHU_TENDE = "tende";
    public static final String DETHITHU_SOLUONG = "soluong";

    public static final String TABLE_DIEUKIEN = "dieukien";
    public static final String DIEUKIEN_MADIEUKIEN = "madieukien";
    public static final String DIEUKIEN_MAHEQUA = "mahequa";
    public static final String DIEUKIEN_TENDIEUKIEN = "tendieukien";
    public static final String DIEUKIEN_NOIDUNGDK = "noidungdk";

    public static final String TABLE_DINHLY = "dinhly";
    public static final String DINHLY_MADINHLY = "madinhly";
    public static final String DINHLY_MABAIHOC = "mabaihoc";
    public static final String DINHLY_TENDINHLY = "tendinhly";
    public static final String DINHLY_NOIDUNGDL = "noidungdl";

    public static final String TABLE_DINHNGHIA = "dinhnghia";
    public static final String DINHNGHIA_MADINHNGHIA = "madinhnghia";
    public static final String DINHNGHIA_MABAIHOC = "mabaihoc";
    public static final String DINHNGHIA_TENDINHNGHIA = "tendinhnghia";
    public static final String DINHNGHIA_NOIDUNGDN = "noidungdn";

    public static final String TABLE_HANGSO = "hangso";
    public static final String HANGSO_MAHANGSO = "mahangso";
    public static final String HANGSO_TENHANGSO = "tenhangso";
    public static final String HANGSO_KYHIEU = "kyhieu";
    public static final String HANGSO_GIATRI = "giatri";

    public static final String TABLE_HEQUA = "hequa";
    public static final String HEQUA_MAHEQUA = "mahequa";
    public static final String HEQUA_MADINHLY = "madinhly";
    public static final String HEQUA_TENHEQUA = "tenhequa";
    public static final String HEQUA_NOIDUNGHQ = "noidunghq";

    public static final String TABLE_HINHANH = "hinhanh";
    public static final String HINHANH_MAHINHANH = "mahinhanh";
    public static final String HINHANH_MABAIHOC = "mabaihoc";
    public static final String HINHANH_MACONGTHUC = "macongthuc";
    public static final String HINHANH_MABAIGIAI = "mabaigiai";
    public static final String HINHANH_TENHINHANH = "tenhinhanh";
    public static final String HINHANH_NOIDUNGHA = "noidungha";

    public static final String TABLE_LOAICAUHOI = "loaicauhoi";
    public static final String LOAI_MALOAI = "maloai";
    public static final String LOAI_TENLOAI = "tenloai";
    public static final String LOAI_TOMTATLOAI = "tomtatloai";

    public static final String TABLE_LOP = "lop";
    public static final String LOP_MALOP = "malop";
    public static final String LOP_TENLOP = "tenlop";
    public static final String LOP_TOMTATLOP = "tomtatlop";

    public static final String TABLE_MONHOC = "monhoc";
    public static final String MONHOC_MAMONHOC = "mamonhoc";
    public static final String MONHOC_TENMON = "tenmon";
    public static final String MONHOC_MABAN = "maban";

    public static final String TABLE_NAM = "nam";
    public static final String NAM_MANAM = "manam";
    public static final String NAM_TENNAM = "tennam";

    public static final String TABLE_PHAN = "phan";
    public static final String PHAN_MAPHAN = "maphan";
    public static final String PHAN_TENPHAN = "tenphan";
    public static final String PHAN_TOMTATPHAN = "tomtatphan";

    public static final String TABLE_THAMSO = "thamso";
    public static final String THAMSO_MATHAMSO = "mathamso";
    public static final String THAMSO_TENTHAMSO = "tenthamso";
    public static final String THAMSO_KYHIEU = "kyhieu";
    public static final String THAMSO_MIEUTA = "mieuta";

    public static final String TABLE_VIDU = "vidu";
    public static final String VIDU_MAVIDU = "mavidu";
    public static final String VIDU_MACONGTHUC = "macongthuc";
    public static final String VIDU_TENVIDU = "tenvidu";
    public static final String VIDU_NOIDUNGVD = "noidungvd";
    public static final String VIDU_GIAIVD = "giaivd";

    private static final String CREATE_TABLE_BACHOC = "Create Table "
            + TABLE_BACHOC + "(" + BACHOC_MABACHOC + " Integer Not Null Primary Key Autoincrement, "
            + BACHOC_TENBACHOC + " Text, "
            + BACHOC_TOMTATBH + " Text );";

    private static final String CREATE_TABLE_NAM = "Create Table "
            + TABLE_NAM + "(" + NAM_MANAM + " Integer Not Null Primary Key Autoincrement, "
            + NAM_TENNAM + " Text );";

    private static final String CREATE_TABLE_PHAN = "Create Table "
            + TABLE_PHAN + "(" + PHAN_MAPHAN + " Integer Not Null Primary Key Autoincrement, "
            + PHAN_TENPHAN + " Text, "
            + PHAN_TOMTATPHAN + " Text );";

    private static final String CREATE_TABLE_LOP = "Create Table "
            + TABLE_LOP + "(" + LOP_MALOP + " Integer Not Null Primary Key Autoincrement, "
            + LOP_TENLOP + " Text, "
            + LOP_TOMTATLOP + " Text );";

    private static final String CREATE_TABLE_LOAICAUHOI = "Create Table "
            + TABLE_LOAICAUHOI + "(" + LOAI_MALOAI + " Integer Not Null Primary Key Autoincrement, "
            + LOAI_TENLOAI + " Text, "
            + LOAI_TOMTATLOAI + " Text );";

    private static final String CREATE_TABLE_THAMSO = "Create Table "
            + TABLE_THAMSO + "(" + THAMSO_MATHAMSO + " Integer Not Null Primary Key Autoincrement, "
            + THAMSO_TENTHAMSO + " Text, "
            + THAMSO_KYHIEU + " Text, "
            + THAMSO_MIEUTA + " Text );";

    private static final String CREATE_TABLE_HANGSO = "Create Table "
            + TABLE_HANGSO + "(" + HANGSO_MAHANGSO + " Integer Not Null Primary Key Autoincrement, "
            + HANGSO_TENHANGSO + " Text, "
            + HANGSO_KYHIEU + " Text, "
            + HANGSO_GIATRI + " Real );";

    private static final String CREATE_TABLE_BAN = "Create Table "
            + TABLE_BAN + "(" + BAN_MABAN + " Integer Not Null Primary Key Autoincrement, "
            + BAN_TENBAN + " Text, "
            + BAN_TOMTATBAN + " Text );";

    private static final String CREATE_TABLE_DANGDETHI = "Create Table "
            + TABLE_DANGDETHI + "(" + DANGDETHI_MADANG + " Integer Not Null Primary Key Autoincrement, "
            + DANGDETHI_TENDANG + " Text, "
            + DANGDETHI_NOIDUNGDANG + " Text );";

    private static final String CREATE_TABLE_MONHOC = "Create Table "
            + TABLE_MONHOC + "(" + MONHOC_MAMONHOC + " Integer Not Null Primary Key Autoincrement, "
            + MONHOC_TENMON + " Text, "
            + MONHOC_MABAN +  " Integer Not Null Constraint monhoc_ban References ban(maban) on Delete Cascade);";

    private static final String CREATE_TABLE_CHUONG = "Create Table "
            + TABLE_CHUONG + "(" + CHUONG_MACHUONG + " Integer Not Null Primary Key Autoincrement, "
            + CHUONG_TENCHUONG + " Text, "
            + CHUONG_NOIDUNGCHUONG + " Text, "
            + CHUONG_MAMONHOC +  " Integer Not Null Constraint monhoc_chuong References monhoc(mamonhoc) on Delete Cascade);";

    private static final String CREATE_TABLE_BAIHOC = "Create Table "
            + TABLE_BAIHOC + "(" + BAIHOC_MABAIHOC + " Integer Not Null Primary Key Autoincrement, "
            + BAIHOC_TENBAIHOC + " Text, "
            + BAIHOC_TOMTATBAIHOC + " Text, "
            + BAIHOC_NOIDUNGBH + " Text, "
            + BAIHOC_MACHUONG +  " Integer Not Null Constraint baihoc_chuong References chuong(machuong) on Delete Cascade);";

    private static final String CREATE_TABLE_DINHLY = "Create Table "
            + TABLE_DINHLY + "(" + DINHLY_MADINHLY + " Integer Not Null Primary Key Autoincrement, "
            + DINHLY_TENDINHLY + " Text, "
            + DINHLY_NOIDUNGDL + " Text, "
            + DINHLY_MABAIHOC +  " Integer Not Null Constraint dinhly_baihoc References baihoc(mabaihoc) on Delete Cascade);";

    private static final String CREATE_TABLE_HEQUA = "Create Table "
            + TABLE_HEQUA + "(" + HEQUA_MAHEQUA + " Integer Not Null Primary Key Autoincrement, "
            + HEQUA_TENHEQUA + " Text, "
            + HEQUA_NOIDUNGHQ + " Text, "
            + HEQUA_MADINHLY +  " Integer Not Null Constraint dinhly_hequa References dinhly(madinhly) on Delete Cascade);";

    private static final String CREATE_TABLE_DIEUKIEN = "Create Table "
            + TABLE_DIEUKIEN + "(" + DIEUKIEN_MADIEUKIEN + " Integer Not Null Primary Key Autoincrement, "
            + DIEUKIEN_TENDIEUKIEN + " Text, "
            + DIEUKIEN_NOIDUNGDK + " Text, "
            + DIEUKIEN_MAHEQUA +  " Integer Not Null Constraint dieukien_hequa References hequa(mahequa) on Delete Cascade);";

    private static final String CREATE_TABLE_CONGTHUC = "Create Table "
            + TABLE_CONGTHUC + "(" + CONGTHUC_MACONGTHUC + " Integer Not Null Primary Key Autoincrement, "
            + CONGTHUC_TENCONGTHUC + " Text, "
            + CONGTHUC_NOIDUNGCT + " Text, "
            + CONGTHUC_MABAIHOC +  " Integer Not Null Constraint baihoc_hequa References baihoc(mabaihoc) on Delete Cascade);";

    private static final String CREATE_TABLE_CHITIETHANGSO= "Create Table "
            + TABLE_CHITIETHANGSO + "(" + CHITIETHANGSO_MAHANGSO + " Integer Not Null Primary Key , "
            + CHITIETHANGSO_MACONGTHUC  + " Integer Not Null Primary Key , "
            + CHITIETHANGSO_MACONGTHUC +  " Constraint hangso_congthuc References congthuc(macongthuc) on Delete Cascade),"
            + CHITIETHANGSO_MAHANGSO + " Constraint hangso_chitiet References hangso(mahangso) on Delete Cascade);";

    private static final String CREATE_TABLE_CHITIETTHAMSO= "Create Table "
            + TABLE_CHITIETTHAMSO + "(" + CHITIETTHAMSO_MATHAMSO + " Integer Not Null Primary Key , "
            + CHITIETTHAMSO_MACONGTHUC + " Integer Not Null Primary Key , "
            + CHITIETTHAMSO_MACONGTHUC +  " Constraint thamso_congthuc References congthuc(macongthuc) on Delete Cascade),"
            + CHITIETTHAMSO_MATHAMSO + " Constraint thamso_chitiet References thamso(mathamso) on Delete Cascade);";

    private static final String CREATE_TABLE_DINHNGHIA = "Create Table "
            + TABLE_DINHNGHIA + "(" + DINHNGHIA_MADINHNGHIA + " Integer Not Null Primary Key Autoincrement, "
            + DINHNGHIA_TENDINHNGHIA + " Text, "
            + DINHNGHIA_NOIDUNGDN + " Text, "
            + DINHNGHIA_MABAIHOC +  " Integer Not Null Constraint dinhnghia_baihoc References baihoc(mabaihoc) on Delete Cascade);";

    private static final String CREATE_TABLE_VIDU = "Create Table "
            + TABLE_VIDU + "(" + VIDU_MAVIDU + " Integer Not Null Primary Key Autoincrement, "
            + VIDU_TENVIDU + " Text, "
            + VIDU_NOIDUNGVD + " Text, "
            + VIDU_GIAIVD + " Text, "
            + VIDU_MACONGTHUC +  " Integer Not Null Constraint vidu_congthuc References congthuc(macongthuc) on Delete Cascade);";


    private static final String CREATE_TABLE_DETHITHU = "Create Table "
            + TABLE_DETHITHU + "(" + DETHITHU_MADETHI + " Integer Not Null Primary Key Autoincrement, "
            + DETHITHU_TENDE + " Text, "
            + DETHITHU_SOLUONG + " Integer, "
            + DETHITHU_MADANG + " Integer Not Null Constraint dang_dethi References dangdethi(madang) on Delete Cascade, "
            + DETHITHU_MAMON +  " Integer Not Null Constraint mon_dethi References monhoc(mamonhoc) on Delete Cascade);";

    private static final String CREATE_TABLE_CAUHOI = "Create Table "
            + TABLE_CAUHOI + "(" + CAUHOI_MACAUHOI + " Integer Not Null Primary Key Autoincrement, "
            + CAUHOI_TENCAUHOI + " Text, "
            + CAUHOI_NOIDUNGCAU + " Integer, "
            + CAUHOI_DIEM + " real, "
            + CAUHOI_MABAN + " Integer Not Null Constraint cauhoi_ban References ban(maban) on Delete Cascade, "
            + CAUHOI_MALOAI + " Integer Not Null Constraint cauhoi_loai References loaicauhoi(maloai) on Delete Cascade, "
            + CAUHOI_MAPHAN +  " Integer Not Null Constraint cauhoi_phan References phan(maphan) on Delete Cascade);";

    private static final String CREATE_TABLE_CHITIETCAUHOI = "Create Table "
            + TABLE_CHITIETCAUHOI + "(" + CHITIETCAUHOI_MACAUHOI + " Integer Not Null Primary Key , "
            + CHITIETCAUHOI_MADE + " Integer Not Null Primary Key , "
            + CHITIETCAUHOI_MADE +  " Constraint de_chitiet References dethithu(made) on Delete Cascade),"
            + CHITIETCAUHOI_MACAUHOI + " Constraint cauhoi_chitiet References cauhoi(macauhoi) on Delete Cascade);";

    private static final String CREATE_TABLE_DAPAN = "Create Table "
            + TABLE_DAPAN + "(" + DAPAN_MADAPAN + " Integer Not Null Primary Key Autoincrement, "
            + DAPAN_TENDAPAN + " Text, "
            + DAPAN_NOIDUNGDA + " Text, "
            + DAPAN_DUNG + " blob, "
            + DAPAN_MACAUHOI +  " Integer Not Null Constraint dapan_cauhoi References cauhoi(macauhoi) on Delete Cascade);";

    private static final String CREATE_TABLE_BAIGIAI = "Create Table "
            + TABLE_BAIGIAI + "(" + BAIGIAI_MABAIGIAI + " Integer Not Null Primary Key Autoincrement, "
            + BAIGIAI_TENBAIGIAI + " Text, "
            + BAIGIAI_NOIDUNGBG + " Text, "
            + BAIGIAI_MACAUHOI +  " Integer Not Null Constraint baigiai_cauhoi References cauhoi(macauhoi) on Delete Cascade);";

    private static final String CREATE_TABLE_HINHANH = "Create Table "
            + TABLE_HINHANH + "(" + HINHANH_MAHINHANH + " Integer Not Null Primary Key Autoincrement, "
            + HINHANH_TENHINHANH + " Text, "
            + HINHANH_NOIDUNGHA + " Text, "
            + HINHANH_MABAIGIAI +  " Integer Not Null Constraint hinhanh_baigiai References baigiai(mabaigiai) on Delete Cascade),"
            + HINHANH_MABAIHOC +  " Integer Not Null Constraint hinhanh_baihoc References baihoc(mabaihoc) on Delete Cascade),"
            + HINHANH_MACONGTHUC +  " Integer Not Null Constraint hinhanh_congthuc References congthuc(macongthuc) on Delete Cascade);";*/

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        /*db.execSQL(CREATE_TABLE_BACHOC);
        db.execSQL(CREATE_TABLE_NAM);
        db.execSQL(CREATE_TABLE_PHAN);

        db.execSQL(CREATE_TABLE_LOP);
        db.execSQL(CREATE_TABLE_LOAICAUHOI);
        db.execSQL(CREATE_TABLE_THAMSO);

        db.execSQL(CREATE_TABLE_HANGSO);
        db.execSQL(CREATE_TABLE_BAN);
        db.execSQL(CREATE_TABLE_DANGDETHI);

        db.execSQL(CREATE_TABLE_MONHOC);
        db.execSQL(CREATE_TABLE_CHUONG);
        db.execSQL(CREATE_TABLE_BAIHOC);

        db.execSQL(CREATE_TABLE_DINHLY);
        db.execSQL(CREATE_TABLE_HEQUA);
        db.execSQL(CREATE_TABLE_DIEUKIEN);

        db.execSQL(CREATE_TABLE_CONGTHUC);
        db.execSQL(CREATE_TABLE_CHITIETHANGSO);
        db.execSQL(CREATE_TABLE_CHITIETTHAMSO);

        db.execSQL(CREATE_TABLE_DINHNGHIA);
        db.execSQL(CREATE_TABLE_VIDU);
        db.execSQL(CREATE_TABLE_DETHITHU);

        db.execSQL(CREATE_TABLE_CAUHOI);
        db.execSQL(CREATE_TABLE_CHITIETCAUHOI);
        db.execSQL(CREATE_TABLE_BAIGIAI);

        db.execSQL(CREATE_TABLE_DAPAN);
        db.execSQL(CREATE_TABLE_HINHANH);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
       /* db.execSQL("Drop Table If Exists " + TABLE_BACHOC);
        db.execSQL("Drop Table If Exists " + TABLE_NAM);
        db.execSQL("Drop Table If Exists " + TABLE_PHAN);

        db.execSQL("Drop Table If Exists " + TABLE_LOAICAUHOI);
        db.execSQL("Drop Table If Exists " + TABLE_LOP);
        db.execSQL("Drop Table If Exists " + TABLE_THAMSO);

        db.execSQL("Drop Table If Exists " + TABLE_HANGSO);
        db.execSQL("Drop Table If Exists " + TABLE_BAN);
        db.execSQL("Drop Table If Exists " + TABLE_DANGDETHI);

        db.execSQL("Drop Table If Exists " + TABLE_MONHOC);
        db.execSQL("Drop Table If Exists " + TABLE_CHUONG);
        db.execSQL("Drop Table If Exists " + TABLE_BAIHOC);

        db.execSQL("Drop Table If Exists " + TABLE_DINHLY);
        db.execSQL("Drop Table If Exists " + TABLE_HEQUA);
        db.execSQL("Drop Table If Exists " + TABLE_DIEUKIEN);

        db.execSQL("Drop Table If Exists " + TABLE_CONGTHUC);
        db.execSQL("Drop Table If Exists " + TABLE_CHITIETHANGSO);
        db.execSQL("Drop Table If Exists " + TABLE_CHITIETTHAMSO);

        db.execSQL("Drop Table If Exists " + TABLE_DINHNGHIA);
        db.execSQL("Drop Table If Exists " + TABLE_VIDU);
        db.execSQL("Drop Table If Exists " + TABLE_DETHITHU);

        db.execSQL("Drop Table If Exists " + TABLE_CAUHOI);
        db.execSQL("Drop Table If Exists " + TABLE_CHITIETCAUHOI);
        db.execSQL("Drop Table If Exists " + TABLE_BAIGIAI);

        db.execSQL("Drop Table If Exists " + TABLE_DAPAN);
        db.execSQL("Drop Table If Exists " + TABLE_HINHANH);*/
    }

    @Override
    public void onOpen(SQLiteDatabase db)
    {
        super.onOpen(db);
    }
}

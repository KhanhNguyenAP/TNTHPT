package com.thud.thpt_dh.datas;

import android.content.ContentValues;
import android.database.Cursor;
import android.view.animation.DecelerateInterpolator;

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
import com.thud.thpt_dh.model.LinhVuc;
import com.thud.thpt_dh.model.MonHoc;
import com.thud.thpt_dh.utils.interfaces.Flags;

/**
 * Created by khanh on 5/12/2016.
 */
public class DbModel {

    /*
    * @author: KhanhNguyen
    * @function: getContentValueBaiGiai
    * */
    public static ContentValues getContentValueBaiGiai(BaiGiai baiGiai){
        ContentValues baigiaiDb = new ContentValues();
        baigiaiDb.put(BaiGiai.ID, baiGiai.getId());
        baigiaiDb.put(BaiGiai.TENBAIGIAI, baiGiai.getTenbaigiai());
        baigiaiDb.put(BaiGiai.NOIDUNGBG, baiGiai.getNoidungbg());

        return baigiaiDb;
    }

    public static BaiGiai getBaiGiai(Cursor cursor){
        BaiGiai baiGiai = new BaiGiai();
        baiGiai.setId(cursor.getString(cursor.getColumnIndex(BaiGiai.ID)));
        baiGiai.setTenbaigiai(cursor.getString(cursor.getColumnIndex(BaiGiai.TENBAIGIAI)));
        baiGiai.setNoidungbg(cursor.getString(cursor.getColumnIndex(BaiGiai.NOIDUNGBG)));

        return baiGiai;
    }

    /*
    * @author: KhanhNguyen
    * @function: getContentValueBaiHoc
    * */
    public static ContentValues getContentValueBaiHoc(BaiHoc baiHoc){
        ContentValues baiHocDb = new ContentValues();
        baiHocDb.put(BaiHoc.ID, baiHoc.getId());
        baiHocDb.put(BaiHoc.MACHUONG, baiHoc.getMachuong());
        baiHocDb.put(BaiHoc.TENBH, baiHoc.getTenbh());

        return baiHocDb;
    }

    public static BaiHoc getBaiHoc(Cursor cursor){
        BaiHoc baiHoc = new BaiHoc();
        baiHoc.setId(cursor.getString(cursor.getColumnIndex(BaiHoc.ID)));
        baiHoc.setMachuong(cursor.getString(cursor.getColumnIndex(BaiHoc.MACHUONG)));
        baiHoc.setTenbh(cursor.getString(cursor.getColumnIndex(BaiHoc.TENBH)));

        return baiHoc;
    }

    /*
    * @author: KhanhNguyen
    * @function: getContentValueCauHoi
    * */
    public static ContentValues getContentValueCauHoi(CauHoi cauHoi){
        ContentValues cauhoiDb = new ContentValues();
        cauhoiDb.put(CauHoi.ID, cauHoi.getId());
        cauhoiDb.put(CauHoi.MADETHI, cauHoi.getMadethi());
        cauhoiDb.put(CauHoi.LOAI, cauHoi.getLoai());
        cauhoiDb.put(CauHoi.NOIDUNGCH, cauHoi.getNoidungch());

        return cauhoiDb;
    }

    public static CauHoi getCauHoi(Cursor cursor){
        CauHoi cauHoi = new CauHoi();
        cauHoi.setId(cursor.getString(cursor.getColumnIndex(CauHoi.ID)));
        cauHoi.setMadethi(cursor.getString(cursor.getColumnIndex(CauHoi.MADETHI)));
        cauHoi.setLoai(cursor.getInt(cursor.getColumnIndex(CauHoi.LOAI)));
        cauHoi.setNoidungch(cursor.getString(cursor.getColumnIndex(CauHoi.NOIDUNGCH)));

        return cauHoi;
    }

    /*
    * @author: KhanhNguyen
    * @function: getContentValueChiTietBaiHoc
    * */
    public static ContentValues getContentValueChiTietBaiHoc(ChiTietBaiHoc CTBH){
        ContentValues CTBHDb = new ContentValues();
        CTBHDb.put(ChiTietBaiHoc.ID, CTBH.getId());
        CTBHDb.put(ChiTietBaiHoc.MABAIHOC, CTBH.getMabaihoc());
        CTBHDb.put(ChiTietBaiHoc.TENCTBH, CTBH.getTenctbh());
        CTBHDb.put(ChiTietBaiHoc.NOIDUNGCTBH, CTBH.getNoidungctbh());
        CTBHDb.put(ChiTietBaiHoc.NOIDUNGCT, CTBH.getNoidungct());

        return CTBHDb;
    }

    public static ChiTietBaiHoc getChiTietBaiHoc(Cursor cursor){
        ChiTietBaiHoc chiTietBaiHoc = new ChiTietBaiHoc();
        chiTietBaiHoc.setId(cursor.getString(cursor.getColumnIndex(ChiTietBaiHoc.ID)));
        chiTietBaiHoc.setMabaihoc(cursor.getString(cursor.getColumnIndex(ChiTietBaiHoc.MABAIHOC)));
        chiTietBaiHoc.setNoidungct(cursor.getString(cursor.getColumnIndex(ChiTietBaiHoc.NOIDUNGCT)));
        chiTietBaiHoc.setTenctbh(cursor.getString(cursor.getColumnIndex(ChiTietBaiHoc.TENCTBH)));
        chiTietBaiHoc.setNoidungctbh(cursor.getString(cursor.getColumnIndex(ChiTietBaiHoc.NOIDUNGCTBH)));

        return chiTietBaiHoc;
    }

    /*
    * @author: KhanhNguyen
    * @function: getContentValueChuong
    * */
    public static ContentValues getContentValueChuong(Chuong chuong){
        ContentValues ChuongDb = new ContentValues();
        ChuongDb.put(Chuong.ID, chuong.getId());
        ChuongDb.put(Chuong.MAMON, chuong.getMamon());
        ChuongDb.put(Chuong.TENCHUONG, chuong.getTenchuong());
        ChuongDb.put(Chuong.MALINHVUC, chuong.getMalinhvuc());

        return ChuongDb;
    }

    public static Chuong getChuong(Cursor cursor){
        Chuong chuong = new Chuong();
        chuong.setId(cursor.getString(cursor.getColumnIndex(Chuong.ID)));
        chuong.setMamon(cursor.getString(cursor.getColumnIndex(Chuong.MAMON)));
        chuong.setTenchuong(cursor.getString(cursor.getColumnIndex(Chuong.TENCHUONG)));
        chuong.setMalinhvuc(cursor.getString(cursor.getColumnIndex(Chuong.MALINHVUC)));

        return chuong;
    }

    /*
    * @author: KhanhNguyen
    * @function: getContentValueCongThuc
    * */
    public static ContentValues getContentValueCongThuc(CongThuc congThuc){
        ContentValues CongThucDb = new ContentValues();
        CongThucDb.put(CongThuc.ID, congThuc.getId());
        CongThucDb.put(CongThuc.MACTBH, congThuc.getMactbh());
        CongThucDb.put(CongThuc.TENCT, congThuc.getTenct());
        CongThucDb.put(CongThuc.NOIDUNGCT, congThuc.getNoidungct());

        return CongThucDb;
    }

    public static CongThuc getCongThuc(Cursor cursor){
        CongThuc congThuc = new CongThuc();
        congThuc.setId(cursor.getString(cursor.getColumnIndex(CongThuc.ID)));
        congThuc.setMactbh(cursor.getString(cursor.getColumnIndex(CongThuc.MACTBH)));
        congThuc.setTenct(cursor.getString(cursor.getColumnIndex(CongThuc.TENCT)));
        congThuc.setNoidungct(cursor.getString(cursor.getColumnIndex(CongThuc.NOIDUNGCT)));

        return congThuc;
    }

    /*
    * @author: KhanhNguyen
    * @function: getContentValueDapAn
    * */
    public static ContentValues getContentValueDapAn(DapAn dapAn){
        ContentValues DapAnDb = new ContentValues();
        DapAnDb.put(DapAn.ID, dapAn.getId());
        DapAnDb.put(DapAn.DUNGSAI, dapAn.getDung());
        DapAnDb.put(DapAn.MACAUHOI, dapAn.getMacauhoi());
        DapAnDb.put(DapAn.NOIDUNGDA, dapAn.getNoidungda());

        return DapAnDb;
    }

    public static DapAn getDapAn(Cursor cursor){
        DapAn dapAn = new DapAn();
        dapAn.setId(cursor.getString(cursor.getColumnIndex(DapAn.ID)));
        dapAn.setDung(cursor.getInt(cursor.getColumnIndex(DapAn.DUNGSAI)));
        dapAn.setMacauhoi(cursor.getString(cursor.getColumnIndex(DapAn.MACAUHOI)));
        dapAn.setNoidungda(cursor.getString(cursor.getColumnIndex(DapAn.NOIDUNGDA)));

        return dapAn;
    }

    /*
     * @author: KhanhNguyen
     * @function: getContentValueDeThiThu
     * */
    public static ContentValues getContentValueDeThiThu(DeThiThu deThiThu){
        ContentValues DeThiThuDb = new ContentValues();
        DeThiThuDb.put(DeThiThu.ID, deThiThu.getId());
        DeThiThuDb.put(DeThiThu.MAMONHOC, deThiThu.getManonhoc());
        DeThiThuDb.put(DeThiThu.SOLUONG, deThiThu.getSoluong());
        DeThiThuDb.put(DeThiThu.TENDE, deThiThu.getTende());
        DeThiThuDb.put(DeThiThu.LOAI, deThiThu.getLoai());

        return DeThiThuDb;
    }

    public static DeThiThu getDeThiThu(Cursor cursor){
        DeThiThu deThiThu = new DeThiThu();
        deThiThu.setId(cursor.getString(cursor.getColumnIndex(DeThiThu.ID)));
        deThiThu.setManonhoc(cursor.getString(cursor.getColumnIndex(DeThiThu.MAMONHOC)));
        deThiThu.setSoluong(cursor.getInt(cursor.getColumnIndex(DeThiThu.SOLUONG)));
        deThiThu.setTende(cursor.getString(cursor.getColumnIndex(DeThiThu.TENDE)));
        deThiThu.setLoai(cursor.getInt(cursor.getColumnIndex(DeThiThu.LOAI)));

        return deThiThu;
    }

    /*
    * @author: KhanhNguyen
    * @function: getContentValueDieuKien
    * */
    public static ContentValues getContentValueDieuKien(DieuKien dieuKien){
        ContentValues DieuKienDb = new ContentValues();
        DieuKienDb.put(DieuKien.ID, dieuKien.getId());
        DieuKienDb.put(DieuKien.TENDK, dieuKien.getTendk());
        DieuKienDb.put(DieuKien.NOIDUNGDK, dieuKien.getNoidungdk());

        return DieuKienDb;
    }

    public static DieuKien getDieuKien(Cursor cursor){
        DieuKien dieuKien = new DieuKien();
        dieuKien.setId(cursor.getString(cursor.getColumnIndex(DieuKien.ID)));
        dieuKien.setNoidungdk(cursor.getString(cursor.getColumnIndex(DieuKien.NOIDUNGDK)));
        dieuKien.setTemdk(cursor.getString(cursor.getColumnIndex(DieuKien.TENDK)));

        return dieuKien;
    }


    /*
    * @author: KhanhNguyen
    * @function: getContentValueDinhLy
    * */
    public static ContentValues getContentValueDinhLy(DinhLy dinhLy){
        ContentValues DinhLyDb = new ContentValues();
        DinhLyDb.put(DinhLy.ID, dinhLy.getId());
        DinhLyDb.put(DinhLy.MABAIHOC, dinhLy.getMabaihoc());
        DinhLyDb.put(DinhLy.TENDL, dinhLy.getTendl());
        DinhLyDb.put(DinhLy.NOIDUNGDL, dinhLy.getNoidungdl());

        return DinhLyDb;
    }

    public static DinhLy getDinhLy(Cursor cursor){
        DinhLy dinhLy = new DinhLy();
        dinhLy.setId(cursor.getString(cursor.getColumnIndex(DinhLy.ID)));
        dinhLy.setMabaihoc(cursor.getString(cursor.getColumnIndex(DinhLy.MABAIHOC)));
        dinhLy.setTendl(cursor.getString(cursor.getColumnIndex(DinhLy.TENDL)));
        dinhLy.setNoidungdl(cursor.getString(cursor.getColumnIndex(DinhLy.NOIDUNGDL)));

        return dinhLy;
    }

    /*
    * @author: KhanhNguyen
    * @function: getContentValueMonHoc
    * */
    public static ContentValues getContentValueMonHoc(MonHoc monHoc){
        ContentValues MonHocDb = new ContentValues();
        MonHocDb.put(MonHoc.ID, monHoc.getId());
        MonHocDb.put(MonHoc.TENMON, monHoc.getTenmon());
        MonHocDb.put(MonHoc.MAMON, monHoc.getMamon());

        return MonHocDb;
    }

    public static MonHoc getMonHoc(Cursor cursor){
        MonHoc monHoc = new MonHoc();
        monHoc.setId(cursor.getString(cursor.getColumnIndex(MonHoc.ID)));
        monHoc.setTenmon(cursor.getString(cursor.getColumnIndex(MonHoc.TENMON)));
        monHoc.setMamon(cursor.getInt(cursor.getColumnIndex(MonHoc.MAMON)));

        return monHoc;
    }


    /*
    * @author: KhanhNguyen
    * @function: getContentValueHinhAnh
    * */
    public static ContentValues getContentValueHinhAnh(HinhAnh hinhAnh){
        ContentValues HinhAnhDb = new ContentValues();
        HinhAnhDb.put(HinhAnh.ID, hinhAnh.getId());
        HinhAnhDb.put(HinhAnh.HINHANH, String.valueOf(hinhAnh.getHinhanh().getUrl()));
        HinhAnhDb.put(HinhAnh.MABAIGIAI, hinhAnh.getMabaigiai());
        HinhAnhDb.put(HinhAnh.MACHITIETBAIHOC, hinhAnh.getMactbg());

        return HinhAnhDb;
    }

    public static HinhAnh getHinhAnh(Cursor cursor){
        HinhAnh hinhAnh = new HinhAnh();
        hinhAnh.setId(cursor.getString(cursor.getColumnIndex(HinhAnh.ID)));
        hinhAnh.setImg(cursor.getString(cursor.getColumnIndex(HinhAnh.HINHANH)));
        hinhAnh.setMabaigiai(cursor.getString(cursor.getColumnIndex(HinhAnh.MABAIGIAI)));
        hinhAnh.setMactbg(cursor.getString(cursor.getColumnIndex(HinhAnh.MACHITIETBAIHOC)));

        return hinhAnh;
    }

    /*
    * @author: KhanhNguyen
    * @function: getContentValueLinhVuc
    * */
    public static ContentValues getContentValueLinhVuc(LinhVuc linhVuc){
        ContentValues linhvucDb = new ContentValues();
        linhvucDb.put(LinhVuc.ID, linhVuc.getId());
        linhvucDb.put(LinhVuc.MAMON, linhVuc.getMamon());
        linhvucDb.put(LinhVuc.TENLINHVUC, linhVuc.getTenlinhvuc());

        return linhvucDb;
    }

    public static LinhVuc getLinhVuc(Cursor cursor){
        LinhVuc linhVuc = new LinhVuc();
        linhVuc.setId(cursor.getString(cursor.getColumnIndex(LinhVuc.ID)));
        linhVuc.setMamon(cursor.getString(cursor.getColumnIndex(LinhVuc.MAMON)));
        linhVuc.setTenlinhvuc(cursor.getString(cursor.getColumnIndex(LinhVuc.TENLINHVUC)));

        return linhVuc;
    }

}

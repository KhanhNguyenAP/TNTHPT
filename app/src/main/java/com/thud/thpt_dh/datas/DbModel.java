package com.thud.thpt_dh.datas;

import android.content.ContentValues;

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

        return CTBHDb;
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

        return ChuongDb;
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

        return DeThiThuDb;
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

    /*
    * @author: KhanhNguyen
    * @function: getContentValueMonHoc
    * */
    public static ContentValues getContentValueMonHoc(MonHoc monHoc){
        ContentValues MonHocDb = new ContentValues();
        MonHocDb.put(MonHoc.ID, monHoc.getId());
        MonHocDb.put(MonHoc.TENMON, monHoc.getTenmon());

        return MonHocDb;
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
}

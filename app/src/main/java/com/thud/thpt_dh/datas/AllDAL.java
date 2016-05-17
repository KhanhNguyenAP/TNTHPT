package com.thud.thpt_dh.datas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.thud.thpt_dh.datas.BaiGiaiDAL;
import com.thud.thpt_dh.datas.BaiHocDAL;
import com.thud.thpt_dh.datas.CauHoiDAL;
import com.thud.thpt_dh.datas.ChiTietBaiHocDAL;
import com.thud.thpt_dh.datas.ChuongDAL;
import com.thud.thpt_dh.datas.CongThucDAL;
import com.thud.thpt_dh.datas.DBHelper;
import com.thud.thpt_dh.datas.DapAnDAL;
import com.thud.thpt_dh.datas.DeThiThuDAL;
import com.thud.thpt_dh.datas.DieuKienDAL;
import com.thud.thpt_dh.datas.DinhLyDAL;
import com.thud.thpt_dh.datas.HinhAnhDAL;
import com.thud.thpt_dh.datas.MonHocDAL;
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
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.Def;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by khanh on 5/11/2016.
 */
public class AllDAL {
    private Context context;

    /*sqlite*/
    private DBHelper db_helper;
    private SQLiteDatabase database;
    public static String path_document = Def.STRING_EMPTY;

    public AllDAL(Context current){
        this.context = current;
        db_helper = new DBHelper(context);
        //database = db_helper.getWritableDatabase();
    }

    public Result<String> saveAll(Object... object) {
        try {
            Result<String> result = new Result<String>(ResultStatus.FALSE, null, Def.STRING_EMPTY);

            //with baigiai
            if (((ArrayList) object[0]).size() >0){
                Object firstObject = ((ArrayList) object[0]).get(0);
                //with Bai Giai
                if (firstObject != null && firstObject instanceof BaiGiai){
                    ArrayList<BaiGiai> baiGiais = ((ArrayList) object[0]);
                    result = new BaiGiaiDAL(context).insertBaiGiaiFromLocal(baiGiais);
                }
                else{
                    if (firstObject != null && firstObject instanceof BaiHoc){
                        ArrayList<BaiHoc> baiHocs = ((ArrayList) object[0]);
                        result = new BaiHocDAL(context).insertBaiHocFromLocal(baiHocs);
                    }
                    else{
                        if (firstObject != null && firstObject instanceof CauHoi){
                            ArrayList<CauHoi> cauHois = ((ArrayList) object[0]);
                            result = new CauHoiDAL(context).insertCauHoiFromLocal(cauHois);
                        }
                        else{
                            if (firstObject != null && firstObject instanceof ChiTietBaiHoc){
                                ArrayList<ChiTietBaiHoc> chiTietBaiHocs = ((ArrayList) object[0]);
                                result = new ChiTietBaiHocDAL(context).insertChiTietBaiHocFromLocal(chiTietBaiHocs);
                            }
                            else {
                                if (firstObject != null && firstObject instanceof Chuong){
                                    ArrayList<Chuong> chuongs = ((ArrayList) object[0]);
                                    result = new ChuongDAL(context).insertChuongFromLocal(chuongs);
                                }
                                else {
                                    if (firstObject != null && firstObject instanceof CongThuc){
                                        ArrayList<CongThuc> congThucs = ((ArrayList) object[0]);
                                        result = new CongThucDAL(context).insertCongThucFromLocal(congThucs);
                                    }
                                    else {
                                        if (firstObject != null && firstObject instanceof DapAn){
                                            ArrayList<DapAn> dapAns = ((ArrayList) object[0]);
                                            result = new DapAnDAL(context).insertDapAnFromLocal(dapAns);
                                        }
                                        else{
                                            if(firstObject != null && firstObject instanceof DeThiThu){
                                                ArrayList<DeThiThu> deThiThus = ((ArrayList) object[0]);
                                                result = new DeThiThuDAL(context).insertDeThiThuFromLocal(deThiThus);
                                            }
                                            else {
                                                if (firstObject != null && firstObject instanceof DieuKien){
                                                    ArrayList<DieuKien> dieuKiens = ((ArrayList) object[0]);
                                                    result = new DieuKienDAL(context).insertDieuKienFromLocal(dieuKiens);
                                                }
                                                else {
                                                    if (firstObject != null && firstObject instanceof DinhLy){
                                                        ArrayList<DinhLy> dinhLies = ((ArrayList) object[0]);
                                                        result = new DinhLyDAL(context).insertDinhLyFromLocal(dinhLies);
                                                    }
                                                    else {
                                                        if (firstObject != null && firstObject instanceof HinhAnh){
                                                            ArrayList<HinhAnh> hinhAnhs = ((ArrayList) object[0]);
                                                            result = new HinhAnhDAL(context).insertHinhAnhFromLocal(hinhAnhs);
                                                        }
                                                        else {
                                                            ArrayList<MonHoc> monHocs = ((ArrayList) object[0]);
                                                            result = new MonHocDAL(context).insertMonHocFromLocal(monHocs);
                                                        }
                                                    }//end Dinh Ly
                                                }//end Dieu Kien
                                            }//end De Thi Thu
                                        }//end Dap An
                                    }//end Cong Thuc
                                }//end Chuong
                            }//end Chi Tiet Bai Hoc
                        }//end Cau Hoi
                    }//end Bai Hoc
                }//end Bai Giai
            }
            return result;
        }
        catch (SQLiteException e){
            e.printStackTrace();
            Log.e(Def.ERROR, e.getMessage());
            return new Result<String>(ResultStatus.FALSE, null);
        }
    }

    public boolean dropAllTable(){
        try{
            db_helper.DropAllTable(database);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

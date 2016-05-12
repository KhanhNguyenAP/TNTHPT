package com.thud.thpt_dh.utils.async;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.assist.AssistStructure;
import android.content.Context;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.thud.thpt_dh.R;
import com.thud.thpt_dh.datas.BaiGiaiDAL;
import com.thud.thpt_dh.datas.BaiHocDAL;
import com.thud.thpt_dh.datas.CauHoiDAL;
import com.thud.thpt_dh.datas.ChiTietBaiHocDAL;
import com.thud.thpt_dh.datas.ChuongDAL;
import com.thud.thpt_dh.datas.CongThucDAL;
import com.thud.thpt_dh.datas.DapAnDAL;
import com.thud.thpt_dh.datas.DeThiThuDAL;
import com.thud.thpt_dh.datas.DieuKienDAL;
import com.thud.thpt_dh.datas.DinhLyDAL;
import com.thud.thpt_dh.datas.HinhAnhDAL;
import com.thud.thpt_dh.datas.MonHocDAL;
import com.thud.thpt_dh.model.AllDAL;
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
import com.thud.thpt_dh.utils.Preference;
import com.thud.thpt_dh.utils.dialogs.ToastMessage;
import com.thud.thpt_dh.utils.interfaces.ActivityInterface;
import com.thud.thpt_dh.utils.interfaces.Def;
import com.thud.thpt_dh.utils.interfaces.Flags;

import java.util.ArrayList;

/**
 * Created by khanh on 5/11/2016.
 */
public class SaveAllDataFromSerVer extends AsyncTask<Void, Integer, Result<String>> implements ActivityInterface{
    private AlertDialog alertDialog;
    private AlertDialog.Builder alertDialogBuilder;
    private View view;
    private TextView text_view_percent, text_view_title;
    private ProgressBar progress_custom;
    private Context context;
    private PowerManager.WakeLock mWakerLock;
    private Preference preference;
    private  int countDataComplete = 0;

    public SaveAllDataFromSerVer(Context current){
        this.context = current;
        view = ((Activity) context).getLayoutInflater().inflate(R.layout.custom_progressbar_percent, null);

        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setCancelable(false);

        initControl();
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        /*mWakerLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());
        mWakerLock.acquire();*/

        //create an alert dialog
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }


    @Override
    protected Result<String> doInBackground(Void... params) {
        try {
            if (Flags.synch_data == 0){
                new AllDAL(context).dropAllTable();
            }
            else {
                return new Result<String>(ResultStatus.FALSE, null, context.getResources().getString(R.string.msg_can_not_connect_to_network));
            }

            getBaiGiai();
            getBaiHoc();
            publishProgress(1);
            getCauHoi();
            publishProgress(2);
            getChiTietBaiHoc();
            getChuong();
            publishProgress(3);
            getCongThuc();
            publishProgress(4);
            getDapAn();
            publishProgress(5);
            getDeThiThu();
            publishProgress(6);
            getDieuKien();
            publishProgress(7);
            getDinhLy();
            publishProgress(8);
            getHinhAnh();
            publishProgress(9);
            getMonHoc();
            return new Result<String>(ResultStatus.TRUE, null);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new Result<String>(ResultStatus.FALSE, null, context.getResources().getString(R.string.msg_can_not_connect_to_network));
    }

    @Override
    protected void onProgressUpdate(Integer... progress){
        super.onProgressUpdate(progress);
        setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(Result<String> result){
        super.onPostExecute(result);
        alertDialog.dismiss();

        if(result.getKey() == ResultStatus.FALSE){
            new ToastMessage(context).showToast(result.getMessage());
        }
        else {
            new ToastMessage(context).showToast(context.getResources().getString(R.string.msg_download_data));
            //Flags.synch_data = 1;
        }
    }

    @Override
    public void initFlags() {

    }

    @Override
    public void initControl() {
        text_view_title = (TextView)view.findViewById(R.id.text_view_title);
        text_view_percent = (TextView)view.findViewById(R.id.text_view_percent);
        progress_custom = (ProgressBar)view.findViewById(R.id.progress_custom);
    }

    @Override
    public void setEventForControl() {

    }

    @Override
    public void getData(String... params) {

    }

    @Override
    public void setData() {

    }

    private void setProgress(int progressNumber){
        progress_custom.setProgress(progressNumber);
        text_view_percent.setText(progressNumber*10+"%");
    }

    //get BaiGiai
    private boolean getBaiGiai(){
        try {
            Result<ArrayList<BaiGiai>> resultBaiGiai = new BaiGiaiDAL(context).getAllBaiGiai();
            if (resultBaiGiai.getKey() == ResultStatus.TRUE &&
                    resultBaiGiai.getValue() != null &&
                    resultBaiGiai.getValue().size() > 0){
                ArrayList<BaiGiai> baiGiais = resultBaiGiai.getValue();

                Result<String> result = new AllDAL(context).saveAll(baiGiais);
                Log.i(Def.INFO, result.getMessage());

            }
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    //get BaiHoc
    private boolean getBaiHoc(){
        try {
            Result<ArrayList<BaiHoc>> resultBaiHoc = new BaiHocDAL(context).getAllBaiHoc();
            if (resultBaiHoc.getKey() == ResultStatus.TRUE &&
                    resultBaiHoc.getValue() != null &&
                    resultBaiHoc.getValue().size() > 0){
                ArrayList<BaiHoc> baiHocs = resultBaiHoc.getValue();

                Result<String> result = new AllDAL(context).saveAll(baiHocs);
                Log.i(Def.INFO, result.getMessage());

            }
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //get CauHoi
    private boolean getCauHoi(){
        try {
            Result<ArrayList<CauHoi>> resultCauHoi = new CauHoiDAL(context).getAllCauHoi();
            if (resultCauHoi.getKey() == ResultStatus.TRUE &&
                    resultCauHoi.getValue() != null &&
                    resultCauHoi.getValue().size() > 0){
                ArrayList<CauHoi> cauHois = resultCauHoi.getValue();

                Result<String> result = new AllDAL(context).saveAll(cauHois);
                Log.i(Def.INFO, result.getMessage());

            }
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //get ChiTietBaiHoc
    private boolean getChiTietBaiHoc(){
        try {
            Result<ArrayList<ChiTietBaiHoc>> resultChiTietBaiHoc = new ChiTietBaiHocDAL(context).getAllChiTietBaiHoc();
            if (resultChiTietBaiHoc.getKey() == ResultStatus.TRUE &&
                    resultChiTietBaiHoc.getValue() != null &&
                    resultChiTietBaiHoc.getValue().size() > 0){
                ArrayList<ChiTietBaiHoc> chiTietBaiHocs = resultChiTietBaiHoc.getValue();

                Result<String> result = new AllDAL(context).saveAll(chiTietBaiHocs);
                Log.i(Def.INFO, result.getMessage());

            }
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //get Chuong
    private boolean getChuong(){
        try {
            Result<ArrayList<Chuong>> resultChuong = new ChuongDAL(context).getAllChuong();
            if (resultChuong.getKey() == ResultStatus.TRUE &&
                    resultChuong.getValue() != null &&
                    resultChuong.getValue().size() > 0){
                ArrayList<Chuong> chuongs = resultChuong.getValue();

                Result<String> result = new AllDAL(context).saveAll(chuongs);
                Log.i(Def.INFO, result.getMessage());

            }
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //get CongThuc
    private boolean getCongThuc(){
        try {
            Result<ArrayList<CongThuc>> resultCongThuc = new CongThucDAL(context).getAllCongThuc();
            if (resultCongThuc.getKey() == ResultStatus.TRUE &&
                    resultCongThuc.getValue() != null &&
                    resultCongThuc.getValue().size() > 0){
                ArrayList<CongThuc> congThucs = resultCongThuc.getValue();

                Result<String> result = new AllDAL(context).saveAll(congThucs);
                Log.i(Def.INFO, result.getMessage());

            }
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //get DapAn
    private boolean getDapAn(){
        try {
            Result<ArrayList<DapAn>> resultDapAn = new DapAnDAL(context).getAllDapAn();
            if (resultDapAn.getKey() == ResultStatus.TRUE &&
                    resultDapAn.getValue() != null &&
                    resultDapAn.getValue().size() > 0){
                ArrayList<DapAn> dapAns = resultDapAn.getValue();

                Result<String> result = new AllDAL(context).saveAll(dapAns);
                Log.i(Def.INFO, result.getMessage());

            }
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //get DapAn
    private boolean getDeThiThu(){
        try {
            Result<ArrayList<DeThiThu>> resultDeThiThu = new DeThiThuDAL(context).getAllDeThiThu();
            if (resultDeThiThu.getKey() == ResultStatus.TRUE &&
                    resultDeThiThu.getValue() != null &&
                    resultDeThiThu.getValue().size() > 0){
                ArrayList<DeThiThu> deThiThus = resultDeThiThu.getValue();

                Result<String> result = new AllDAL(context).saveAll(deThiThus);
                Log.i(Def.INFO, result.getMessage());

            }
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //get DieuKien
    private boolean getDieuKien(){
        try {
            Result<ArrayList<DieuKien>> resultDieuKien = new DieuKienDAL(context).getAllDieuKien();
            if (resultDieuKien.getKey() == ResultStatus.TRUE &&
                    resultDieuKien.getValue() != null &&
                    resultDieuKien.getValue().size() > 0){
                ArrayList<DieuKien> dieuKiens = resultDieuKien.getValue();

                Result<String> result = new AllDAL(context).saveAll(dieuKiens);
                Log.i(Def.INFO, result.getMessage());

            }
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //get DinhLy
    private boolean getDinhLy(){
        try {
            Result<ArrayList<DinhLy>> resultDinhLy = new DinhLyDAL(context).getAllDinhLy();
            if (resultDinhLy.getKey() == ResultStatus.TRUE &&
                    resultDinhLy.getValue() != null &&
                    resultDinhLy.getValue().size() > 0){
                ArrayList<DinhLy> dinhLies = resultDinhLy.getValue();

                Result<String> result = new AllDAL(context).saveAll(dinhLies);
                Log.i(Def.INFO, result.getMessage());

            }
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //get HinhAnh
    private boolean getHinhAnh(){
        try {
            Result<ArrayList<HinhAnh>> resultHinhAnh = new HinhAnhDAL(context).getAllHinhAnh();
            if (resultHinhAnh.getKey() == ResultStatus.TRUE &&
                    resultHinhAnh.getValue() != null &&
                    resultHinhAnh.getValue().size() > 0){
                ArrayList<HinhAnh> hinhAnhs = resultHinhAnh.getValue();

                Result<String> result = new AllDAL(context).saveAll(hinhAnhs);
                Log.i(Def.INFO, result.getMessage());

            }
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //get HinhAnh
    private boolean getMonHoc(){
        try {
            Result<ArrayList<MonHoc>> resultMonHoc = new MonHocDAL(context).getAllMonHoc();
            if (resultMonHoc.getKey() == ResultStatus.TRUE &&
                    resultMonHoc.getValue() != null &&
                    resultMonHoc.getValue().size() > 0){
                ArrayList<MonHoc> monHocs = resultMonHoc.getValue();

                Result<String> result = new AllDAL(context).saveAll(monHocs);
                Log.i(Def.INFO, result.getMessage());

            }
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

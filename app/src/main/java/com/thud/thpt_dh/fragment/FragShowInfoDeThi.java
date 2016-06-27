package com.thud.thpt_dh.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.thud.thpt_dh.R;
import com.thud.thpt_dh.datas.DeThiThuDAL;
import com.thud.thpt_dh.model.DeThiThu;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.ActivityInterface;
import com.thud.thpt_dh.utils.interfaces.Flags;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

/**
 * Created by KhanhNguyen on 5/23/2016.
 */
public class FragShowInfoDeThi extends Fragment implements ActivityInterface{
    private View view;
    private DeThiThu deThiThu = new DeThiThu();
    private TextView txt_ten_dethi, txt_soluong, txt_thoigian, txt_loai,
            txt_title_info_dethi, txt_title_soluong_dethi, txt_title_time_dethi,
            txt_title_hinhthuc_dethi;
    private Button btn_start_dethi, btn_view_pdf_dethi, btn_close_info_dethi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.item_info_dethithu, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        initFlags();

        initControl();

        setEventForControl();

        getData();
    }

    @Override
    public void initFlags() {
        Flags.main_dethi = false;
        Flags.chosen_fragment_vitri_dethi = 2;
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void initControl() {
        txt_title_hinhthuc_dethi = (TextView) view.findViewById(R.id.txt_title_hinhthuc_dethi);
        txt_title_info_dethi = (TextView) view.findViewById(R.id.txt_title_info_dethi);
        txt_title_soluong_dethi = (TextView) view.findViewById(R.id.txt_title_soluong_dethi);
        txt_title_time_dethi = (TextView) view.findViewById(R.id.txt_title_time_dethi);

        txt_ten_dethi = (TextView) view.findViewById(R.id.txt_ten_dethi);
        txt_soluong = (TextView) view.findViewById(R.id.txt_soluong);
        txt_thoigian = (TextView) view.findViewById(R.id.txt_thoigian);
        txt_loai = (TextView) view.findViewById(R.id.txt_loai);

        btn_start_dethi = (Button) view.findViewById(R.id.btn_start_dethi);
        btn_view_pdf_dethi = (Button) view.findViewById(R.id.btn_view_pdf_dethi);
        btn_close_info_dethi = (Button) view.findViewById(R.id.btn_close_info_dethi);

        btn_view_pdf_dethi.setVisibility(View.GONE);
    }

    @Override
    public void setEventForControl() {
        btn_start_dethi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCauHoiDeThi();
        }
        });

        btn_view_pdf_dethi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPDF(v);
            }
        });

        btn_close_info_dethi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    public void getData(String... params) {
        new apiGetDeThi().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void setData() {
        if (deThiThu.getShowpdf() == Flags.xem_pdf_dethi){
            btn_view_pdf_dethi.setVisibility(View.VISIBLE);
        }

        txt_title_info_dethi.setText(R.string.thongtin_dethi);
        txt_title_hinhthuc_dethi.setText(R.string.hinh_thuc_thi);
        txt_title_soluong_dethi.setText(R.string.title_soluong_cauhoi);
        txt_title_time_dethi.setText(R.string.title_thoigian_cauhoi);

        txt_ten_dethi.setText(deThiThu.getTende());
        txt_soluong.setText(""+deThiThu.getSoluong());

        //Toán, Ngữ Văn, Lịch Sử, Địa Lí: 180p
        //Anh Văn, Sinh Học, Vật Lý, Hóa Học: 90p
        if ((Flags.chosen_dethi == 1) ||
                (Flags.chosen_dethi == 2) ||
                (Flags.chosen_dethi == 7) ||
                (Flags.chosen_dethi == 8)){
            txt_thoigian.setText(R.string.time_180);
            Flags.thoigian_lambai = 180;
        }
        else {
            txt_thoigian.setText(R.string.time_90);
            Flags.thoigian_lambai = 90;
        }

        if(deThiThu.getLoai() == 0){
            txt_loai.setText(R.string.thi_tracnghiem);
            Flags.loai_dethi = false;
        }
        else {
            txt_loai.setText(R.string.thi_tu_luan);
            Flags.loai_dethi = true;
        }

    }

    private class apiGetDeThi extends AsyncTask<String, Void, Result<DeThiThu>>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Result<DeThiThu> doInBackground(String... params) {
            return new DeThiThuDAL(getActivity()).getDeThiThuFromLoCal(Flags.madethi);
        }

        @Override
        protected void onPostExecute(Result<DeThiThu> result){
            super.onPostExecute(result);

            if (result.getKey() == ResultStatus.TRUE){
                deThiThu = result.getValue();

                if (deThiThu != null){
                   setData();
                }
            }
        }
    }

    private void showCauHoiDeThi(){
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Fragment fragShowCauHoi = new FragShowCauHoiDeThi();
        fragmentTransaction.replace(R.id.fra_toanhoc, fragShowCauHoi, "Cau Hoi");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    public void viewPDF(View v)
    {
        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/PDF DOWNLOAD/" + "" +deThiThu.getPdfname());  // -> filename = maven.pdf
        Uri path = Uri.fromFile(pdfFile);
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try{
            startActivity(pdfIntent);
        }catch(ActivityNotFoundException e){
            Toast.makeText(getActivity(), "No Application available to view PDF", Toast.LENGTH_SHORT).show();
        }
    }
}

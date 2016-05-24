package com.thud.thpt_dh.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.thud.thpt_dh.R;
import com.thud.thpt_dh.datas.CauHoiDAL;
import com.thud.thpt_dh.datas.DapAnDAL;
import com.thud.thpt_dh.model.CauHoi;
import com.thud.thpt_dh.model.DapAn;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.ActivityInterface;
import com.thud.thpt_dh.utils.interfaces.Flags;

import java.util.ArrayList;

/**
 * Created by KhanhNguyen on 5/23/2016.
 */
public class FragShowCauHoiDeThi extends Fragment implements ActivityInterface{
    private View view;
    private TextView txt_sothutu_cauhoi, txt_noidung_cauhoi;
    private RadioGroup group_rad_dapan;
    private RadioButton rad_dapan_a, rad_dapan_b, rad_dapan_c, rad_dapan_d;
    private Button btn_back, btn_next, btn_check, btn_resume;

    private ArrayList<CauHoi> arr_list_cauhoi = new ArrayList<>();
    private CauHoi cauHoi =  new CauHoi();
    private ArrayList<DapAn> array_list_dapan = new ArrayList<>();
    private int vitri_cauhoi = 0;
    private ArrayList<Integer> arr_cautraloi = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState){
        view = inflater.inflate(R.layout.item_show_detail_cauhoi_dethi, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        initFlags();

        initControl();

        setEventForControl();

        setValue();

        getData();
    }

    @Override
    public void initFlags() {
    }

    @Override
    public void initControl() {
        txt_sothutu_cauhoi = (TextView) view.findViewById(R.id.txt_sothutu_cauhoi);
        txt_noidung_cauhoi = (TextView) view.findViewById(R.id.txt_noidung_cauhoi);

        group_rad_dapan = (RadioGroup) view.findViewById(R.id.group_rad_dapan);
        rad_dapan_a = (RadioButton) view.findViewById(R.id.rad_dapan_a);
        rad_dapan_b = (RadioButton) view.findViewById(R.id.rad_dapan_b);
        rad_dapan_c = (RadioButton) view.findViewById(R.id.rad_dapan_c);
        rad_dapan_d = (RadioButton) view.findViewById(R.id.rad_dapan_d);

        btn_back = (Button) view.findViewById(R.id.btn_back);
        btn_next = (Button) view.findViewById(R.id.btn_next);
        btn_resume = (Button) view.findViewById(R.id.btn_resume);
        btn_check = (Button) view.findViewById(R.id.btn_check);
    }

    @Override
    public void setEventForControl() {
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Flags.vitri_cauhoi += 1;
                vitri_cauhoi += 1;

                //check and set value of array chosen anwer
               /* int i = arr_cautraloi.size();
                if(i < vitri_cauhoi){
                    arr_cautraloi.add(group_rad_dapan.getCheckedRadioButtonId());
                }else {
                    arr_cautraloi.set(vitri_cauhoi ,group_rad_dapan.getCheckedRadioButtonId());
                }*/
                setValue();

                setData();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Flags.vitri_cauhoi -= 1;
                vitri_cauhoi -= 1;


                setValue();

                setData();
            }
        });
    }

    private void setValue(){
        if (Flags.vitri_cauhoi == 1){
            btn_next.setVisibility(View.VISIBLE);

            btn_back.setVisibility(View.GONE);
            btn_resume.setVisibility(View.GONE);
            btn_check.setVisibility(View.GONE);

        }

        if (Flags.vitri_cauhoi > 1 && Flags.vitri_cauhoi < Flags.soluong_cauhoi){
            btn_resume.setVisibility(View.VISIBLE);
            btn_back.setVisibility(View.VISIBLE);
            btn_next.setVisibility(View.VISIBLE);
        }

        if (Flags.vitri_cauhoi == Flags.soluong_cauhoi){
            btn_check.setVisibility(View.VISIBLE);
            btn_back.setVisibility(View.VISIBLE);

            btn_resume.setVisibility(View.GONE);
            btn_next.setVisibility(View.GONE);
        }

        if (Flags.loai_dethi == true){
            group_rad_dapan.setVisibility(View.GONE);
        }
    }

    @Override
    public void getData(String... params) {
        new apiGetCauHoi().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void setData() {
        cauHoi = arr_list_cauhoi.get(vitri_cauhoi);
        txt_sothutu_cauhoi.setText(""+Flags.vitri_cauhoi + "/" + Flags.soluong_cauhoi+":");
        txt_noidung_cauhoi.setText(""+cauHoi.getNoidungch());
    }

    private class apiGetCauHoi extends AsyncTask<String, Void, Result<ArrayList<CauHoi>>>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Result<ArrayList<CauHoi>> doInBackground(String... params) {
            return new CauHoiDAL(getActivity()).getAllCauHoiFromLoCal(Flags.madethi);
        }

        @Override
        protected void onPostExecute(Result<ArrayList<CauHoi>> arrayListResult){
            super.onPostExecute(arrayListResult);

            if (arrayListResult.getKey() == ResultStatus.TRUE){
                arr_list_cauhoi = arrayListResult.getValue();
            }

            setData();
        }
    }

    private class apiGetDapAn extends AsyncTask<String, Void, Result<ArrayList<DapAn>>>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Result<ArrayList<DapAn>> doInBackground(String... params) {
            return new DapAnDAL(getActivity()).getAllDapAnFromLoCal(arr_list_cauhoi.get(vitri_cauhoi).getMadethi()) ;
        }

        @Override
        protected void onPostExecute(Result<ArrayList<DapAn>> arrayListResult){
            super.onPostExecute(arrayListResult);
            if (arrayListResult.getKey() == ResultStatus.TRUE){
                array_list_dapan = arrayListResult.getValue();
            }
        }
    }
}

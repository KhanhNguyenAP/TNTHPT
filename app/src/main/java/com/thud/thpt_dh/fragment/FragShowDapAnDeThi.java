package com.thud.thpt_dh.fragment;

import android.app.Fragment;
import android.graphics.Color;
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
import com.thud.thpt_dh.customcontrols.TextViewEx;
import com.thud.thpt_dh.datas.CauHoiDAL;
import com.thud.thpt_dh.datas.DapAnDAL;
import com.thud.thpt_dh.model.CauHoi;
import com.thud.thpt_dh.model.DapAn;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.ActivityInterface;
import com.thud.thpt_dh.utils.interfaces.Def;
import com.thud.thpt_dh.utils.interfaces.Flags;

import java.util.ArrayList;

import io.github.kexanie.library.MathView;

/**
 * Created by KhanhNguyen on 5/23/2016.
 */
public class FragShowDapAnDeThi extends Fragment implements ActivityInterface{
    private View view;
    private TextView txt_sothutu_cauhoi;
    private TextViewEx txt_noidung_cauhoi, txt_noidung_dapan;;
    private MathView mathview_dethi, mathview_dapan;
    private RadioGroup group_rad_dapan;
    private RadioButton rad_dapan_a, rad_dapan_b, rad_dapan_c, rad_dapan_d;
    private Button btn_back, btn_next, btn_close;

    private ArrayList<CauHoi> arr_list_cauhoi = new ArrayList<>();
    private CauHoi cauHoi =  new CauHoi();
    private ArrayList<DapAn> array_list_dapan = new ArrayList<>();
    private ArrayList<ArrayList<DapAn>> array_dapan = new ArrayList<>();
    private int vitri_cauhoi = 0;
    private ArrayList<Integer> arr_cautraloi = new ArrayList<>();
    private String id_cauhoi = Def.STRING_EMPTY;

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
        Flags.vitri_cauhoi = 1;
    }

    @Override
    public void initControl() {
        txt_sothutu_cauhoi = (TextView) view.findViewById(R.id.txt_sothutu_cauhoi);
        txt_noidung_cauhoi = (TextViewEx) view.findViewById(R.id.txt_noidung_cauhoi);
        txt_noidung_dapan = (TextViewEx) view.findViewById(R.id.txt_noidung_dapan);
        mathview_dethi = (MathView) view.findViewById(R.id.mathview_dethi);
        mathview_dapan = (MathView) view.findViewById(R.id.mathview_dapan);

        group_rad_dapan = (RadioGroup) view.findViewById(R.id.group_rad_dapan);
        rad_dapan_a = (RadioButton) view.findViewById(R.id.rad_dapan_a);
        rad_dapan_b = (RadioButton) view.findViewById(R.id.rad_dapan_b);
        rad_dapan_c = (RadioButton) view.findViewById(R.id.rad_dapan_c);
        rad_dapan_d = (RadioButton) view.findViewById(R.id.rad_dapan_d);

        btn_back = (Button) view.findViewById(R.id.btn_back);
        btn_next = (Button) view.findViewById(R.id.btn_next);
        btn_close = (Button) view.findViewById(R.id.btn_check);

        btn_close.setText(R.string.btn_close);

        if (Flags.loai_dethi == true){
            group_rad_dapan.setVisibility(View.GONE);
            mathview_dapan.setVisibility(View.VISIBLE);
        }
        else{
            group_rad_dapan.setVisibility(View.VISIBLE);
            mathview_dapan.setVisibility(View.GONE);
        }

        arr_cautraloi = Flags.traloi_tracnghiem;
    }

    @Override
    public void setEventForControl() {
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedNext();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBack();
            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Flags.traloi_tracnghiem = null;
                getActivity().onBackPressed();
            }
        });

    }

    private void setValue(){
        if (Flags.vitri_cauhoi == 1){
            btn_next.setVisibility(View.VISIBLE);

            btn_back.setVisibility(View.GONE);
        }

        if (Flags.vitri_cauhoi > 1 && Flags.vitri_cauhoi < Flags.soluong_cauhoi){
            btn_back.setVisibility(View.VISIBLE);
            btn_next.setVisibility(View.VISIBLE);
        }

        if (Flags.vitri_cauhoi == Flags.soluong_cauhoi){
            btn_back.setVisibility(View.VISIBLE);

            btn_next.setVisibility(View.GONE);
        }
    }

    @Override
    public void getData(String... params) {
        new apiGetCauHoi().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void setData() {
        cauHoi = arr_list_cauhoi.get(vitri_cauhoi);
        txt_sothutu_cauhoi.setText(Flags.vitri_cauhoi + "/" + Flags.soluong_cauhoi+":");
        txt_noidung_cauhoi.setText(cauHoi.getNoidungch(), true);
        mathview_dethi.setText(cauHoi.getNoidungch());

        //if Toan, Ly, Hoa thi load mathview
        if (Flags.chosen_dethi == 1 ||
                Flags.chosen_dethi == 5||
                Flags.chosen_dethi == 6){
            txt_noidung_cauhoi.setVisibility(View.GONE);
            mathview_dethi.setVisibility(View.VISIBLE);
        }
        else {
            txt_noidung_cauhoi.setVisibility(View.VISIBLE);
            txt_noidung_dapan.setVisibility(View.VISIBLE);
            mathview_dethi.setVisibility(View.GONE);
        }

        if (Flags.loai_dethi == false){
            rad_dapan_a.setText(array_dapan.get(vitri_cauhoi).get(0).getNoidungda());
            rad_dapan_b.setText(array_dapan.get(vitri_cauhoi).get(1).getNoidungda());
            rad_dapan_c.setText(array_dapan.get(vitri_cauhoi).get(2).getNoidungda());
            rad_dapan_d.setText(array_dapan.get(vitri_cauhoi).get(3).getNoidungda());

            setSelected();
        }
        else{
            String noidung_dapan = Def.STRING_EMPTY;
            for(int i = 0; i< array_dapan.get(vitri_cauhoi).size(); i++){
                noidung_dapan = noidung_dapan + " " + array_dapan.get(vitri_cauhoi).get(i).getNoidungda();
            }
            if (Flags.chosen_dethi == 1 ||
                    Flags.chosen_dethi == 5||
                    Flags.chosen_dethi == 6) {
                mathview_dapan.setText(noidung_dapan);
            }
            else {
                txt_noidung_dapan.setText(noidung_dapan, true);
            }
        }
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

            for(int i=0; i<arr_list_cauhoi.size(); i++){
                id_cauhoi = arr_list_cauhoi.get(i).getId();

                array_list_dapan = new DapAnDAL(getActivity()).getAllDapAnFromLoCal(id_cauhoi);
                array_dapan.add(array_list_dapan);
            }

            setData();
        }
    }

    protected void selectedNext(){
        if (Flags.loai_dethi == false){
            Flags.vitri_cauhoi += 1;
            vitri_cauhoi += 1;

            group_rad_dapan.clearCheck();

            setValue();

            setData();

        }
        else {
            Flags.vitri_cauhoi += 1;
            vitri_cauhoi += 1;

            setValue();

            setData();
        }
    }

    protected void selectedBack(){
        Flags.vitri_cauhoi -= 1;
        vitri_cauhoi -= 1;

        setValue();

        setData();
    }

    private void setSelected(){
        rad_dapan_a.setTextColor(Color.BLACK);
        rad_dapan_b.setTextColor(Color.BLACK);
        rad_dapan_c.setTextColor(Color.BLACK);
        rad_dapan_d.setTextColor(Color.BLACK);


        int vt = arr_cautraloi.get(vitri_cauhoi);
        int vitri = 0;
        for( int i=0 ; i<array_dapan.get(vitri_cauhoi).size(); i++){
            if (array_dapan.get(vitri_cauhoi).get(i).getDung() == 1){
                vitri = i;
            }
        }

        if (vt == 0){
            rad_dapan_a.setTextColor(Color.BLUE);
        }
        if (vt == 1){
            rad_dapan_b.setTextColor(Color.BLUE);
        }
        if (vt == 2){
            rad_dapan_c.setTextColor(Color.BLUE);
        }
        if (vt == 3){
            rad_dapan_d.setTextColor(Color.BLUE);
        }

        //set checked
        if (vitri == 0){
            rad_dapan_a.setChecked(true);
        }
        if (vitri == 1){
            rad_dapan_b.setChecked(true);
        }
        if (vitri == 2){
            rad_dapan_c.setChecked(true);
        }
        if (vitri == 3){
            rad_dapan_d.setChecked(true);
        }
    }
}

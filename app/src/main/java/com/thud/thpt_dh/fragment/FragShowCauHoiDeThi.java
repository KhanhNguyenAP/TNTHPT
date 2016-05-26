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
import com.thud.thpt_dh.utils.dialogs.DialogShowFinish;
import com.thud.thpt_dh.utils.dialogs.ToastMessage;
import com.thud.thpt_dh.utils.interfaces.ActivityInterface;
import com.thud.thpt_dh.utils.interfaces.Def;
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
    private Button btn_back, btn_next, btn_check;

    private ArrayList<CauHoi> arr_list_cauhoi = new ArrayList<>();
    private CauHoi cauHoi =  new CauHoi();
    private ArrayList<DapAn> array_list_dapan = new ArrayList<>();
    private int vitri_cauhoi = 0, so_caudung = 0;
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
        txt_noidung_cauhoi = (TextView) view.findViewById(R.id.txt_noidung_cauhoi);

        group_rad_dapan = (RadioGroup) view.findViewById(R.id.group_rad_dapan);
        rad_dapan_a = (RadioButton) view.findViewById(R.id.rad_dapan_a);
        rad_dapan_b = (RadioButton) view.findViewById(R.id.rad_dapan_b);
        rad_dapan_c = (RadioButton) view.findViewById(R.id.rad_dapan_c);
        rad_dapan_d = (RadioButton) view.findViewById(R.id.rad_dapan_d);

        btn_back = (Button) view.findViewById(R.id.btn_back);
        btn_next = (Button) view.findViewById(R.id.btn_next);
        btn_check = (Button) view.findViewById(R.id.btn_check);
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

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTrueFalse();

                //new ToastMessage(getActivity()).showToast(""+so_caudung);
                new DialogShowFinish(getActivity()).showConfirm(so_caudung);
            }
        });

    }

    private void setValue(){
        if (Flags.vitri_cauhoi == 1){
            btn_next.setVisibility(View.VISIBLE);

            btn_back.setVisibility(View.GONE);
            btn_check.setVisibility(View.GONE);
        }

        if (Flags.vitri_cauhoi > 1 && Flags.vitri_cauhoi < Flags.soluong_cauhoi){
            btn_back.setVisibility(View.VISIBLE);
            btn_next.setVisibility(View.VISIBLE);

            btn_check.setVisibility(View.GONE);
        }

        if (Flags.vitri_cauhoi == Flags.soluong_cauhoi){
            btn_check.setVisibility(View.VISIBLE);
            btn_back.setVisibility(View.VISIBLE);

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

    protected void selectedNext(){
        int selectedId = group_rad_dapan.getCheckedRadioButtonId();
        if (selectedId == -1){
            new ToastMessage(getActivity()).showToast(getResources().getString(R.string.msg_not_select_dapan));
        }
        else {
            RadioButton checked = (RadioButton) group_rad_dapan.findViewById(selectedId);
            int check = group_rad_dapan.indexOfChild(checked);

            Flags.vitri_cauhoi += 1;
            vitri_cauhoi += 1;

            int i = arr_cautraloi.size();
            if(i < vitri_cauhoi){
                arr_cautraloi.add(check);
            }else {
                arr_cautraloi.set(vitri_cauhoi -1 ,check);
            }
            setValue();

            setData();

            group_rad_dapan.clearCheck();
        }
    }

    protected void selectedBack(){
        Flags.vitri_cauhoi -= 1;
        vitri_cauhoi -= 1;

        setValue();

        setData();

        setSelected();
    }

    private void setSelected(){
        int vt = arr_cautraloi.get(vitri_cauhoi);
        if (vt == 0){
            rad_dapan_a.setChecked(true);
        }
        if (vt == 1){
            rad_dapan_b.setChecked(true);
        }
        if (vt == 2){
            rad_dapan_c.setChecked(true);
        }
        if (vt == 3){
            rad_dapan_d.setChecked(true);
        }
    }

    private void checkTrueFalse(){
        int selectedId = group_rad_dapan.getCheckedRadioButtonId();
        RadioButton checked = (RadioButton) group_rad_dapan.findViewById(selectedId);
        int check = group_rad_dapan.indexOfChild(checked);
        arr_cautraloi.add(check);

        for(int i=0; i<arr_cautraloi.size(); i++){
            id_cauhoi = arr_list_cauhoi.get(i).getId();
            array_list_dapan = new DapAnDAL(getActivity()).getAllDapAnFromLoCal(id_cauhoi);

            if(array_list_dapan.get(arr_cautraloi.get(i)).getDung() == 1){
                so_caudung = so_caudung + 1;
            }
        }

    }
}

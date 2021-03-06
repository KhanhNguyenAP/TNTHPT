package com.thud.thpt_dh.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import com.thud.thpt_dh.utils.dialogs.ToastMessage;
import com.thud.thpt_dh.utils.interfaces.ActivityInterface;
import com.thud.thpt_dh.utils.interfaces.Def;
import com.thud.thpt_dh.utils.interfaces.Flags;

import java.util.ArrayList;

import io.github.kexanie.library.MathView;

/**
 * Created by KhanhNguyen on 5/23/2016.
 */
public class FragShowCauHoiDeThi extends Fragment implements ActivityInterface{
    private View view;
    private TextView txt_sothutu_cauhoi;
    private TextViewEx txt_noidung_cauhoi;
    private MathView mathview_dethi, mathview_dapan;
    private RadioGroup group_rad_dapan;
    private RadioButton rad_dapan_a, rad_dapan_b, rad_dapan_c, rad_dapan_d;
    private Button btn_back, btn_next, btn_check;

    private ArrayList<CauHoi> arr_list_cauhoi = new ArrayList<>();
    private CauHoi cauHoi =  new CauHoi();
    private ArrayList<DapAn> array_list_dapan = new ArrayList<>();
    private ArrayList<ArrayList<DapAn>> array_dapan = new ArrayList<>();
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
        txt_noidung_cauhoi = (TextViewEx) view.findViewById(R.id.txt_noidung_cauhoi);
        mathview_dethi = (MathView) view.findViewById(R.id.mathview_dethi);
        mathview_dapan = (MathView) view.findViewById(R.id.mathview_dapan);

        group_rad_dapan = (RadioGroup) view.findViewById(R.id.group_rad_dapan);
        rad_dapan_a = (RadioButton) view.findViewById(R.id.rad_dapan_a);
        rad_dapan_b = (RadioButton) view.findViewById(R.id.rad_dapan_b);
        rad_dapan_c = (RadioButton) view.findViewById(R.id.rad_dapan_c);
        rad_dapan_d = (RadioButton) view.findViewById(R.id.rad_dapan_d);

        btn_back = (Button) view.findViewById(R.id.btn_back);
        btn_next = (Button) view.findViewById(R.id.btn_next);
        btn_check = (Button) view.findViewById(R.id.btn_check);

        mathview_dapan.setVisibility(View.GONE);

        if (Flags.loai_dethi == true){
            group_rad_dapan.setVisibility(View.GONE);
            txt_noidung_cauhoi.setVisibility(View.GONE);
        }
        else {
            group_rad_dapan.setVisibility(View.VISIBLE);
        }
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
                if (Flags.loai_dethi == false){
                    int selectedId = group_rad_dapan.getCheckedRadioButtonId();
                    if (selectedId == -1){
                        new ToastMessage(getActivity()).showToast(getResources().getString(R.string.msg_not_select_dapan));
                    }
                    else {
                        btn_check.setVisibility(View.GONE);

                        checkTrueFalse();

                        Flags.traloi_tracnghiem = arr_cautraloi;

                        showDapAn();
                    }
                }
                else {
                    btn_check.setVisibility(View.GONE);

                    showDapAn();
                }
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

        //if Toan, Ly, Hoa thi2 load mathview
        if (Flags.chosen_dethi == 1 ||
                Flags.chosen_dethi == 5||
                Flags.chosen_dethi == 6){
            txt_noidung_cauhoi.setVisibility(View.GONE);
            mathview_dethi.setVisibility(View.VISIBLE);
        }
        else {
            txt_noidung_cauhoi.setVisibility(View.VISIBLE);
            mathview_dethi.setVisibility(View.GONE);
        }

        if (Flags.loai_dethi == false){
            rad_dapan_a.setText(array_dapan.get(vitri_cauhoi).get(0).getNoidungda());
            rad_dapan_b.setText(array_dapan.get(vitri_cauhoi).get(1).getNoidungda());
            rad_dapan_c.setText(array_dapan.get(vitri_cauhoi).get(2).getNoidungda());
            rad_dapan_d.setText(array_dapan.get(vitri_cauhoi).get(3).getNoidungda());
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

            Flags.soluong_cauhoi = arr_list_cauhoi.size();

            setData();
        }
    }

    protected void selectedNext(){
        if (Flags.loai_dethi == false){
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

        if(Flags.loai_dethi == false){
            setSelected();
        }
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
            if(array_dapan.get(i).get(arr_cautraloi.get(i)).getDung() == 1){
                so_caudung = so_caudung + 1;
            }
        }
    }

    public void showDapAn(){
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Fragment fragDapAnDeThi = new FragShowDapAnDeThi();
        fragmentTransaction.replace(R.id.fra_toanhoc, fragDapAnDeThi, "De Thi");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }
}

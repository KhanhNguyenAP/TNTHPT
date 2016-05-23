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
import android.widget.TextView;

import com.thud.thpt_dh.R;
import com.thud.thpt_dh.datas.DeThiThuDAL;
import com.thud.thpt_dh.model.DeThiThu;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.ActivityInterface;
import com.thud.thpt_dh.utils.interfaces.Flags;

/**
 * Created by KhanhNguyen on 5/23/2016.
 */
public class FragShowInfoDeThi extends Fragment implements ActivityInterface{
    private View view;
    private DeThiThu deThiThu = new DeThiThu();
    private TextView txt_ten_dethi, txt_soluong, txt_thoigian, txt_loai;
    private Button btn_start_dethi;

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
        txt_ten_dethi = (TextView) view.findViewById(R.id.txt_ten_dethi);
        txt_soluong = (TextView) view.findViewById(R.id.txt_soluong);
        txt_thoigian = (TextView) view.findViewById(R.id.txt_thoigian);
        txt_loai = (TextView) view.findViewById(R.id.txt_loai);
        btn_start_dethi = (Button) view.findViewById(R.id.btn_start_dethi);
    }

    @Override
    public void setEventForControl() {
        btn_start_dethi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCauHoiDeThi();
            }
        });
    }

    @Override
    public void getData(String... params) {
        new apiGetDeThi().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void setData() {
        txt_ten_dethi.setText(deThiThu.getTende());
        txt_soluong.setText(""+deThiThu.getSoluong());

        Flags.soluong_cauhoi = deThiThu.getSoluong();
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
}

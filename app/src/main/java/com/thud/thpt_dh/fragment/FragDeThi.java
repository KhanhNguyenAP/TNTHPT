package com.thud.thpt_dh.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thud.thpt_dh.R;
import com.thud.thpt_dh.adapters.DeThiThuAdapter;
import com.thud.thpt_dh.datas.DeThiThuDAL;
import com.thud.thpt_dh.model.DeThiThu;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.ActivityInterface;
import com.thud.thpt_dh.utils.interfaces.Flags;

import java.util.ArrayList;

/**
 * Created by KhanhNguyen on 5/15/2016.
 */
public class FragDeThi extends Fragment implements ActivityInterface {
    private View view;
    private ListView lst_dethi;
    private ArrayList<DeThiThu> array_dethi = new ArrayList<>();
    private DeThiThuAdapter dethithu_list_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.item_list_view, container, false);
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
    public void onResume(){
        super.onResume();
        Flags.main_dethi = true;
        getData();
    }

    @Override
    public void initFlags() {
        Flags.main_dethi = true;
        Flags.chosen_fragment_vitri_dethi = 1;
    }

    @Override
    public void initControl() {
        lst_dethi = (ListView) view.findViewById(R.id.lst_list_view);
    }

    @Override
    public void setEventForControl() {
       lst_dethi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Flags.madethi = array_dethi.get(position).getId();
               showDeTail();
           }
       });
    }

    @Override
    public void getData(String... params) {
        new apiGetDeThiThu().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void setData() {
    }

    private  class apiGetDeThiThu extends AsyncTask<String, Void, Result<ArrayList<DeThiThu>>>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Result<ArrayList<DeThiThu>> doInBackground(String... strings) {
            return new DeThiThuDAL(getActivity()).getAllDeThiThuFromLoCal(Flags.chosen_dethi);
        }

        @Override
        protected void onPostExecute(Result<ArrayList<DeThiThu>> arrayListResult){
            super.onPostExecute(arrayListResult);
            if (arrayListResult.getKey() == ResultStatus.TRUE){
                array_dethi = arrayListResult.getValue();

                if(array_dethi != null){
                    dethithu_list_adapter = new DeThiThuAdapter(getActivity(), array_dethi);
                    lst_dethi.setAdapter(dethithu_list_adapter);
                }
            }
        }
    }

    public void showDeTail(){
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Fragment fragInfoDeThi = new FragShowInfoDeThi();
        fragmentTransaction.replace(R.id.fra_toanhoc, fragInfoDeThi, "De Thi");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }
}

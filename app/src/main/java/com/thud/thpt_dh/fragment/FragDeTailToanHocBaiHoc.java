package com.thud.thpt_dh.fragment;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.thud.thpt_dh.R;
import com.thud.thpt_dh.adapters.ChiTietBaiHocAdapter;
import com.thud.thpt_dh.datas.ChiTietBaiHocDAL;
import com.thud.thpt_dh.model.ChiTietBaiHoc;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.ActivityInterface;
import com.thud.thpt_dh.utils.interfaces.Flags;

import java.util.ArrayList;

/**
 * Created by KhanhNguyen on 5/15/2016.
 */
public class FragDeTailToanHocBaiHoc extends Fragment implements ActivityInterface {
    private View view;
    private ListView lst_list_view;
    private ArrayList<ChiTietBaiHoc> array_baihoc = new ArrayList<>();
    private ChiTietBaiHocAdapter baihoc_list_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.item_list_view, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        initFlags();

        initControl();;

        setEventForControl();

        getData();
    }

    @Override
    public void initFlags() {
        Flags.chosen_fragment_vitri = 2;
    }

    @Override
    public void initControl() {
        lst_list_view = (ListView) view.findViewById(R.id.lst_list_view);
    }

    @Override
    public void setEventForControl() {
    }

    @Override
    public void getData(String... params) {
        new apiGetChiTietBaiHoc().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void setData() {

    }

    private  class apiGetChiTietBaiHoc extends AsyncTask<String, Void, Result<ArrayList<ChiTietBaiHoc>>>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Result<ArrayList<ChiTietBaiHoc>> doInBackground(String... strings) {
            return new ChiTietBaiHocDAL(getActivity()).getAllChiTietBaiHocFromLocal(Flags.mabaihoc);
        }

        @Override
        protected void onPostExecute(Result<ArrayList<ChiTietBaiHoc>> arrayListResult){
            super.onPostExecute(arrayListResult);
            if (arrayListResult.getKey() == ResultStatus.TRUE){
                array_baihoc = arrayListResult.getValue();

                if(array_baihoc != null){
                    baihoc_list_adapter = new ChiTietBaiHocAdapter(getActivity(), array_baihoc);
                    lst_list_view.setAdapter(baihoc_list_adapter);
                }
            }
        }
    }
}

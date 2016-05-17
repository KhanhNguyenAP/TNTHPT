package com.thud.thpt_dh.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.thud.thpt_dh.R;
import com.thud.thpt_dh.adapters.BaiHocAdapter;
import com.thud.thpt_dh.adapters.ChuongAdapter;
import com.thud.thpt_dh.datas.BaiHocDAL;
import com.thud.thpt_dh.datas.ChuongDAL;
import com.thud.thpt_dh.model.BaiHoc;
import com.thud.thpt_dh.model.Chuong;
import com.thud.thpt_dh.model.MonHoc;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.ActivityInterface;
import com.thud.thpt_dh.utils.interfaces.Def;
import com.thud.thpt_dh.utils.interfaces.Flags;

import java.util.ArrayList;

/**
 * Created by KhanhNguyen on 5/15/2016.
 */
public class FragDeTailToanHoc extends Fragment implements ActivityInterface {
    private View view;
    private ListView lst_toanhoc;
    private ArrayList<BaiHoc> array_baihoc = new ArrayList<>();
    private BaiHocAdapter baihoc_list_adapter;

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

    }

    @Override
    public void initControl() {
        lst_toanhoc = (ListView) view.findViewById(R.id.lst_list_view);
    }

    @Override
    public void setEventForControl() {
        lst_toanhoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    @Override
    public void getData(String... params) {
        new apiGetBaiHoc().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void setData() {

    }

    private  class apiGetBaiHoc extends AsyncTask<String, Void, Result<ArrayList<BaiHoc>>>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Result<ArrayList<BaiHoc>> doInBackground(String... strings) {
            return new BaiHocDAL(getActivity()).getAllBaiHocFromLocal(Flags.machuong);
        }

        @Override
        protected void onPostExecute(Result<ArrayList<BaiHoc>> arrayListResult){
            super.onPostExecute(arrayListResult);
            if (arrayListResult.getKey() == ResultStatus.TRUE){
                array_baihoc = arrayListResult.getValue();

                if(array_baihoc != null){
                    baihoc_list_adapter = new BaiHocAdapter(getActivity(), array_baihoc);
                    lst_toanhoc.setAdapter(baihoc_list_adapter);
                }
            }
        }
    }
}

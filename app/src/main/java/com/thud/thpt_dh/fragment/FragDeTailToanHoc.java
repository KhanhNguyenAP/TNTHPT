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
import android.widget.Button;
import android.widget.ListView;

import com.thud.thpt_dh.R;
import com.thud.thpt_dh.adapters.BaiHocAdapter;
import com.thud.thpt_dh.datas.BaiHocDAL;
import com.thud.thpt_dh.model.BaiHoc;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.ActivityInterface;
import com.thud.thpt_dh.utils.interfaces.Flags;

import java.util.ArrayList;

/**
 * Created by KhanhNguyen on 5/15/2016.
 */
public class FragDeTailToanHoc extends Fragment implements ActivityInterface {
    private View view;
    private ListView lst_toanhoc;
    private Button btn_back_list;
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
        Flags.main_toan = false;
        Flags.main_nguvan = false;
        Flags.chosen_fragment_vitri = 2;
    }

    @Override
    public void initControl() {
        lst_toanhoc = (ListView) view.findViewById(R.id.lst_list_view);
        btn_back_list = (Button) view.findViewById(R.id.btn_back_list);
    }

    @Override
    public void setEventForControl() {
        lst_toanhoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Flags.mabaihoc = array_baihoc.get(i).getId();

                showDeTail();
            }
        });

        btn_back_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
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

    @Override
    public void onResume() {
        super.onResume();
        getData();
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

    public void showDeTail(){
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Fragment fragDeTail = new FragDeTailToanHocBaiHoc();
        fragmentTransaction.replace(R.id.fra_toanhoc, fragDeTail, "Toan Hoc");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }
}

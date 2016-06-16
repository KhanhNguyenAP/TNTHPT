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
import com.thud.thpt_dh.adapters.ChuongAdapter;
import com.thud.thpt_dh.adapters.LinhVucAdapter;
import com.thud.thpt_dh.datas.ChuongDAL;
import com.thud.thpt_dh.datas.LinhVucDAL;
import com.thud.thpt_dh.model.Chuong;
import com.thud.thpt_dh.model.LinhVuc;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.ActivityInterface;
import com.thud.thpt_dh.utils.interfaces.Flags;

import java.util.ArrayList;

/**
 * Created by KhanhNguyen on 5/15/2016.
 */
public class FragLinhVuc extends Fragment implements ActivityInterface {
    private View view;
    private ListView lst_linhvuc;
    private Button btn_back_list;
    private ArrayList<LinhVuc> array_linhvuc = new ArrayList<>();
    private LinhVucAdapter linhvuc_adapter;

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
        Flags.main_toan = true;
        Flags.main_nguvan = true;
        getData();
    }

    @Override
    public void initFlags() {
        Flags.main_toan = true;
    }

    @Override
    public void initControl() {
        lst_linhvuc = (ListView) view.findViewById(R.id.lst_list_view);
        btn_back_list = (Button) view.findViewById(R.id.btn_back_list);
    }

    @Override
    public void setEventForControl() {
       lst_linhvuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Flags.malinhvuc = array_linhvuc.get(position).getId();
               showFragToanHoc();
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
        new apiGetLinhVuc().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void setData() {

    }

    private  class apiGetLinhVuc extends AsyncTask<String, Void, Result<ArrayList<LinhVuc>>>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Result<ArrayList<LinhVuc>> doInBackground(String... strings) {
            return new LinhVucDAL(getActivity()).getAllLinhVucFromLocal(Flags.chosen_mon);
        }

        @Override
        protected void onPostExecute(Result<ArrayList<LinhVuc>> arrayListResult){
            super.onPostExecute(arrayListResult);
            if (arrayListResult.getKey() == ResultStatus.TRUE){
                array_linhvuc = arrayListResult.getValue();

                if(array_linhvuc != null){
                    linhvuc_adapter = new LinhVucAdapter(getActivity(), array_linhvuc);
                    lst_linhvuc.setAdapter(linhvuc_adapter);
                }
            }
        }
    }

    public void showFragToanHoc(){
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Fragment fragToanHoc = new FragToanHoc();
        fragmentTransaction.replace(R.id.fra_toanhoc, fragToanHoc, "Toan Hoc");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }


}

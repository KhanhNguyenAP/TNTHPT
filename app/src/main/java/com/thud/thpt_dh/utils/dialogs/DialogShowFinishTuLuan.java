package com.thud.thpt_dh.utils.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.thud.thpt_dh.R;
import com.thud.thpt_dh.activities.DeThiDeTailActivity;
import com.thud.thpt_dh.adapters.DapAnAdapter;
import com.thud.thpt_dh.datas.DapAnDAL;
import com.thud.thpt_dh.model.CauHoi;
import com.thud.thpt_dh.model.ChiTietBaiHoc;
import com.thud.thpt_dh.model.DapAn;
import com.thud.thpt_dh.utils.interfaces.ActivityInterface;
import com.thud.thpt_dh.utils.interfaces.Flags;

import java.util.ArrayList;

/**
 * Created by KhanhNguyen on 5/26/2016.
 */
public class DialogShowFinishTuLuan implements ActivityInterface {
    private View view;
    private ListView lst_list_view;
    private Button btn_close;
    private DapAnAdapter dapan_adapter;
    private ArrayList<DapAn> array_list_dapan = new ArrayList<>();

    private Context context;

    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alert;

    public DialogShowFinishTuLuan(Context current){
        this.context = current;
    }

    @Override
    public void initFlags() {

    }

    @Override
    public void initControl() {
        lst_list_view = (ListView) view.findViewById(R.id.lst_list_view);
        btn_close = (Button) view.findViewById(R.id.btn_close);
    }

    @Override
    public void setEventForControl() {
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();

                Intent intent = new Intent(context, DeThiDeTailActivity.class);
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        });
    }

    @Override
    public void getData(String... params) {
    }

    @Override
    public void setData() {
    }

    public void showConfirm(ArrayList<CauHoi> cauHois) {
        view = ((Activity) context).getLayoutInflater().inflate(R.layout.item_list_view_button, null);

        for (int i=0; i<cauHois.size(); i++){
            ArrayList<DapAn> array_dapan = new ArrayList<>();
            array_dapan = new DapAnDAL(context).getAllDapAnFromLoCal(cauHois.get(i).getId());
            for(int k=0; k<array_dapan.size(); k++){
                array_list_dapan.add(array_dapan.get(k));
            }
        }

        initControl();

        setEventForControl();

        if (array_list_dapan != null){
            dapan_adapter = new DapAnAdapter(context, array_list_dapan);
            lst_list_view.setAdapter(dapan_adapter);
        }

        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setCancelable(false);

        // create an alert dialog
        alert = alertDialogBuilder.create();
        alert.show();
    }

}

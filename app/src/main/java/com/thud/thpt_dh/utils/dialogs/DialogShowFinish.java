package com.thud.thpt_dh.utils.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.thud.thpt_dh.R;
import com.thud.thpt_dh.activities.DeThiDeTailActivity;
import com.thud.thpt_dh.model.DeThiThu;
import com.thud.thpt_dh.utils.interfaces.ActivityInterface;
import com.thud.thpt_dh.utils.interfaces.Flags;

/**
 * Created by KhanhNguyen on 5/26/2016.
 */
public class DialogShowFinish implements ActivityInterface {
    private View view;
    private TextView txt_ten_dethi, txt_socaudung, txt_thoigian, txt_sodiem,
            txt_title_info_dethi, txt_title_soluong_dethi, txt_title_time_dethi,
            txt_title_hinhthuc_dethi;
    private Button btn_start_dethi;
    private int socaudung;
    private Context context;

    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alert;

    public DialogShowFinish(Context current){
        this.context = current;
    }

    @Override
    public void initFlags() {

    }

    @Override
    public void initControl() {
        txt_title_hinhthuc_dethi = (TextView) view.findViewById(R.id.txt_title_hinhthuc_dethi);
        txt_title_info_dethi = (TextView) view.findViewById(R.id.txt_title_info_dethi);
        txt_title_soluong_dethi = (TextView) view.findViewById(R.id.txt_title_soluong_dethi);
        txt_title_time_dethi = (TextView) view.findViewById(R.id.txt_title_time_dethi);

        txt_ten_dethi = (TextView) view.findViewById(R.id.txt_ten_dethi);
        txt_socaudung = (TextView) view.findViewById(R.id.txt_soluong);
        txt_thoigian = (TextView) view.findViewById(R.id.txt_thoigian);
        txt_sodiem = (TextView) view.findViewById(R.id.txt_loai);
        btn_start_dethi = (Button) view.findViewById(R.id.btn_start_dethi);
    }

    @Override
    public void setEventForControl() {
        btn_start_dethi.setOnClickListener(new View.OnClickListener() {
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
        txt_ten_dethi.setVisibility(View.GONE);

        txt_title_info_dethi.setText(R.string.dialog_info);
        txt_title_soluong_dethi.setText(R.string.dialog_socaudung);
        txt_title_time_dethi.setText(R.string.dialog_thoigian);
        txt_title_hinhthuc_dethi.setText(R.string.dialog_sodiem);

        txt_socaudung.setText(""+socaudung);
        txt_sodiem.setText(""+ socaudung*10/Flags.soluong_cauhoi);

        btn_start_dethi.setText(R.string.dialog_close);
    }

    public void showConfirm(int socau) {
        view = ((Activity) context).getLayoutInflater().inflate(R.layout.item_show_dialog, null);
        socaudung = socau;

        initControl();

        setData();

        setEventForControl();

        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setCancelable(false);

        // create an alert dialog
        alert = alertDialogBuilder.create();
        alert.show();
    }

}

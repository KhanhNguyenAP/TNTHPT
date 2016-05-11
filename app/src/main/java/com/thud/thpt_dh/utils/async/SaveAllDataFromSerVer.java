package com.thud.thpt_dh.utils.async;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.assist.AssistStructure;
import android.content.Context;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.thud.thpt_dh.R;
import com.thud.thpt_dh.model.AllDAL;
import com.thud.thpt_dh.model.MonHoc;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.Preference;
import com.thud.thpt_dh.utils.dialogs.ToastMessage;
import com.thud.thpt_dh.utils.interfaces.ActivityInterface;
import com.thud.thpt_dh.utils.interfaces.Flags;

import java.util.ArrayList;

/**
 * Created by khanh on 5/11/2016.
 */
public class SaveAllDataFromSerVer extends AsyncTask<Void, Integer, Result<String>> implements ActivityInterface{
    private AlertDialog alertDialog;
    private AlertDialog.Builder alertDialogBuilder;
    private View view;
    private TextView text_view_percent, text_view_title;
    private ProgressBar progress_custom;
    private Context context;
    private PowerManager.WakeLock mWakerLock;
    private Preference preference;
    private  int countDataComplete = 0;

    public SaveAllDataFromSerVer(Context current){
        this.context = current;
        view = ((Activity) context).getLayoutInflater().inflate(R.layout.custom_progressbar_percent, null);

        alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setCancelable(false);

        initControl();
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        mWakerLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());
        mWakerLock.acquire();

        //create an alert dialog
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }


    @Override
    protected Result<String> doInBackground(Void... params) {
        try {
            if (Flags.synch_data == 0){
                new AllDAL(context).dropAllTable();
            }
            else {
                return new Result<String>(ResultStatus.FALSE, null, context.getResources().getString(R.string.msg_can_not_connect_to_network));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new Result<String>(ResultStatus.FALSE, null, context.getResources().getString(R.string.msg_can_not_connect_to_network));
    }

    @Override
    protected void onProgressUpdate(Integer... progress){
        super.onProgressUpdate(progress);
        setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(Result<String> result){
        super.onPostExecute(result);
        alertDialog.dismiss();

        if(result.getKey() == ResultStatus.FALSE){
            new ToastMessage(context).showToast(result.getMessage());
        }
        else {
            new ToastMessage(context).showToast(context.getResources().getString(R.string.msg_download_data));
            //Flags.synch_data = 1;
        }
    }

    @Override
    public void initFlags() {

    }

    @Override
    public void initControl() {
        text_view_title = (TextView)view.findViewById(R.id.text_view_title);
        text_view_percent = (TextView)view.findViewById(R.id.text_view_percent);
        progress_custom = (ProgressBar)view.findViewById(R.id.progress_custom);
    }

    @Override
    public void setEventForControl() {

    }

    @Override
    public void getData(String... params) {

    }

    @Override
    public void setData() {

    }

    private void setProgress(int progressNumber){
        progress_custom.setProgress(progressNumber);
        text_view_percent.setText(progressNumber*10+"%");
    }

    //get MonHoc
    private boolean getMonHoc(){
        try {

            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

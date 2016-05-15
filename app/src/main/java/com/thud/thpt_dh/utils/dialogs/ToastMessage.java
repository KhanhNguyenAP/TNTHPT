package com.thud.thpt_dh.utils.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.thud.thpt_dh.R;
import com.thud.thpt_dh.utils.interfaces.Def;

public class ToastMessage {
    private Context context;

    public ToastMessage(Context context){
        this.context = context;
    }

    public void showToast(String message){
        try{
            Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            View view = toast.getView();
            view.setBackgroundResource(R.color.bg_black);
            TextView text = (TextView) view.findViewById(android.R.id.message);
            text.setTextColor(Color.WHITE);
            /*here you can do anything with text*/
            toast.show();
        }
        catch (Exception e){
            Log.e(Def.ERROR, e.getMessage());
            e.printStackTrace();
        }
    }

}

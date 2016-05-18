package com.thud.thpt_dh.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thud.thpt_dh.R;
import com.thud.thpt_dh.model.BaiHoc;
import com.thud.thpt_dh.model.ChiTietBaiHoc;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by KhanhNguyen on 5/15/2016.
 */
public class ChiTietBaiHocAdapter extends ArrayAdapter<ChiTietBaiHoc> {
    private Context context;
    private ArrayList<ChiTietBaiHoc> values;
    HashMap<Integer, Bitmap> hash_item = new HashMap<>();

    public ChiTietBaiHocAdapter(Context context, ArrayList<ChiTietBaiHoc> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public void clear(){
        this.values = null;
        notifyDataSetChanged();
        super.clear();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_detail_baihoc, parent, false);
        rowView.setTag(values.get(position).getId());

        final TextView text_view_title_monhoc = (TextView) rowView.findViewById(R.id.textview_title);
        final TextView text_view_noidung_monhoc = (TextView) rowView.findViewById(R.id.text_view_noidung_monhoc);
        final TextView text_view_noidung_congthuc = (TextView) rowView.findViewById(R.id.text_view_noidung_congthuc);

        text_view_title_monhoc.setText(values.get(position).getTenctbh());
        text_view_noidung_monhoc.setText(values.get(position).getNoidungctbh());
        text_view_noidung_congthuc.setText(values.get(position).getNoidungct());

        if (text_view_noidung_congthuc.getText().equals("null")){
            text_view_noidung_congthuc.setVisibility(View.GONE);
        }

        return rowView;
    }
}
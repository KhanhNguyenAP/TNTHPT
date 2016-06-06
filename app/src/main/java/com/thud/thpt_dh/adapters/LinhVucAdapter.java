package com.thud.thpt_dh.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thud.thpt_dh.R;
import com.thud.thpt_dh.model.Chuong;
import com.thud.thpt_dh.model.LinhVuc;

import java.util.ArrayList;

/**
 * Created by KhanhNguyen on 5/15/2016.
 */
public class LinhVucAdapter extends ArrayAdapter<LinhVuc> {
    private Context context;
    private ArrayList<LinhVuc> values;

    public LinhVucAdapter(Context context, ArrayList<LinhVuc> values) {
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
        View rowView;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_monhoc, parent, false);
        }
        else {
            rowView = convertView;
        }

        rowView.setTag(values.get(position).getId());
        final TextView text_view_title_monhoc = (TextView) rowView.findViewById(R.id.text_view_title_monhoc);
        text_view_title_monhoc.setText(values.get(position).getTenlinhvuc());
        return rowView;
    }
}

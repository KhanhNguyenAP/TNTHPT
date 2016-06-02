package com.thud.thpt_dh.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thud.thpt_dh.R;
import com.thud.thpt_dh.model.ChiTietBaiHoc;
import com.thud.thpt_dh.model.DapAn;
import com.thud.thpt_dh.utils.interfaces.Flags;

import java.util.ArrayList;

import io.github.kexanie.library.MathView;

/**
 * Created by KhanhNguyen on 5/15/2016.
 */
public class DapAnAdapter extends ArrayAdapter<DapAn> {
    private Context context;
    private ArrayList<DapAn> values;

    public DapAnAdapter(Context context, ArrayList<DapAn> values) {
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
        final MathView mathview = (MathView) rowView.findViewById(R.id.mathview);

        text_view_title_monhoc.setText("Câu " + (position+1));
        text_view_noidung_monhoc.setText(values.get(position).getNoidungda());
        mathview.setText(values.get(position).getNoidungda());

        if (Flags.chosen_dethi == 1
            || Flags.chosen_dethi == 5
            || Flags.chosen_dethi == 6){
            mathview.setVisibility(View.VISIBLE);
            text_view_noidung_monhoc.setVisibility(View.GONE);
        }
        else {
            mathview.setVisibility(View.GONE);
            text_view_noidung_monhoc.setVisibility(View.VISIBLE);
        }

        return rowView;
    }
}

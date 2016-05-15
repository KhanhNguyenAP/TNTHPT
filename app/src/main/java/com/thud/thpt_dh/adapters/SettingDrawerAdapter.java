package com.thud.thpt_dh.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.thud.thpt_dh.model.DrawerSetting;

import java.util.ArrayList;
import java.util.HashMap;
import com.thud.thpt_dh.R;
/**
 * Created by KhanhNguyen on 13/5/2016.
 */
public class SettingDrawerAdapter extends ArrayAdapter<DrawerSetting> {
    private Context context;
    private final ArrayList<DrawerSetting> values;
    private HashMap<String, DrawerSetting> hash_values = new HashMap<>();
    public SettingDrawerAdapter(Context context, ArrayList<DrawerSetting> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_navigation_drawer, parent, false);
        }
        else{
            rowView = convertView;
        }
        if(!hash_values.containsKey(values.get(position).getTitle())){
            hash_values.put(values.get(position).getTitle(), values.get(position));
        }
        rowView.setTag(values.get(position).getTitle());
        final ImageView img_setting_drawer = (ImageView) rowView.findViewById(R.id.img_setting_drawer);
        final TextView text_view_name_setting_drawer = (TextView) rowView.findViewById(R.id.text_view_name_setting_drawer);
        final RelativeLayout item_navigation = (RelativeLayout) rowView.findViewById(R.id.item_navigation);

        img_setting_drawer.setBackground(values.get(position).getIcon());
        text_view_name_setting_drawer.setText(values.get(position).getTitle());
        item_navigation.setBackground(values.get(position).getBackground());

        //build version
        if(values.get(position).getTitle().length() > 60){
            text_view_name_setting_drawer.setTextColor(Color.RED);
            text_view_name_setting_drawer.setPadding(0, 0, 0, 0);
            item_navigation.setPadding(10, 0, 0, 0);
        }
        else{
            if(values.get(position).getTitle().length() > 40 &&
                    values.get(position).getTitle().length() < 60){
                text_view_name_setting_drawer.setTextColor(Color.WHITE);
                text_view_name_setting_drawer.setPadding(0, 0, 0, 0);
                item_navigation.setPadding(10, 0, 0, 0);
            }
            else{
                text_view_name_setting_drawer.setTextColor(Color.WHITE);
                text_view_name_setting_drawer.setPadding(45, 0, 0, 0);
                item_navigation.setPadding(30, 0, 0, 0);
            }

        }
        return rowView;
    }

    public ArrayList<DrawerSetting> getValues() {
        return values;
    }

    public HashMap<String, DrawerSetting> getHash_values() {
        return hash_values;
    }
}

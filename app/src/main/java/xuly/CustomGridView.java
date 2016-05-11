package xuly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thud.thpt_dh.R;

/**
 * Created by KhanhNguyen on 10/13/2015.
 */
public class CustomGridView extends BaseAdapter
{
    private Context mContext;
    private final String[] TenMon;
    private final int[] Icon;
    private static LayoutInflater inflater = null;

    public CustomGridView(Context mainActivity, String[] TenMon, int[] Icon)
    {
        mContext = mainActivity;
        this.TenMon = TenMon;
        this.Icon = Icon;
        inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return TenMon.length;
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    public class Holder
    {
        TextView txtMon;
        ImageView imgMon;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View rowView;
        Holder holder = new Holder();

        rowView = inflater.inflate(R.layout.grid_single, null);
        holder.txtMon = (TextView) rowView.findViewById(R.id.txt_gridtext);//ten mon
        holder.imgMon = (ImageView) rowView.findViewById(R.id.img_gridtext);//icon mon

        holder.txtMon.setText(TenMon[position]);
        holder.imgMon.setImageResource(Icon[position]);


        return  rowView;
    }
}

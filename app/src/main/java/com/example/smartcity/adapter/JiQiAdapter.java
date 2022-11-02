package com.example.smartcity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.bean.JiQiData;

import java.util.ArrayList;

public class JiQiAdapter extends BaseAdapter {
    private ArrayList<JiQiData> arrayList;
    private Context context;
    private ImageView icon;
    private TextView tvName;
    private TextView suolue;
    private TextView station;
    private TextView time;


    public JiQiAdapter(ArrayList<JiQiData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoldre viewHoldre = null;
        if (convertView == null) {
            viewHoldre = new ViewHoldre();
            convertView = View.inflate(context, R.layout.jiqiitem, null);
            viewHoldre.icon = (ImageView) convertView.findViewById(R.id.icon);
            viewHoldre.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHoldre.suolue = (TextView) convertView.findViewById(R.id.suolue);
            viewHoldre.time = (TextView) convertView.findViewById(R.id.time);
            viewHoldre.station = (TextView) convertView.findViewById(R.id.station);
            convertView.setTag(viewHoldre);
        } else {
            viewHoldre = (ViewHoldre) convertView.getTag();
        }
        viewHoldre.suolue.setText(arrayList.get(position).getSuolue());
        viewHoldre.icon.setImageResource(arrayList.get(position).getIcon());
        viewHoldre.time.setText(arrayList.get(position).getTime());
        viewHoldre.tvName.setText(arrayList.get(position).getTv_name());
        viewHoldre.station.setText(arrayList.get(position).getStation());
        return convertView;
    }

    private void initView(View convertView) {


    }

    static class ViewHoldre {
        ImageView icon;
        TextView tvName;
        TextView suolue;
        TextView time;
        TextView station;
    }
}

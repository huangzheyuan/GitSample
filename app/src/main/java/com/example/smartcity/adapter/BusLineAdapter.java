package com.example.smartcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.bean.BusBean;
import com.example.smartcity.bean.BusStopBean;

import java.util.List;


public class BusLineAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<BusStopBean> mList;
    private BusStopBean bean;

    public BusLineAdapter(Context mContext, List<BusStopBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold vHold = null;
        if (convertView == null) {
            vHold = new ViewHold();
            convertView = inflater.inflate(R.layout.busline_item, null);
            vHold.zhandian =convertView
                    .findViewById(R.id.zhandian);

            convertView.setTag(vHold);
        } else {
            vHold = (ViewHold) convertView.getTag();
        }

        bean = mList.get(position);
        vHold.zhandian.setText(bean.getName());

        return convertView;
    }

    class ViewHold {
        private TextView zhandian;

    }
}
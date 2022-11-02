package com.example.smartcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.bean.BusWayBean;
import com.example.smartcity.bean.IllegaBean;
import com.example.smartcity.bean.IllegaData;

import java.util.List;


public class IllegaAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<IllegaBean> mList;
    private IllegaBean bean;

    public IllegaAdapter(Context mContext, List<IllegaBean> mList) {
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
            convertView = inflater.inflate(R.layout.illega_item, null);
            vHold.trafficOffence =convertView
                    .findViewById(R.id.trafficOffence);
            vHold.licencePlate = (TextView) convertView
                    .findViewById(R.id.licencePlate);
            vHold.illegalSites=convertView.findViewById(R.id.illegalSites);
            vHold.badTime=convertView.findViewById(R.id.badTime);
            vHold.catType=convertView.findViewById(R.id.catType);
            convertView.setTag(vHold);
        } else {
            vHold = (ViewHold) convertView.getTag();
        }

        bean = mList.get(position);


        vHold.trafficOffence.setText(bean.getDisposeState());
        vHold.licencePlate.setText("车牌号:"+bean.getLicencePlate());
        vHold.illegalSites.setText("地点:"+bean.getIllegalSites());
        vHold.badTime.setText("时间:"+bean.getBadTime());
        vHold.catType.setText("车辆类型:"+bean.getCatType());


        return convertView;
    }

    class ViewHold {
        private TextView trafficOffence;
        private TextView licencePlate;
        private TextView illegalSites,badTime,catType;

    }
}
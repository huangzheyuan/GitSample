package com.example.smartcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.bean.BusBean;
import com.example.smartcity.bean.BusOrderBean;

import java.util.List;


public class BusOrderAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<BusOrderBean> mList;
    private BusOrderBean bean;

    public BusOrderAdapter(Context mContext, List<BusOrderBean> mList) {
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
            convertView = inflater.inflate(R.layout.busorder_item, null);
            vHold.start =convertView
                    .findViewById(R.id.start);
            vHold.end =convertView
                    .findViewById(R.id.end);
            vHold.num =convertView
                    .findViewById(R.id.odernum);
            vHold.price =convertView
                    .findViewById(R.id.price);
            vHold.name =convertView
                    .findViewById(R.id.uname2);
            vHold.phone =convertView
                    .findViewById(R.id.phone2);
            vHold.xianlu =convertView
                    .findViewById(R.id.xianlu);

            convertView.setTag(vHold);
        } else {
            vHold = (ViewHold) convertView.getTag();
        }

        bean = mList.get(position);
        vHold.name.setText(bean.getUserName());
        vHold.start.setText(bean.getStart());
        vHold.end.setText(bean.getEnd());
        vHold.num.setText("订单编号:"+bean.getOrderNum());
        vHold.phone.setText(bean.getUserTel());
        vHold.price.setText("票价:"+bean.getPrice()+"元");
        vHold.xianlu.setText(bean.getPath());

        return convertView;
    }

    class ViewHold {
        private TextView start,end,num,price,name,phone,xianlu;

    }
}
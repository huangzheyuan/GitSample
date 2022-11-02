package com.example.smartcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.bean.HyuyueBean;
import com.example.smartcity.bean.KeshitypesBean;

import java.util.List;


public class HyuyueAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<HyuyueBean> mList;
    private HyuyueBean bean;

    public HyuyueAdapter(Context mContext, List<HyuyueBean> mList) {
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
            convertView = inflater.inflate(R.layout.type2_item, null);
            vHold.t1 =convertView
                    .findViewById(R.id.t1);
            vHold.t2 =convertView
                    .findViewById(R.id.t2);
            vHold.t3 =convertView
                    .findViewById(R.id.t3);
            vHold.t4 =convertView
                    .findViewById(R.id.t4);
            vHold.t5 =convertView
                    .findViewById(R.id.t5);

            convertView.setTag(vHold);
        } else {
            vHold = (ViewHold) convertView.getTag();
        }

        bean = mList.get(position);
        vHold.t1.setText(bean.getPatientName());
        vHold.t2.setText("科室:"+bean.getCategoryName());
        vHold.t3.setText("时间:"+bean.getReserveTime());
        vHold.t4.setText("费用:"+bean.getMoney()+"元");
        vHold.t5.setVisibility(View.GONE);
       // vHold.typeName.setText("费用:"+bean.getMoney()+"元");


        return convertView;
    }

    class ViewHold {
        private TextView t1,t2,t3,t4,t5;

    }
}
package com.example.smartcity.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.bean.HyuyueBean;
import com.example.smartcity.bean.JiuzhenrenBean;
import com.example.smartcity.view.HospitalInfoActivity;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;


public class JzrAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<JiuzhenrenBean> mList;
    private JiuzhenrenBean bean;

    public JzrAdapter(Context mContext, List<JiuzhenrenBean> mList) {
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
            convertView = inflater.inflate(R.layout.type1_item, null);
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
            vHold.guahao =convertView
                    .findViewById(R.id.guahao);
            vHold.update =convertView
                    .findViewById(R.id.update);

            convertView.setTag(vHold);
        } else {
            vHold = (ViewHold) convertView.getTag();
        }

        bean = mList.get(position);
        vHold.t1.setText(bean.getName());
        vHold.t2.setText("身份证:"+bean.getCardId());
        vHold.t3.setText("电话:"+bean.getTel());
        vHold.t4.setText("地址:"+bean.getAddress());
        //vHold.t5.setVisibility(View.GONE);
        vHold.t5.setText("出生日期:"+bean.getBirthday());


        return convertView;
    }

    class ViewHold {
        private TextView t1,t2,t3,t4,t5,guahao,update;

    }
}
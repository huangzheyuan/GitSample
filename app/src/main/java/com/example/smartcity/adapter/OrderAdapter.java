package com.example.smartcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.bean.BusBean;
import com.example.smartcity.bean.OrderBean;

import java.util.List;


public class OrderAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<OrderBean> mList;
    private OrderBean bean;

    public OrderAdapter(Context mContext, List<OrderBean> mList) {
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
            convertView = inflater.inflate(R.layout.item_order, null);

            vHold.textView_order_status = convertView.findViewById(R.id.textView_order_status);
            vHold.textView_pay_time = (TextView) convertView.findViewById(R.id.textView_pay_time);
            vHold.textView_name = convertView.findViewById(R.id.textView_name);
            vHold.textView_order_type_name = convertView.findViewById(R.id.textView_order_type_name);
            vHold.textView_amount = convertView.findViewById(R.id.textView_amount);

            convertView.setTag(vHold);
        } else {
            vHold = (ViewHold) convertView.getTag();
        }

        bean = mList.get(position);

        vHold.textView_order_status.setText("订单状态：" +bean.getOrderStatus());
        vHold.textView_pay_time.setText("支付时间：" +bean.getPayTime());
        vHold.textView_name.setText("商家：" +bean.getName());
        vHold.textView_order_type_name.setText("订单类型：" +bean.getOrderTypeName());
        vHold.textView_amount.setText("消费金额：" +bean.getAmount());

        return convertView;
    }

    class ViewHold {
        private TextView textView_order_status;
        private TextView textView_pay_time;
        private TextView textView_name;
        private TextView textView_order_type_name;
        private TextView textView_amount;
    }
}
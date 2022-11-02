package com.example.smartcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.bean.FeeslistBean;
import com.example.smartcity.bean.KeshitypesBean;

import java.util.List;


public class HtypesAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<KeshitypesBean> mList;
    private KeshitypesBean bean;

    public HtypesAdapter(Context mContext, List<KeshitypesBean> mList) {
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
            convertView = inflater.inflate(R.layout.fess_item, null);
            vHold.ownerName =convertView
                    .findViewById(R.id.ownerName);
            vHold.typeName = (TextView) convertView
                    .findViewById(R.id.typeName);
            vHold.liveName=convertView.findViewById(R.id.liveName);

            convertView.setTag(vHold);
        } else {
            vHold = (ViewHold) convertView.getTag();
        }

        bean = mList.get(position);
        vHold.ownerName.setText(bean.getCategoryName());
        vHold.typeName.setText("费用:"+bean.getMoney()+"元");
        vHold.liveName.setVisibility(View.GONE);

        return convertView;
    }

    class ViewHold {
        private TextView ownerName,typeName,liveName;

    }
}
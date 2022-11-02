package com.example.smartcity.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.bean.ParklotBean;
import com.example.smartcity.util.PicassoUtils;

import java.util.List;

public class ParklotAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<ParklotBean> mList;
    private ParklotBean bean;

    public ParklotAdapter(Context mContext, List<ParklotBean> mList) {
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
            convertView = inflater.inflate(R.layout.parklot_item, null);
            vHold.pimgUrl = convertView.findViewById(R.id.pimgUrl);
            vHold.parkName = convertView.findViewById(R.id.parkName);
            vHold.vacancy = convertView.findViewById(R.id.vacancy);
            vHold.priceCaps = convertView.findViewById(R.id.priceCaps);
            vHold.rates = convertView.findViewById(R.id.rates);
            vHold.distance = convertView.findViewById(R.id.distance);
            vHold.address = convertView.findViewById(R.id.address);
            vHold.allPark = convertView.findViewById(R.id.allPark);
            convertView.setTag(vHold);
        } else {
            vHold = (ViewHold) convertView.getTag();
        }

        bean = mList.get(position);
        vHold.parkName.setText(bean.getParkName());
        vHold.vacancy.setText("空缺:" + bean.getVacancy());
        vHold.parkName.setText(bean.getParkName());
        vHold.priceCaps.setText("价格上限:" + bean.getPriceCaps());
        vHold.rates.setText("价格:" + bean.getRates());
        vHold.distance.setText("距离:" + bean.getDistance());
        vHold.address.setText("地址:" + bean.getAddress());
        vHold.allPark.setText("所有空位:" + bean.getAllPark());

        if (!TextUtils.isEmpty(bean.getImgUrl())) {
            PicassoUtils.loadImageViewSize(mContext, Connectinfo.contexturl + bean.getImgUrl(), 150, 100, vHold.pimgUrl);
        } else {
            vHold.pimgUrl.setImageResource(R.mipmap.ic_launcher);
        }


        return convertView;
    }

    class ViewHold {
        private ImageView pimgUrl;
        private TextView parkName, vacancy, priceCaps, rates, distance, address, allPark;

    }
}
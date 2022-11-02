package com.example.smartcity.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.bean.HospitalListBean;
import com.example.smartcity.util.PicassoUtils;

import java.util.List;


public class HospitalListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<HospitalListBean> mList;
    private HospitalListBean bean;

    public HospitalListAdapter(Context mContext, List<HospitalListBean> mList) {
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
            convertView = inflater.inflate(R.layout.hospital_item, null);
            vHold.pic = convertView
                    .findViewById(R.id.hosPic);
            vHold.name = (TextView) convertView
                    .findViewById(R.id.hos_title);
            vHold.content = convertView.findViewById(R.id.hos_content);


            convertView.setTag(vHold);
        } else {
            vHold = (ViewHold) convertView.getTag();
        }

        bean = mList.get(position);
        vHold.name.setText(bean.getHospitalName());
        vHold.content.setText(Html.fromHtml(bean.getBrief()));
        PicassoUtils.loadImageViewSize(mContext, Connectinfo.contexturl + bean.getImgUrl(), 150, 100, vHold.pic);
        return convertView;
    }

    class ViewHold {
        private ImageView pic;
        private TextView name;
        private TextView content;

    }
}
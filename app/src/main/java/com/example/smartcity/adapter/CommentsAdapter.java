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
import com.example.smartcity.bean.CommentsBean;
import com.example.smartcity.util.PicassoUtils;

import java.util.List;


public class CommentsAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<CommentsBean> mList;
    private CommentsBean bean;

    public CommentsAdapter(Context mContext, List<CommentsBean> mList) {
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
            convertView = inflater.inflate(R.layout.comm_item, null);
            vHold.iv_url = (ImageView) convertView
                    .findViewById(R.id.iv_url);
            vHold.tv_title = (TextView) convertView
                    .findViewById(R.id.tv_title);
            vHold.tv_content=convertView.findViewById(R.id.tv_content);
            vHold.updateTime = (TextView) convertView.findViewById(R.id.updateTime);
            vHold.viewsNumber=convertView.findViewById(R.id.viewsNumber);
            convertView.setTag(vHold);
        } else {
            vHold = (ViewHold) convertView.getTag();
        }

        bean = mList.get(position);
        vHold.tv_title.setText(bean.getNewsTitle());
        vHold.tv_content.setText(bean.getContent());
        vHold.updateTime.setText("时间:"+bean.getCommentDate());
        //vHold.viewsNumber.setText("评论数量:"+bean.getViewsNumber());
//        if(!TextUtils.isEmpty(bean.getAvatar())){
//            PicassoUtils.loadImageViewSize(mContext, Connectinfo.contexturl+bean.getAvatar(),150,100, vHold.iv_url);
//        }else{
//            vHold.iv_url.setImageResource(R.mipmap.ic_launcher);
//        }

        return convertView;
    }

    class ViewHold {
        private ImageView iv_url;
        private TextView tv_title;
        private TextView tv_content;
        private TextView updateTime;
        private TextView viewsNumber;
    }
}
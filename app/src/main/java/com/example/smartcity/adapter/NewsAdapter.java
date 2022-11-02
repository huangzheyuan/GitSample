package com.example.smartcity.adapter;
import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ImageView;
import android.widget.TextView;


import com.example.smartcity.R;

import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.bean.NewBean;
import com.example.smartcity.bean.NewsData;
import com.example.smartcity.util.PicassoUtils;

import java.util.List;

import static android.content.ContentValues.TAG;


public class NewsAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<NewsData.RowsDTO> mList;
    private NewsData.RowsDTO bean;

    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    //    public NewsAdapter(Context mContext, List<NewBean> mList) {
//        this.mContext = mContext;
//        this.mList = mList;
//        inflater = (LayoutInflater) mContext
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
    public NewsAdapter(Context mContext, List<NewsData.RowsDTO> mList) {
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
            convertView = inflater.inflate(R.layout.news_item, null);
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
        vHold.tv_title.setText(bean.getTitle());
//        vHold.tv_content.setText(bean.getContent());
        vHold.tv_content.setText(bean.getContent());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            vHold.tv_content.setText(Html.fromHtml(bean.getContent(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            vHold.tv_content.setText(Html.fromHtml(bean.getContent()));
        }
        vHold.updateTime.setText("时间:"+bean.getUpdateTime());
        vHold.viewsNumber.setText("评论数量:"+bean.getCommentNum());
        if(!TextUtils.isEmpty(bean.getCover())){
            PicassoUtils.loadImageViewSize(mContext, Connectinfo.contexturl+bean.getCover(),150,100, vHold.iv_url);
        }else{
            vHold.iv_url.setImageResource(R.mipmap.ic_launcher);
        }

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
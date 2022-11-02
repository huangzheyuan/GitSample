package com.example.smartcity.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.smartcity.R;

import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.bean.NewBean;
import com.example.smartcity.bean.NewsData;
import com.example.smartcity.util.OkHttpUtils;

import com.example.smartcity.adapter.*;
import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class NewsFragmentbak extends Fragment {
    private android.widget.ListView ListView;
    private List<NewsData.RowsDTO> mList = new ArrayList<>();
    private NewsAdapter adapter;
    public NewsFragmentbak() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view= inflater.inflate(R.layout.fragment_news, container, false);
        ListView = view.findViewById(R.id.news_listview);
        getData();
        return view;
    }
    private void getData() {

        String params = "?pageNum=1&pageSize=10&pressCategory=48";
        OkHttpUtils okHttp = OkHttpUtils.getInstance();

        okHttp.syncJsonStringByURL(Connectinfo.newsurl, new OkHttpUtils.FuncJsonString() {
            private static final String TAG = "NewsActivity";

            @Override
            public void onResponse(String result) {
                Log.d(TAG, "onResponse: " + result);
                getJson(result);

            }
        });
    }
    private void getJson(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jArray = jsonObject.getJSONArray("rows");
            //我们的json格式是数组里面包含对象，所以直接拿数组再for循环解析即可
            //如果复杂样式{result:{list:[{1},{2},{3}]}}
            // 则代码如下:先获取对象，再获取result对象，在获取list数组，最后循环
            //  JSONObject jsonObject = new JSONObject(json);
            //JSONObject jsonresult = jsonObject.getJSONObject("result");
            // JSONArray jArray = jsonresult.getJSONArray("list");

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jb = (JSONObject) jArray.get(i);
                NewsData.RowsDTO bean = new NewsData.RowsDTO();
                bean.setTitle(jb.getString("title"));
//                bean.setContent(jb.getString("content"));
//                bean.setCreateTime(jb.getString("createTime"));
//                bean.setLikeNumber(Integer.parseInt(jb.getString("likeNumber")));
//                bean.setImgUrl(Connectinfo.contexturl + jb.getString("imgUrl"));

                mList.add(bean);

            }
            adapter = new NewsAdapter(getActivity(), mList);
            ListView.setAdapter(adapter);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

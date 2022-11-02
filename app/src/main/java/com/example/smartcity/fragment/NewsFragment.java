package com.example.smartcity.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.smartcity.R;
import com.example.smartcity.adapter.NewsAdapter;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.bean.CategoryBean;
import com.example.smartcity.bean.CategoryData;
import com.example.smartcity.bean.GuildData;
import com.example.smartcity.bean.NewsData;
import com.example.smartcity.bean.QueryBean;
import com.example.smartcity.bean.QueryData;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PicassoUtils;
import com.example.smartcity.view.NewsInfo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.example.smartcity.baseinfo.Const.newBeans;
import static com.example.smartcity.util.setListViewHeightBasedOnChildren.setListViewHeightBasedOnChildren;


public class NewsFragment extends Fragment {
    private ListView listView;
    private ImageView imageView, imageView2;
    private TextView textView, textView2;
    private View view;
    //todo 定义图片集合
    private List<String> list_img = new ArrayList<>();
    GuildData getgulids;
    //todo 查询 推荐服务
    private List<String> queryList_img = new ArrayList<>();
    List<QueryBean> queryList = new ArrayList<>();
    QueryData queryData;
    //todo 查询新闻分类
    List<CategoryBean> categoryList = new ArrayList<>();
    CategoryData categoryData;
    TextView newsCate1, newsCate2, newsCate3, newsCate4, newsCate5, newsCate6;
    TextView newsCate[] = new TextView[]{newsCate1, newsCate2, newsCate3, newsCate4, newsCate5, newsCate6};
    int newsCateId[] = new int[]{R.id.newsCate1, R.id.newsCate2, R.id.newsCate3, R.id.newsCate4, R.id.newsCate5, R.id.newsCate6};
    //todo 查询所属分类下的新闻列表
    private NewsAdapter adapter;
    List<NewsData.RowsDTO> ls = new ArrayList<>();
    List<NewsData.RowsDTO> ls2 = new ArrayList<>();
    NewsData getnews;
    //todo 查询 推荐服务 图片
    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9, img10;
    ImageView images[] = new ImageView[]{img1, img2, img3, img4, img5, img6, img7, img8, img9, img10};
    int imagesId[] = new int[]{R.id.img0, R.id.img1, R.id.img3, R.id.img4, R.id.img5, R.id.img6, R.id.img6, R.id.img7, R.id.img9, R.id.img10};
    //todo 查询 推荐服务 文本
    TextView text1, text2, text3, text4, text5, text6, text7, text8, text9, text10;
    TextView texts[] = new TextView[]{text1, text2, text3, text4, text5, text6, text7, text8, text9, text10};
    int textId[] = new int[]{R.id.text0, R.id.text1, R.id.text3, R.id.text4, R.id.text5, R.id.text8, R.id.text6, R.id.text7, R.id.text9, R.id.text10};

    int r = 0x10;
    int g = 0x86;
    int b = 0xf4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.newspage, container, false);

        init();
        getData();
        return view;
    }

    private void init() {
        imageView = view.findViewById(R.id.image_hot1);
        imageView2 = view.findViewById(R.id.image_hot2);
        textView = view.findViewById(R.id.textView_hot1);
        textView2 = view.findViewById(R.id.textView_hot2);
        listView = view.findViewById(R.id.news_list);
        for (int i = 0; i < 6; i++) {
            newsCate[i] = view.findViewById(newsCateId[i]);
        }
    }


    private void initNewsListview() {
        //todo 查询新闻分类

        OkHttpUtils okHttp3 = OkHttpUtils.getInstance();
        okHttp3.syncJsonStringByURL(Connectinfo.categoryurl, new OkHttpUtils.FuncJsonString() {

            @Override
            public void onResponse(String result) {

                categoryData = new Gson().fromJson(result, CategoryData.class);
                int i = 0;
                for (; i < categoryData.getData().size(); i++) {
                    String title = categoryData.getData().get(i).getName();

                    int type = categoryData.getData().get(i).getId();

                    String typeName = categoryData.getData().get(i).getName();
                    newsCate[i].setText(title);
                    newsCate[i].setVisibility(View.VISIBLE);

                    newsCate[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //显示第i类新闻列表
                            TextView textView = (TextView) v;
                            for (TextView tv : newsCate
                            ) {

                                if (textView == tv) {
                                    tv.setTextColor(Color.rgb(r, g, b));
                                } else {
                                    tv.setTextColor(Color.GRAY);
                                }
                            }

                            initNewsListview2(typeName, type);

                        }
                    });

                }


                //显示第1个分类新闻列表
                if (categoryData.getData().size() > 0) {
                    int type = categoryData.getData().get(0).getId();
                    String typeName = categoryData.getData().get(0).getName();
                    initNewsListview2(typeName, type);
                }


            }
        });

    }

    private void getData() {

        //todo 查询所属分类下的新闻列表  最下方

        initNewsListview();
        //todo 查询专题新闻列表 ---单独两个
        OkHttpUtils okHttp5 = OkHttpUtils.getInstance();
        okHttp5.syncJsonStringByURL(Connectinfo.newsurl + "?hot=Y", new OkHttpUtils.FuncJsonString() {

            @Override
            public void onResponse(String result) {

                getnews = new Gson().fromJson(result, NewsData.class);
                initNewsBox(getnews.getRows());

            }
        });
    }

    private void initNewsListview2(String typeName, int type) {

        OkHttpUtils okHttp5 = OkHttpUtils.getInstance();
        okHttp5.syncJsonStringByURL(Connectinfo.newsurl + "?type=" + type, new OkHttpUtils.FuncJsonString() {


            @Override
            public void onResponse(String result) {
                getnews = new Gson().fromJson(result, NewsData.class);

                adapter = new NewsAdapter(getContext(), getnews.getRows());

                listView.setAdapter(adapter);
                setListViewHeightBasedOnChildren(listView);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        newBeans = getnews.getRows().get(i);
                        Intent intent = new Intent(getActivity(), NewsInfo.class);
                        startActivity(intent);

                    }
                });
            }
        });

    }

//    private void initNewsListview() {
//        //todo 查询新闻分类
//        OkHttpUtils okHttp3 = OkHttpUtils.getInstance();
//        okHttp3.syncJsonStringByURL(Connectinfo.categoryurl, new OkHttpUtils.FuncJsonString() {
//
//            @Override
//            public void onResponse(String result) {
////                categoryData = new Gson().fromJson(result, CategoryData.class);
////                for (int i = 0; i < categoryData.getData().size(); i++) {
////                    newsCate[i] = view.findViewById(newsCateId[i]);
////                    newsCate[i].setText(categoryData.getData().get(i).getName());
////                    if (i == 0) {
////                        ls2.clear();
////                        for (int s = 0; s < ls.size() - 1; s++) {
////                            if (ls.get(s).getType() != null) {
////                                if (ls.get(s).getType().equals(categoryData.getData().get(0).getName())) {
////                                    ls2.add(ls.get(s));
////                                }
////                            }
////                        }
////                        initNewsListview2();
////                    }
////                    int finalI = i;
////                    newsCate[i].setOnClickListener(new View.OnClickListener() {
////                        @Override
////                        public void onClick(View view) {
////                            ls2.clear();
////                            for (int s = 0; s < ls.size() - 1; s++) {
////                                if (ls.get(s).getType() != null) {
////                                    if (ls.get(s).getType().equals(categoryData.getData().get(finalI).getName())) {
////                                        ls2.add(ls.get(s));
////                                    }
////                                }
////                            }
////                            initNewsListview2();
////                        }
////                    });
////                }
//                categoryData = new Gson().fromJson(result, CategoryData.class);
//                int i = 0;
//                for (; i < categoryData.getData().size(); i++) {
//                    String title = categoryData.getData().get(i).getName();
//
//                    int type = categoryData.getData().get(i).getId();
//
//                    String typeName = categoryData.getData().get(i).getName();
//                    newsCate[i].setText(title);
//                    newsCate[i].setVisibility(View.VISIBLE);
//
//                    newsCate[i].setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            //显示第i类新闻列表
//                            TextView textView = (TextView) v;
//                            for (TextView tv : newsCate
//                            ) {
//
//                                if (textView == tv) {
//                                    tv.setTextColor(Color.rgb(r, g, b));
//                                } else {
//                                    tv.setTextColor(Color.GRAY);
//                                }
//                            }
//
//                            initNewsListview2(typeName, type);
//
//                        }
//                    });
//
//                }
//
//
//                //显示第1个分类新闻列表
//                if (categoryData.getData().size() > 0) {
//                    int type = categoryData.getData().get(0).getId();
//                    String typeName = categoryData.getData().get(0).getName();
//                    initNewsListview2(typeName, type);
//                }
//
//            }
//        });
//
//    }

    private void initNewsBox(List<NewsData.RowsDTO> hotNews) {
        PicassoUtils.loadImageViewCrop2(getActivity(), Connectinfo.contexturl + hotNews.get(0).getCover(), imageView);
        PicassoUtils.loadImageViewCrop2(getActivity(), Connectinfo.contexturl + hotNews.get(1).getCover(), imageView2);
        textView.setText(Html.fromHtml( hotNews.get(0).getContent()));
        textView2.setText(Html.fromHtml( hotNews.get(1).getContent()));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newBeans = hotNews.get(0);
                Intent intent = new Intent(getActivity(), NewsInfo.class);
                startActivity(intent);
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newBeans = hotNews.get(1);
                Intent intent = new Intent(getActivity(), NewsInfo.class);
                startActivity(intent);
            }
        });
    }


}

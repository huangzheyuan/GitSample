package com.example.smartcity.fragment;


import static com.example.smartcity.baseinfo.Const.newBeans;
import static com.example.smartcity.util.setListViewHeightBasedOnChildren.setListViewHeightBasedOnChildren;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.R;
import com.example.smartcity.adapter.NewsAdapter;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.baseinfo.Const;
import com.example.smartcity.bean.CategoryBean;
import com.example.smartcity.bean.CategoryData;
import com.example.smartcity.bean.GuildData;
import com.example.smartcity.bean.NewsData;
import com.example.smartcity.bean.QueryBean;
import com.example.smartcity.bean.QueryData;
import com.example.smartcity.fragment.adapters.RecommendationAdapter;
import com.example.smartcity.fragment.interf.OnRecommendationClicked;
import com.example.smartcity.util.BannerLoader;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PicassoUtils;
import com.example.smartcity.view.HospitalActivity;
import com.example.smartcity.view.IllegaActivity;
import com.example.smartcity.view.LinesActivity;
import com.example.smartcity.view.LoginActivity;
import com.example.smartcity.view.MainActivity;
import com.example.smartcity.view.NewsInfo;
import com.example.smartcity.view.ParklotActivity;
import com.example.smartcity.view.car.movement.CarMovementActivity;
import com.example.smartcity.view.car.query.CheckCarActivity;
import com.example.smartcity.view.data.DataAnalysisActivity;
import com.example.smartcity.view.house.HouseActivity;
import com.example.smartcity.view.job.JobMainActivity;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//todo ------------------new 2020/12/31-----------

public class HomeFragment extends Fragment implements OnBannerListener, OnRecommendationClicked {
    private Banner banner;
    private ListView listView;
    private ImageView imageView_hot1, imageView_hot2;
    private TextView textView_hot1, textView_hot2;
    private View view;
    private ScrollView scrollView;

    private RecyclerView recycleView;
    private RecommendationAdapter recommendationAdapter;

    private Map<String, Class> activityMap;
    //todo 定义图片集合
    private List<String> list_img = new ArrayList<>();
    GuildData getgulids;
    //todo 查询 推荐服务
//    private List<String> queryList_img = new ArrayList<>();
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
    //    List<NewsData.RowsDTO> ls = new ArrayList<>();
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

    int selectedForeColor = Color.rgb(0x10, 0x86, 0xF4);
    int r = 0x10;
    int g = 0x86;
    int b = 0xf4;

    private TextView recommendationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        getData();
        return view;
    }

    private void init() {

        activityMap = new HashMap<>();

        activityMap.put("城市地铁", LinesActivity.class);
        activityMap.put("找工作", JobMainActivity.class);
//        activityMap.put("生活缴费", ElectricityFeeActivity.class);
        activityMap.put("看电影", CarMovementActivity.class);//借用入口
        activityMap.put("活动管理", CheckCarActivity.class);//借用入口
//        activityMap.put("智慧巴士", BusOrderActivity.class);
        activityMap.put("门诊预约", HospitalActivity.class);
        activityMap.put("找房子", HouseActivity.class);
        activityMap.put("找工作", JobMainActivity.class);
        activityMap.put("停哪儿", ParklotActivity.class);
        activityMap.put("智慧交管", IllegaActivity.class);
        activityMap.put("数据分析", DataAnalysisActivity.class);

//        activityClassMap.put("外卖订单",);

        imageView_hot1 = view.findViewById(R.id.image_hot1);
        imageView_hot2 = view.findViewById(R.id.image_hot2);
        textView_hot1 = view.findViewById(R.id.textView_hot1);
        textView_hot2 = view.findViewById(R.id.textView_hot2);
        listView = view.findViewById(R.id.news_list);
        banner = view.findViewById(R.id.homeBanner);

        scrollView = view.findViewById(R.id.scrollView);

        recycleView = view.findViewById(R.id.recyclerView_recommendation);

        recommendationView = view.findViewById(R.id.textView_recommendation);

        for (int i = 0; i < 6; i++) {
            newsCate[i] = view.findViewById(newsCateId[i]);
        }
        newsCate[0].setTextColor(Color.rgb(r, g, b));
//        newsCate[0].setTextColor(selectedForeColor);


        recommendationAdapter = new RecommendationAdapter();

        RecyclerView.LayoutManager layoutManager = null;
        if (isTabletDevice(getContext())) {

            layoutManager = new GridLayoutManager(getActivity(), 7);
        } else {
            layoutManager = new GridLayoutManager(getActivity(), 5);
        }

        recycleView.setLayoutManager(layoutManager);

        recommendationAdapter.setClicked(HomeFragment.this);

    }

    private boolean isTabletDevice(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    private void getData() {
        //todo 主页轮播图
        String params = "?pageNum=1&pageSize=8&type=2";
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        okHttp.syncJsonStringByURL(Connectinfo.mainrotationurl + params, new OkHttpUtils.FuncJsonString() {
            private static final String TAG = "HomeFragment";

            @Override
            public void onResponse(String result) {
                Log.d(TAG, "onResponse: " + result);

                getgulids = new Gson().fromJson(result, GuildData.class);

                for (int i = 0; i < getgulids.getRows().size(); i++) {
//                    list_img.add(Connectinfo.contexturl + getgulids.getRows().get(i).getImgUrl());
                    list_img.add(Connectinfo.contexturl + getgulids.getRows().get(i).getAdvImg());
                    Log.d(TAG, "onResponse: getguilds---" + list_img.get(i));
                }
                initView();

            }
        });

        //todo 查询 推荐服务 XXX
        String params2 = "?isRecommend=N";
        OkHttpUtils okHttp2 = OkHttpUtils.getInstance();
        okHttp2.syncJsonStringByURL(Connectinfo.all_services_url + params2, new OkHttpUtils.FuncJsonString() {

            @Override
            public void onResponse(String result) {
                queryData = new Gson().fromJson(result, QueryData.class);
                recycleView.setAdapter(recommendationAdapter);
                recommendationView.setVisibility(queryData.getRows().size() == 0 ? View.GONE : View.VISIBLE);
                recommendationAdapter.setQueryData(queryData);
            }
        });

        //todo 新闻分类
        initNewsListview();

        //todo 查询所属分类下的新闻列表  最下方
//        String params4 = "?pageNum=1&pageSize=10&pressCategory=37";
//        OkHttpUtils okHttp4 = OkHttpUtils.getInstance();
//        okHttp4.syncJsonStringByURL(Connectinfo.newsurl + params4, new OkHttpUtils.FuncJsonString() {
//            private static final String TAG = "HomeFragment";
//
//            @Override
//            public void onResponse(String result) {
//                Log.d(TAG, "onResponse: " + result);
//                getnews = new Gson().fromJson(result, NewsData.class);
//                for (int i = 0; i < getnews.getRows().size(); i++) {
//                    ls.add(getnews.getRows().get(i));
//                }
//                initNewsListview();
//
//            }
//        });

        //todo 查询专题新闻列表 ---单独两个 XXX
        OkHttpUtils okHttp5 = OkHttpUtils.getInstance();
        okHttp5.syncJsonStringByURL(Connectinfo.newsurl + "?hot=Y", new OkHttpUtils.FuncJsonString() {

            @Override
            public void onResponse(String result) {

                getnews = new Gson().fromJson(result, NewsData.class);
                initHotNewsBox(getnews.getRows());

            }
        });
    }

    private void initNewsListview2() {
        adapter = new NewsAdapter(getContext(), ls2);
        listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                newBeans = ls2.get(i);
                Intent intent = new Intent(getActivity(), NewsInfo.class);
                startActivity(intent);

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

    private void initHotNewsBox(List<NewsData.RowsDTO> hotNews) {
        PicassoUtils.loadImageViewCrop2(getActivity(), Connectinfo.contexturl + hotNews.get(0).getCover(), imageView_hot1);
        PicassoUtils.loadImageViewCrop2(getActivity(), Connectinfo.contexturl + hotNews.get(1).getCover(), imageView_hot2);
        textView_hot1.setText(Html.fromHtml(hotNews.get(0).getContent()));
        textView_hot2.setText(Html.fromHtml(hotNews.get(1).getContent()));
        imageView_hot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newBeans = hotNews.get(0);
                Intent intent = new Intent(getActivity(), NewsInfo.class);
                startActivity(intent);
            }
        });
        imageView_hot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newBeans = hotNews.get(1);
                Intent intent = new Intent(getActivity(), NewsInfo.class);
                startActivity(intent);
            }
        });

//        PicassoUtils.loadImageViewCrop2(getActivity(), Connectinfo.contexturl + ls.get(0).getImgUrl(), imageView);
//        PicassoUtils.loadImageViewCrop2(getActivity(), Connectinfo.contexturl + ls.get(1).getImgUrl(), imageView2);
//        textView.setText(ls.get(0).getContent());
//        textView2.setText(ls.get(1).getContent());
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                newBeans = ls.get(0);
//                Intent intent = new Intent(getActivity(), NewsInfo.class);
//                startActivity(intent);
//            }
//        });
//        imageView2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                newBeans = ls.get(1);
//                Intent intent = new Intent(getActivity(), NewsInfo.class);
//                startActivity(intent);
//            }
//        });
    }

    private void initView() {
        //todo 轮播图
        banner.setImageLoader(new BannerLoader());
        banner.setImages(list_img);
        banner.setBannerAnimation(Transformer.Default);
        banner.setDelayTime(2000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER)
                .setOnBannerListener(this)
                .start();

    }

    private void initQuery() {

        for (int i = 0; i < images.length; i++) {
            images[i] = view.findViewById(imagesId[i]);
            texts[i] = view.findViewById(textId[i]);
            PicassoUtils.loadImageViewCrop2(getActivity(), queryData.getRows().get(i).getImgUrl(), images[i]);
            texts[i].setText(queryData.getRows().get(i).getServiceName());
            //todo 点击事件
            int finalI = i;
            images[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (finalI) {
                        case 0:
                            //城市地铁
//                            Intent intent = new Intent(getActivity(), SubWayActivity.class);
//                            startActivity(intent);
                            break;
                        case 1:
                            //智慧巴士
//                            Intent intent2 = new Intent(getActivity(), BusActivity.class);
//                            startActivity(intent2);
                            break;
                        case 2:
                            //门诊预约
                            Intent intent3 = new Intent(getActivity(), HospitalActivity.class);
                            startActivity(intent3);
                            break;
                        case 3:
                            break;
                        case 4:
                            //违章查询
                            Intent intent5 = new Intent(getActivity(), IllegaActivity.class);
                            startActivity(intent5);
                            break;
                        case 5:
                            //停车场
                            Intent intent7 = new Intent(getActivity(), ParklotActivity.class);
                            startActivity(intent7);
                            Toast.makeText(getActivity(), String.valueOf(finalI), Toast.LENGTH_LONG).show();
                            break;
                        case 6:
                            //Toast.makeText(getActivity(), String.valueOf(finalI), Toast.LENGTH_LONG).show();
                            break;
                        case 7:
                            //Toast.makeText(getActivity(), String.valueOf(finalI), Toast.LENGTH_LONG).show();
                            break;
                        case 8:
                            //智慧巴士
//                            Intent intent9 = new Intent(getActivity(), BusActivity.class);
//                            startActivity(intent9);
                            break;
                        case 9:
                            Toast.makeText(getActivity(), "23333333333333333", Toast.LENGTH_LONG).show();
                            break;
                        default:
                            break;
                    }


                }
            });
        }

    }


    //轮播图的监听方法
    @Override
    public void OnBannerClick(int position) {
        Log.i("tag", "你点了第" + position + "张轮播图");
//        Intent intent=new Intent(this, getActivity().class);
//        startActivity(intent);

    }


    @Override
    public void onClick(String title) {

        if (title.equals("更多")) {

            ((MainActivity) getActivity()).select(1);
        } else {

            if (Const.tokens == null) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            } else {

                if (title.equals("找工作") || title.equals("找房子") || title.equals("门诊预约") || title.equals("停哪儿") || title.equals("智慧交管") || title.equals("活动管理") || title.equals("活动管理") || title.equals("数据分析")) {

                    Intent intent = new Intent(getActivity(), activityMap.get(title));
                    startActivity(intent);
                } else {

                    Toast.makeText(getActivity(), title + " 即将上线", Toast.LENGTH_SHORT).show();
                }

            }


        }
    }

}

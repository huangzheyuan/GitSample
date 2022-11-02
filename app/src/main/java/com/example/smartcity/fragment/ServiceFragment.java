package com.example.smartcity.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.bean.GuildData;
import com.example.smartcity.bean.QueryBean;
import com.example.smartcity.bean.QueryData;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PicassoUtils;
import com.example.smartcity.view.HospitalActivity;
import com.example.smartcity.view.IllegaActivity;
import com.example.smartcity.view.ParklotActivity;
import com.example.smartcity.view.car.movement.CarMovementActivity;
import com.example.smartcity.view.car.query.CheckCarActivity;
import com.example.smartcity.view.job.JobMainActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.example.smartcity.baseinfo.Connectinfo.queryyiurl;


public class ServiceFragment extends Fragment {
    //todo 查询 推荐服务
//    private List<String> queryList_img = new ArrayList<>();
    List<QueryBean> queryList = new ArrayList<>();
    QueryData queryData;
    //todo 查询 推荐服务 图片
    ImageView images[] = new ImageView[11];
    int imagesId[] = new int[]{R.id.img0, R.id.img1, R.id.img2, R.id.img3, R.id.img4, R.id.img5, R.id.img6, R.id.img7, R.id.img8, R.id.img9, R.id.img10};
    //todo 查询 推荐服务 文本
    TextView texts[] = new TextView[11];
    int textId[] = new int[]{R.id.text0, R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6, R.id.text7, R.id.text8, R.id.text9, R.id.text10};
    //todo 定义图片集合
    private List<String> list_img = new ArrayList<>();
    GuildData getgulids;
    //布局
//    LinearLayout ft1, ft2, ft3;
//    LinearLayout ft[] = new LinearLayout[]{ft1, ft2, ft3};
    LinearLayout ft[] = new LinearLayout[3];

    int ftid[] = new int[]{R.id.ft1, R.id.ft2, R.id.ft3};
    //切换
    TextView te1, te2, te3;
    TextView te[] = new TextView[]{te1, te2, te3};
    int teid[] = new int[]{R.id.te1, R.id.te2, R.id.te3};
    View view;
    //

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_search, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);


        view.findViewById(R.id.img6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getActivity(), HouseActivity.class));
            }
        });
        view.findViewById(R.id.img7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), JobMainActivity.class));
            }
        });
        view.findViewById(R.id.img9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CheckCarActivity.class));
            }
        });

        view.findViewById(R.id.img10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CarMovementActivity.class));
            }
        });

    }

    private void init(View view) {


        for (int i = 0; i < ft.length; i++) {
            ft[i] = view.findViewById(ftid[i]);
            te[i] = view.findViewById(teid[i]);
            int finalI = i;
            te[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for (int i = 0; i < ft.length; i++) {

                        if (finalI == i) {
                            te[i].setBackgroundResource(R.color.baise);
                            ft[i].setVisibility(View.VISIBLE);
                        } else {
                            te[i].setBackgroundResource(R.color.huises);
                            ft[i].setVisibility(View.GONE);
                        }
                    }
                }
            });
        }

        for (int i = 0; i < images.length; i++) {

            images[i] = view.findViewById(imagesId[i]);
            texts[i] = view.findViewById(textId[i]);
        }
        getdata();
    }

    private void getdata() {
        //todo 查询 推荐服务
        String params2 = "?isRecommend=N";
        OkHttpUtils okHttp2 = OkHttpUtils.getInstance();
        okHttp2.syncJsonStringByURL(Connectinfo.all_services_url + params2, new OkHttpUtils.FuncJsonString() {

            @Override
            public void onResponse(String result) {
                queryData = new Gson().fromJson(result, QueryData.class);
                initQuery();
            }
        });

        //todo 查询服务一级分类
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        okHttp.syncJsonStringByURL(queryyiurl, new OkHttpUtils.FuncJsonString() {
            private static final String TAG = "onetype";

            @Override
            public void onResponse(String result) {
                Log.d(TAG, "onResponse: " + result);

//                TextView t=view.findViewById(R.id.tt2);
//                t.setText("233333");
//                t.setText(result);

            }
        });


    }

    private void initQuery() {

        int i0 = 0, i1 = 3, i2 = 6;
        int index;

        for (int i = 0; i < queryData.getRows().size(); i++) {

            QueryBean queryBean = queryData.getRows().get(i);

            if (queryBean.getServiceType().equals("生活服务")) {

                index = i0;
                i0++;
            } else if (queryBean.getServiceType().equals("便民服务")) {
                index = i1;
                i1++;
            } else {
                index = i2;
                i2++;
            }
            texts[index].setText(queryBean.getServiceName());
            PicassoUtils.loadImageViewCrop2(getActivity(), Connectinfo.contexturl +  queryData.getRows().get(i).getImgUrl(), images[i]);

        }
//
//        for (int i = 0; i < images.length; i++) {
//
////            PicassoUtils.loadImageViewCrop2(getActivity(), queryList_img.get(i), images[i]);
//            texts[i].setText(queryData.getRows().get(i).getServiceName());
//            //todo 点击事件
//            int finalI = i;
//            images[i].setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    switch (finalI) {
//                        case 0:
//                            Intent intent = new Intent(getActivity(), SubWayActivity.class);
//                            startActivity(intent);
//                            break;
//                        case 1:
////                            Intent intent2 = new Intent(getActivity(), BusActivity.class);
////                            startActivity(intent2);
//                            break;
//                        case 2:
//                            Intent intent3 = new Intent(getActivity(), JobMainActivity.class);
//                            startActivity(intent3);
//                            break;
//                        case 3:
//                            Intent intent4 = new Intent(getActivity(), HospitalActivity.class);
//                            startActivity(intent4);
//                            break;
//                        case 4:
//                            break;
//                        case 5:
//                            Intent intent7 = new Intent(getActivity(), IllegaActivity.class);
//                            startActivity(intent7);
//                            break;
//                        case 6:
//                            startActivity(new Intent(getActivity(), ParklotActivity.class));
//                            break;
//                        case 7:
//                            //startActivity(new Intent(getActivity(), HouseActivity.class));
//                            break;
//                        case 8:
////                            Intent intent9 = new Intent(getActivity(), BusActivity.class);
////                            startActivity(intent9);
//                            break;
//                        case 9:
//                            startActivity(new Intent(getActivity(), CheckCarActivity.class));
//                            break;
//                        default:
//
//                            break;
//                    }
//
//
//                }
//            });
//        }

    }
}

 package com.example.smartcity.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.util.BannerLoader;
import com.example.smartcity.view.HistoryActivity;
import com.example.smartcity.view.MainActivity;
import com.example.smartcity.view.YuliuActivity;
import com.example.smartcity.view.ZhanshiActivity;
import com.example.smartcity.view.huishoujiActivity;
import com.example.smartcity.view.shangmenhuishou;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;


public class EnviromentFragment extends Fragment implements OnBannerListener, View.OnClickListener {
    View view;
    private Banner banner;
    LinearLayout order,display,history,liu,huishouji;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_sort, container, false);
        //todo 初始化 banner
        initBanner();
        init();
        return view;
    }

    private void init() {
//        TextView t=view.findViewById(R.id.headerTitle);
//        t.setText("预约上门回收垃圾");
        order=view.findViewById(R.id.order);
        order.setOnClickListener(this::onClick);
        display=view.findViewById(R.id.display);
        display.setOnClickListener(this::onClick);

        history=view.findViewById(R.id.history);
        history.setOnClickListener(this::onClick);
        liu=view.findViewById(R.id.liu);
        liu.setOnClickListener(this::onClick);
        huishouji=view.findViewById(R.id.jiqi);
        huishouji.setOnClickListener(this::onClick);
    }

    private void initBanner() {
        banner = (Banner) view.findViewById(R.id.vf);
        //设置图片加载器
        banner.setImageLoader(new BannerLoader());
        //显示图片
        List images = new ArrayList();
        images.add(R.drawable.h1);
        images.add(R.drawable.h2);
        images.add(R.drawable.h3);
        images.add(R.drawable.h4);
        images.add(R.drawable.h5);
        banner.setImages(images);
        banner.setBannerAnimation(Transformer.Default);

        banner.setDelayTime(2000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();
    }

    //轮播图的监听方法
    @Override
    public void OnBannerClick(int position) {
        Log.i("tag", "你点了第" + position + "张轮播图");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id. display:
                Intent intent=new Intent(getActivity(), ZhanshiActivity.class);
                startActivity(intent);
                break;
            case R.id.order:
                Intent intent2=new Intent(getActivity(), shangmenhuishou.class);
                startActivity(intent2);
                break;
            case R.id.history:
                Intent intent3=new Intent(getActivity(), HistoryActivity.class);
                startActivity(intent3);
                break;
            case R.id.liu:
                Intent intent4=new Intent(getActivity(), YuliuActivity.class);
                startActivity(intent4);
                break;
            case R.id.jiqi:
                Intent intent5=new Intent(getActivity(), huishoujiActivity.class);
                startActivity(intent5);
                break;

            default:
                break;
        }
    }
}

package com.example.smartcity.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.example.smartcity.R;
import com.example.smartcity.util.BannerLoader;
import com.example.smartcity.view.DangjiandongtaiActivity;
import com.example.smartcity.view.DangyuandongtaiinfoActivity;
import com.example.smartcity.view.JianyanxianceActivity;
import com.example.smartcity.view.JubaoActivity;
import com.example.smartcity.view.LearnActivity;
import com.example.smartcity.view.SuishoupaiActivity;
import com.example.smartcity.view.ZuzhihuodongActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.Collections;


public class DangjianFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, OnBannerListener, View.OnClickListener {

    View view;
    Banner banner;
    LinearLayout dongtai,ls1,ls2,learn,jyxc,zuzhihuodong,jubao,suishoupai;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_dangjian, container, false);
        init();
        initView();
        return view;
    }

    private void init() {
        banner=view.findViewById(R.id.vf);
        dongtai=view.findViewById(R.id.dongtai);
        dongtai.setOnClickListener(this::onClick);
        ls1=view.findViewById(R.id.ls1);
        ls1.setOnClickListener(this::onClick);
        ls2=view.findViewById(R.id.ls2);
        ls2.setOnClickListener(this::onClick);
        learn=view.findViewById(R.id.learn);
        learn.setOnClickListener(this::onClick);
        jyxc=view.findViewById(R.id.jyxc);
        jyxc.setOnClickListener(this::onClick);
        zuzhihuodong=view.findViewById(R.id.zuzhihuodong);
        zuzhihuodong.setOnClickListener(this::onClick);
        jubao=view.findViewById(R.id.jubao);
        jubao.setOnClickListener(this::onClick);
        suishoupai=view.findViewById(R.id.suishoupai);
        suishoupai.setOnClickListener(this::onClick);
        getdata();
    }
    private void initView() {
        //todo 轮播图
        banner.setImageLoader(new BannerLoader());
        banner.setImages(Collections.singletonList(R.drawable.huodong1));
        banner.setBannerAnimation(Transformer.Default);
        banner.setDelayTime(2000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER)
                .setOnBannerListener(this)
                .start();

    }
    private void getdata() {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

    }

    @Override
    public void OnBannerClick(int position) {

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.dongtai :
                Intent intent=new Intent(getActivity(), DangyuandongtaiinfoActivity.class);
                startActivity(intent);
                break;
            case R.id.learn :
                Intent intent4=new Intent(getActivity(), LearnActivity.class);
                startActivity(intent4);
                break;
            case R.id.ls1 :
                Intent intent2=new Intent(getActivity(), DangjiandongtaiActivity.class);
                startActivity(intent2);
                break;
            case R.id.ls2 :
                Intent intent3=new Intent(getActivity(),DangjiandongtaiActivity.class);
                startActivity(intent3);
                break;
            case R.id.jyxc :
                Intent intent5=new Intent(getActivity(), JianyanxianceActivity.class);
                startActivity(intent5);
                break;
            case R.id.zuzhihuodong :
                Intent intent6=new Intent(getActivity(), ZuzhihuodongActivity.class);
                startActivity(intent6);
                break;
            case R.id.jubao :
                Intent intent7=new Intent(getActivity(), JubaoActivity.class);
                startActivity(intent7);
                break;
            case R.id.suishoupai :
                Intent intent8=new Intent(getActivity(), SuishoupaiActivity.class);
                startActivity(intent8);
                break;
            default :
                break;
        }
    }
}

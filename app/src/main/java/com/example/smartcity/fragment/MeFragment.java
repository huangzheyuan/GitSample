package com.example.smartcity.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.baseinfo.Const;
import com.example.smartcity.otherview.GetInfoActivity;
import com.example.smartcity.util.PrefStore;
import com.example.smartcity.view.BusOrderActivity;
import com.example.smartcity.view.ChangePsswordActivity;
import com.example.smartcity.view.FeedbackActivity;
import com.example.smartcity.view.LoginActivity;
import com.example.smartcity.view.OrderListActivity;
import com.example.smartcity.view.PersonalinformationActivity;

import static com.example.smartcity.baseinfo.Const.userid;


public class MeFragment extends Fragment implements View.OnClickListener {
    View view;

    private TextView pr1, pr2, pr3, pr4, pr5;
    private Button exit;
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_me, container, false);
        init();
        return view;
    }

    private void init() {

        pr5 = view.findViewById(R.id.textView3);
        PrefStore prefStore = PrefStore.getInstance(getActivity());

        if (userid >= 0) {

            String username = prefStore.getPref("username", "");
            pr5.setText(username);
        }


        imageView = view.findViewById(R.id.imageView);
        pr1 = view.findViewById(R.id.pr1);
        pr2 = view.findViewById(R.id.pr2);
        pr3 = view.findViewById(R.id.pr3);
        pr4 = view.findViewById(R.id.pr4);
        exit = view.findViewById(R.id.exit);
        pr1.setOnClickListener(this);
        pr2.setOnClickListener(this);
        pr3.setOnClickListener(this);
        pr4.setOnClickListener(this);
        exit.setOnClickListener(this);



        String text;
        if (Const.tokens != null) {

            String url = prefStore.getPref("avater", "");
//            String avatarUrl = url.replace("http://124.93.196.45", "http://124.93.196.45:10001/prod-api");
//            Glide.with(getActivity()).load(avatarUrl).into(imageView);
            Glide.with(getActivity()).load("http://124.93.196.45:10001/prod-api" + url).into(imageView);
            text = "退出";
        }else {

            text = "去登录";
        }
        exit.setText(text);
    }

    @Override
    public void onClick(View view) {

        if (userid < 0) {

            Intent intent6 = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent6);
            getActivity().finish();
            return;
        }
        switch (view.getId()) {

            case R.id.pr1:
                Intent intent = new Intent(getActivity(), GetInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.pr2:
                Intent intent2 = new Intent(getActivity(), OrderListActivity.class);
                startActivity(intent2);
                break;
            case R.id.pr3:
                Intent intent3 = new Intent(getActivity(), ChangePsswordActivity.class);
                startActivity(intent3);
                break;
            case R.id.pr4:
                Intent intent4 = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(intent4);
                break;
            case R.id.exit:

                Intent intent6 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent6);
                getActivity().finish();
                PrefStore prefStore = PrefStore.getInstance(getActivity());
                prefStore.clearPref();
                Const.clear();
                break;
            default:
                break;
        }
    }
}

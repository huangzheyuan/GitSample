package com.example.smartcity.view.job.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.baseinfo.Const;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.example.smartcity.view.car.query.entities.CarInfoListEntity;
import com.example.smartcity.view.job.entities.HotJobListEntity;
import com.example.smartcity.view.job.entities.PersonalInfo;
import com.example.smartcity.view.job.fragment.activities.PersonalResumeActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    private ImageView avaterView;
    private TextView userIdView;
    private TextView nicknameView;
    private TextView genderView;
    private TextView emailView;
    private TextView phoneView;
    private Button resumeManagementButton;
    private Button editButton;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public UserFragment() {
    }

    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        avaterView = view.findViewById(R.id.imageView_avater);
        userIdView = view.findViewById(R.id.textView_userId);
        userIdView.setVisibility(View.GONE);
        nicknameView = view.findViewById(R.id.textView_nickname);
        genderView = view.findViewById(R.id.textView_gender);
        emailView = view.findViewById(R.id.textView_email);
        phoneView = view.findViewById(R.id.textView_phone);
        resumeManagementButton = view.findViewById(R.id.button_resume_management);
        editButton = view.findViewById(R.id.button_edit);

        resumeManagementButton.setOnClickListener(view1 -> {

            Intent intent = new Intent(view1.getContext(), PersonalResumeActivity.class);
            view1.getContext().startActivity(intent);
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }




    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    public void getData() {

        if (Const.userid < 0) {

            return;
        }
        OkHttpUtils okHttp = OkHttpUtils.getInstance();
//        PrefStore prefStore = PrefStore.getInstance(getActivity());
//        String token = prefStore.getPref("Authorization", null);
//
//        String url = String.format("%s%d", Connectinfo.byUserIdurl, Const.userid);

        if (Const.tokens != null) {

            okHttp.syncJsonGetwithTokenByURL(Connectinfo.byUserIdurl, Const.tokens, jsonObject -> {

                Gson gson = new Gson();

                PersonalInfo personalInfo = gson.fromJson(jsonObject.toString(), PersonalInfo.class);

                if (personalInfo.getCode() == 200) {

                    PersonalInfo.UserDTO info = personalInfo.getUser();
                    userIdView.setText(String.valueOf(Const.userid));
                    nicknameView.setText("昵  称：" + info.getNickName());
                    genderView.setText("性  别：" + (info.getSex().equals("0") ? "男" : "女"));
                    emailView.setText("Email：" + info.getEmail());
                    phoneView.setText("电话号码：" + info.getPhonenumber());

                    if(info.getAvatar() != null && !TextUtils.equals(info.getAvatar(), "")){

//                        String avatarUrl= info.getAvatar().replace("http://124.93.196.45", "http://124.93.196.45:10001/prod-api");
                        Picasso.get().load("http://124.93.196.45:10001/prod-api" + info.getAvatar()).placeholder(R.drawable.bashi2).error(R.drawable.bashi2).into(avaterView);
                    }

                }
            });
        }

    }

}
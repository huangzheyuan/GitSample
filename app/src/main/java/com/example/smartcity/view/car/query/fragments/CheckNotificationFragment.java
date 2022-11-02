package com.example.smartcity.view.car.query.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.example.smartcity.view.car.query.entities.CarCheckNotificationEntity;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckNotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckNotificationFragment extends Fragment {

    private TextView checkNotificationView;

    private String noticeText = "";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CheckNotificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment notificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckNotificationFragment newInstance(String param1, String param2) {
        CheckNotificationFragment fragment = new CheckNotificationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        checkNotificationView = view.findViewById(R.id.textView_check_notification);
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    private void getData() {

        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        PrefStore prefStore = PrefStore.getInstance(getActivity());


        String token = prefStore.getPref("Authorization", null);

        okHttp.syncGetwithTokenByURL(Connectinfo.querycarNoticeurl, token, new OkHttpUtils.FuncJsonString() {
            @Override
            public void onResponse(String result) {

                Gson gson = new Gson();
                CarCheckNotificationEntity carCheckNotification = gson.fromJson(result, CarCheckNotificationEntity.class);
                if (carCheckNotification != null && carCheckNotification.getCode() == 200) {

                    String notice = carCheckNotification.getData().getNotice();
                    if (!notice.equals(noticeText)) {
                        noticeText = notice;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            checkNotificationView.setText(Html.fromHtml(notice, Html.FROM_HTML_MODE_LEGACY));
                        } else {
                            checkNotificationView.setText(Html.fromHtml(notice));
                        }
                    }
                }else{

                    Toast.makeText(getActivity(), carCheckNotification.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
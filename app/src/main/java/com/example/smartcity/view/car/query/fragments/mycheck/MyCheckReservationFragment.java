package com.example.smartcity.view.car.query.fragments.mycheck;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.example.smartcity.view.car.query.entities.CarInfoListEntity;
import com.example.smartcity.view.car.query.entities.MyCheckListEntity;
import com.example.smartcity.view.car.query.fragments.mycheck.adapter.MyCheckListAdapter;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyCheckReservationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyCheckReservationFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyCheckListAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyCheckReservationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyReservationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyCheckReservationFragment newInstance(String param1, String param2) {
        MyCheckReservationFragment fragment = new MyCheckReservationFragment();
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
    public void onResume() {
        super.onResume();
        getData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){

            getData();
        }
    }

    private void getData() {

        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        PrefStore prefStore = PrefStore.getInstance(getActivity());
        String token = prefStore.getPref("Authorization", null);
        String userId = prefStore.getPref("userId", "1");

        int pageNum = 1;
        int pageSize = 10;
//        String url = String.format("%s?userId=%s&pageNum=%d&pageSize=%d", Connectinfo.aptlisturl, userId, pageNum, pageSize);
        String url = String.format("%s?userId=%s", Connectinfo.aptlisturl, userId);

        if (token != null) {

            okHttp.syncJsonGetwithTokenByURL(url, token, jsonObject -> {

                Gson gson = new Gson();

                MyCheckListEntity myCheckListEntity = gson.fromJson(jsonObject.toString(), MyCheckListEntity.class);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                adapter.setData(myCheckListEntity);
                DividerItemDecoration decoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                recyclerView.addItemDecoration(decoration);
            });
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new MyCheckListAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_reservation, container, false);
    }
}
package com.example.smartcity.view.job.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.baseinfo.Const;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.view.job.entities.PostListEntity;
import com.example.smartcity.view.job.fragment.adapter.PostListAdapter;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JobPostListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JobPostListFragment extends Fragment {

    private RecyclerView recyclerView;
    private PostListAdapter adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public JobPostListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JobPostListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JobPostListFragment newInstance(String param1, String param2) {
        JobPostListFragment fragment = new JobPostListFragment();
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
        recyclerView = view.findViewById(R.id.recyclerView);


        adapter = new PostListAdapter();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        DividerItemDecoration decoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);

//        getData();

    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_job_post_list, container, false);
    }

    private void getData(){

        if(Const.userid < 0){

            return;
        }

        OkHttpUtils okHttp = OkHttpUtils.getInstance();
//        PrefStore prefStore = PrefStore.getInstance(getActivity());
//        String token = prefStore.getPref("Authorization", null);
//        String userId = prefStore.getPref("userId", "1");

//        int pageNum = 1;
//        int pageSize = 10;
//        String url = String.format("%s?userId=%s&pageNum=%d&pageSize=%d", Connectinfo.deliverlisturl, userId, pageNum, pageSize);

        if (Const.tokens != null) {

            okHttp.syncJsonGetwithTokenByURL(Connectinfo.deliverlisturl, Const.tokens, jsonObject -> {

                Gson gson = new Gson();
                PostListEntity postListEntity = gson.fromJson(jsonObject.toString(), PostListEntity.class);
                adapter.setData(postListEntity);
            });
        }
    }

}
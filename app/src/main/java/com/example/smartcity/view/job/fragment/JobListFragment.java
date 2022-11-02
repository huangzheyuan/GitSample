package com.example.smartcity.view.job.fragment;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.baseinfo.Const;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.view.job.JobMainActivity;
import com.example.smartcity.view.job.entities.HotJobListEntity;
import com.example.smartcity.view.job.entities.JobListEntity;
import com.example.smartcity.view.job.fragment.activities.JobDetailActivity;
import com.example.smartcity.view.job.fragment.adapter.HotJobListAdapter;
import com.example.smartcity.view.job.fragment.adapter.JobListAdapter;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JobListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JobListFragment extends Fragment {


    private EditText searchView;
    private ImageButton searchButton;
    private RecyclerView hotRecyclerView;
    private RecyclerView recyclerView;

    private HotJobListAdapter hotAdapter;
    private JobListAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public JobListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JobListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JobListFragment newInstance(String param1, String param2) {
        JobListFragment fragment = new JobListFragment();
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

        searchView = view.findViewById(R.id.editText_job);
        searchButton = view.findViewById(R.id.button_search);
        hotRecyclerView = view.findViewById(R.id.recyclerView_hot_job_list);
        recyclerView = view.findViewById(R.id.recyclerView_job_list);

        hotAdapter = new HotJobListAdapter(this);
        adapter = new JobListAdapter(this);

        RecyclerView.LayoutManager hotLayoutManager = new GridLayoutManager(getContext(), 6);
        hotRecyclerView.setLayoutManager(hotLayoutManager);

        hotRecyclerView.setAdapter(hotAdapter);

        DividerItemDecoration hotDecoration = new DividerItemDecoration(hotRecyclerView.getContext(), DividerItemDecoration.HORIZONTAL);
        hotRecyclerView.addItemDecoration(hotDecoration);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        DividerItemDecoration decoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);

        getData("");
        getHotData();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getData(searchView.getText().toString());
            }
        });
    }


    public void goToUserFragment(){

        JobMainActivity activity = (JobMainActivity) getActivity();
        activity.goToFragment(3);
    }

    public void goToPostListFragment(){

        Toast.makeText(getContext(), "go to post list fragment", Toast.LENGTH_SHORT).show();

    }

    public void showJobDetail(JobListEntity.RowsBean job){

        Intent intent = new Intent(getContext(), JobDetailActivity.class);
        intent.putExtra("job", job);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1){

            goToUserFragment();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_job_list, container, false);
    }

    public void getData(String name) {

        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        int paperNum = 1;
        int paperSize = 10;
        String url = String.format("%s?name=%s", Connectinfo.postlisturl, name);

        okHttp.syncJsonGetwithTokenByURL(url, Const.tokens, jsonObject -> {

            Gson gson = new Gson();

            JobListEntity jobListEntity = gson.fromJson(jsonObject.toString(), JobListEntity.class);
            adapter.setData(jobListEntity);
        });
    }

    public void getHotData() {

        OkHttpUtils okHttp = OkHttpUtils.getInstance();
//        int paperNum = 1;
//        int paperSize = 10;
//        String url = String.format("%s?paperNum=%d&paperSize=%d", Connectinfo.professionlisturl, paperNum, paperSize);

        okHttp.syncJsonGetwithTokenByURL(Connectinfo.professionlisturl, Const.tokens, jsonObject -> {

            Gson gson = new Gson();

            HotJobListEntity hotJobListEntity = gson.fromJson(jsonObject.toString(), HotJobListEntity.class);
            hotAdapter.setData(hotJobListEntity);
        });
    }

    public void getHotJobList(int id) {

        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        String url = String.format("%s?professionId=%d", Connectinfo.postlisturl, id);

        okHttp.syncJsonGetwithTokenByURL(url,Const.tokens, jsonObject -> {

            Gson gson = new Gson();

            JobListEntity jobListEntity = gson.fromJson(jsonObject.toString(), JobListEntity.class);
            adapter.setData(jobListEntity);
        });
    }


}
package com.example.smartcity.view.car.query.fragments.checkfragment.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.baseinfo.Const;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.example.smartcity.view.car.query.entities.AddressListEntity;
import com.example.smartcity.view.car.query.fragments.checkfragment.CheckFragment;
import com.example.smartcity.view.car.query.fragments.checkfragment.dialog.adapter.AddressListAdapter;
import com.google.gson.Gson;


public class AddressListDialogFragment extends DialogFragment {

    private RecyclerView recyclerView;
    private AddressListAdapter adapter;
    private Button buttonCancel;
    private int paperNum = 1;
    private int paperSize = 10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_address_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = view.findViewById(R.id.recyclerView);
        buttonCancel = view.findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(view1 -> dismiss());

        adapter = new AddressListAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);

        recyclerView.addItemDecoration(decoration);
    }

    public void afterSelectPlace(AddressListEntity.RowsBean address){

        CheckFragment fragment = (CheckFragment) getParentFragment();
        fragment.setSelectedAPlace(address);
        dismiss();
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        getData();
    }

    private void getData() {

        OkHttpUtils okHttp = OkHttpUtils.getInstance();
//        PrefStore prefStore = PrefStore.getInstance(getActivity());
//        String token = prefStore.getPref("Authorization", null);
        String url = String.format("%s?paperNum=%d&paperSize=%d", Connectinfo.queryplaceurl, paperNum, paperSize);

        if (Const.tokens != null) {

            okHttp.syncJsonGetwithTokenByURL(url,Const.tokens, jsonObject -> {

                Gson gson = new Gson();

                AddressListEntity addressListEntity = gson.fromJson(jsonObject.toString(), AddressListEntity.class);
                adapter.setData(addressListEntity);
                DividerItemDecoration decoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                recyclerView.addItemDecoration(decoration);
            });
        }
    }
}

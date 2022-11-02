package com.example.smartcity.view.car.query.fragments.checkfragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
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
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.example.smartcity.view.car.query.CheckCarActivity;
import com.example.smartcity.view.car.query.entities.AddressListEntity;
import com.example.smartcity.view.car.query.entities.CarInfoListEntity;
import com.example.smartcity.view.car.query.fragments.checkfragment.dialog.AddressListDialogFragment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckFragment extends Fragment {


    private CarCheckListAdapter adapter;
    private RecyclerView recyclerView;
    private Button buttonOk;
    private ImageButton buttonDate;
    private ImageButton buttonTime;
    private ImageButton buttonAddress;
    private EditText editTextDate, editTextTime;
    private EditText editTextPlace;

    private int paperNum = 1;
    private final int paperSize = 10;

    private int year, month, day, hour, minute, second;
    private CarInfoListEntity carInfoEntity;
    private AddressListEntity.RowsBean address;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public CheckFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReservationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckFragment newInstance(String param1, String param2) {
        CheckFragment fragment = new CheckFragment();
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
        buttonOk = view.findViewById(R.id.button_ok);
        buttonDate = view.findViewById(R.id.button_date);
        buttonTime = view.findViewById(R.id.button_time);
        buttonAddress = view.findViewById(R.id.button_address);

        editTextDate = view.findViewById(R.id.editText_date);
        editTextTime = view.findViewById(R.id.editText_time);
        editTextPlace = view.findViewById(R.id.editText_place);

        editTextDate.setKeyListener(null);
        editTextTime.setKeyListener(null);
        editTextPlace.setKeyListener(null);

        buttonOk.setOnClickListener(view1 -> {

            int position = adapter.lastSelectedPosition;
            String date = editTextDate.getText().toString();
            String time = editTextTime.getText().toString();

            if (position >= 0 && address != null && !date.equals("") && !time.equals("")) {

                PrefStore prefStore = PrefStore.getInstance(getActivity());
                String token = prefStore.getPref("Authorization", null);
                String userId = prefStore.getPref("userId", "1");

                Map<String, String> params = new HashMap<>();

                CarInfoListEntity.RowsBean carInfo = carInfoEntity.getRows().get(position);
                String carId = String.valueOf(carInfo.getId());
                String addressId = String.valueOf(address.getId());

                String aptTime = String.format("%s %s", date, time);

                params.put("userId", userId);
                params.put("carId", carId);
                params.put("aptTime", aptTime);
                params.put("addressId", addressId);

                OkHttpUtils okHttp = OkHttpUtils.getInstance();

                okHttp.syncRequestWithBodyAndTokenForJsonString(Connectinfo.apturl, params, token, OkHttpUtils.ReqType.POST, result -> {

                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
                    int code = jsonObject.get("code").getAsInt();
                    String msg = jsonObject.get("msg").getAsString();

                    if (code == 200) {

                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

                    }
                });
            }else if(position < 0){

                Toast.makeText(getContext(), "请选择车辆", Toast.LENGTH_SHORT).show();
            }else if(address == null) {

                Toast.makeText(getContext(), "请选择检车地点", Toast.LENGTH_SHORT).show();
            }else if(date == null){

                Toast.makeText(getContext(), "请选择预约日期", Toast.LENGTH_SHORT).show();
            }else if(time == null){

                Toast.makeText(getContext(), "请选择预约时间", Toast.LENGTH_SHORT).show();
            }

        });

        buttonDate.setOnClickListener(view12 -> {

            final Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    (view14, year, monthOfYear, dayOfMonth) -> editTextDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth), year, month, day);
            datePickerDialog.show();
        });


        buttonTime.setOnClickListener(view13 -> {

            final Calendar c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                    (view15, hourOfDay, minute) -> editTextTime.setText(hourOfDay + ":" + minute + ":00"), hour, minute, true);
            timePickerDialog.show();
        });

        buttonAddress.setOnClickListener(view16 -> {

            DialogFragment newFragment = new AddressListDialogFragment();
            newFragment.show(getChildFragmentManager(), "dialog");
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        adapter = new CarCheckListAdapter();
        return inflater.inflate(R.layout.fragment_reservation, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    //    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//
//        if(!hidden){
//            getData();
//        }
//    }
    public void setSelectedAPlace(AddressListEntity.RowsBean address) {


        this.address = address;
        editTextPlace.setText(address.getPlaceName());
    }

    private void getData() {

        OkHttpUtils okHttp = OkHttpUtils.getInstance();
        PrefStore prefStore = PrefStore.getInstance(getActivity());
        String token = prefStore.getPref("Authorization", null);
        String userId = prefStore.getPref("userId", "1");
        String url = String.format("%s?userId=%s&paperNum=%d&paperSize=%d", Connectinfo.carslisturl, userId, paperNum, paperSize);

        if (token != null) {

            okHttp.syncJsonGetwithTokenByURL(url, token, jsonObject -> {

                Gson gson = new Gson();

                carInfoEntity = gson.fromJson(jsonObject.toString(), CarInfoListEntity.class);
                if (carInfoEntity != null && carInfoEntity.getRows() != null) {

                    if (carInfoEntity.getRows().size() == 0) {
                        //跳转
                        Toast.makeText(getActivity(), "暂无车辆，请先添加车辆信息！", Toast.LENGTH_LONG).show();
                        CheckCarActivity activity = (CheckCarActivity) getActivity();
                        activity.selectTab(3);

                    } else {
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);

                        adapter.setData(carInfoEntity);

                        DividerItemDecoration decoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);

                        recyclerView.addItemDecoration(decoration);
                    }
                }
            });
        }

    }

}
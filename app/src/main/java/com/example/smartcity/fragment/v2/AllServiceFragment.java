package com.example.smartcity.fragment.v2;

import static com.example.smartcity.baseinfo.Const.tokens;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.bean.QueryBean;
import com.example.smartcity.bean.QueryData;
import com.example.smartcity.otherview.ElectricityFeeActivity;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.view.BusOrderActivity;
import com.example.smartcity.view.HospitalActivity;
import com.example.smartcity.view.IllegaActivity;
import com.example.smartcity.view.LinesActivity;
import com.example.smartcity.view.LoginActivity;
import com.example.smartcity.view.ParklotActivity;
import com.example.smartcity.view.car.movement.CarMovementActivity;
import com.example.smartcity.view.car.query.CheckCarActivity;
import com.example.smartcity.view.data.DataAnalysisActivity;
import com.example.smartcity.view.house.HouseActivity;
import com.example.smartcity.view.job.JobMainActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllServiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllServiceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView serviceTypesView;
    private RecyclerView servicesView;
    private TextView serviceTypeTitleView;

    private QueryData queryData;
    private ServiceTypesAdapter serviceTypeAdapter;
    private ServicesAdapter servicesAdapter;

    private List<String> serviceTypes = new ArrayList<>();
    private Map<String, List<QueryBean>> serviceListMap = new HashMap<>();
    private Map<String , Class> activityClassMap = new HashMap<>();

    public AllServiceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllServiceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllServiceFragment newInstance(String param1, String param2) {
        AllServiceFragment fragment = new AllServiceFragment();
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

        activityClassMap.put("城市地铁", LinesActivity.class);
        activityClassMap.put("生活缴费", ElectricityFeeActivity.class);

        activityClassMap.put("看电影", CarMovementActivity.class);
        activityClassMap.put("活动管理", CheckCarActivity.class);//wrong
        activityClassMap.put("智慧巴士", BusOrderActivity.class);

        activityClassMap.put("门诊预约", HospitalActivity.class);

//        activityClassMap.put("外卖订单",);

        activityClassMap.put("找房子", HouseActivity.class);
        activityClassMap.put("找工作", JobMainActivity.class);
        activityClassMap.put("停哪儿", ParklotActivity.class);
        activityClassMap.put("智慧交管", IllegaActivity.class);
        activityClassMap.put("数据分析", DataAnalysisActivity.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_service, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        serviceTypesView = view.findViewById(R.id.recyclerView_service_types);
        servicesView = view.findViewById(R.id.recyclerView_services);
        serviceTypeTitleView = view.findViewById(R.id.textView_service_type_title);

        serviceTypeAdapter = new ServiceTypesAdapter();
        servicesAdapter = new ServicesAdapter();
        requestData();

    }

    private void requestData() {

        String params2 = "?isRecommend=N";
        OkHttpUtils okHttp2 = OkHttpUtils.getInstance();
        okHttp2.syncJsonStringByURL(Connectinfo.all_services_url + params2, new OkHttpUtils.FuncJsonString() {

            @Override
            public void onResponse(String result) {
                queryData = new Gson().fromJson(result, QueryData.class);

                Set<String> typeSet = new HashSet<>();
                for (int i = 0; i < queryData.getRows().size(); i++) {

                    typeSet.add(queryData.getRows().get(i).getServiceType());
                }

                Iterator<String> iterator = typeSet.iterator();

                serviceTypes.clear();
                serviceListMap.clear();

                while (iterator.hasNext()) {

                    String type = iterator.next();
                    serviceTypes.add(type);
                }

                for (int i = 0; i < queryData.getRows().size(); i++) {

                    String type = queryData.getRows().get(i).getServiceType();

                    if(!serviceListMap.containsKey(type)){

                        List<QueryBean> list = new ArrayList<>();
                        list.add(queryData.getRows().get(i));
                        serviceListMap.put(type,list );
                    }else {

                        List<QueryBean> list = serviceListMap.get(type);
                        list.add(queryData.getRows().get(i));
                    }
                }

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                serviceTypesView.setLayoutManager(layoutManager);
                serviceTypesView.setAdapter(serviceTypeAdapter);
                serviceTypeAdapter.setData(serviceTypes);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
                servicesView.setLayoutManager(gridLayoutManager);
                servicesView.setAdapter(servicesAdapter);

                serviceTypeAdapter.SetOnServiceTypeChanged(type -> {

                    List<QueryBean> list = serviceListMap.get(type);
                    if(list != null){

                        servicesAdapter.setData(list);
                        serviceTypeTitleView.setText(type);
                    }

                });

                servicesAdapter.setOnServiceSelected(new ServicesAdapter.OnServiceSelected() {
                    @Override
                    public void serviceSelected(String serviceName) {

                        if (tokens == null) {
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                            return;
                        }



//                        Class cls = activityClassMap.get(serviceName);
//                        if(cls != null){
//
//
//                            startActivity(new Intent(getActivity(), cls));
//                        }

                        if(serviceName.equals("找工作") || serviceName.equals("找房子") || serviceName.equals("门诊预约") || serviceName.equals("停哪儿") ||  serviceName.equals("智慧交管")||  serviceName.equals("活动管理")||  serviceName.equals("看电影") ||  serviceName.equals("数据分析") ){

                            Intent intent = new Intent(getActivity(), activityClassMap.get(serviceName));
                            startActivity(intent);
                        }else {

                            Toast.makeText(getActivity(), serviceName + " 即将上线", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                List<QueryBean> list = serviceListMap.get(serviceTypes.get(0));
                if(list != null){

                    servicesAdapter.setData(list);
                }
                serviceTypeTitleView.setText(serviceTypes.get(0));
            }
        });

    }
}
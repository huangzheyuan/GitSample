package com.example.smartcity.view.car.movement.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.baseinfo.Const;
import com.example.smartcity.util.OkHttpUtils;
import com.example.smartcity.util.PrefStore;
import com.example.smartcity.util.UploadFile;
import com.example.smartcity.view.LoginActivity;
import com.example.smartcity.view.car.movement.FileUtil;
import com.example.smartcity.view.car.movement.entities.Cities;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarMovementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarMovementFragment extends Fragment {


    private EditText plateView, nameView, phoneView, idcardView, addressView;
    private Spinner provinceSpinner, citySpinner, districtSpinner;
    private ImageView livePicImageView;
    private TextView takePhoneView;
    private Button okButton;
    private Cities[] cities;
    private Uri imageUri;
    private TextView phoneNumberView;
    private ImageButton callButton;

    private String filePath, fileName;

    private ArrayAdapter<String> provinceAdapter, cityAdapter, districtAdapter;
    private List<String> provinceList, cityList, districtList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private UploadFile.OnUploadOver onUploadOver = new UploadFile.OnUploadOver() {
        @Override
        public void onSuccess(Object fileName, Object url) {

            request(fileName.toString());
        }

        @Override
        public void onFailure(String error) {

        }
    };

    public CarMovementFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CarMovementFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CarMovementFragment newInstance(String param1, String param2) {
        CarMovementFragment fragment = new CarMovementFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_movement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
    }

    private void initView(View view) {

        plateView = view.findViewById(R.id.editText_plate);
        nameView = view.findViewById(R.id.editText_name);
        phoneView = view.findViewById(R.id.editText_phone);
        idcardView = view.findViewById(R.id.editText_id_card);
        addressView = view.findViewById(R.id.editText_address);

        provinceSpinner = view.findViewById(R.id.spinner_province);
        citySpinner = view.findViewById(R.id.spinner_city);
        districtSpinner = view.findViewById(R.id.spinner_district);

        takePhoneView = view.findViewById(R.id.textView_take_photo);
        livePicImageView = view.findViewById(R.id.imageView_live);

        okButton = view.findViewById(R.id.button_ok);

        loadAddressIntoSpinner();

        takePhoneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imageUri = createImageUri(getContext());
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//如果不设置EXTRA_OUTPUT getData()  获取的是bitmap数据  是压缩后的
                startActivityForResult(intent, 1);
            }
        });

        livePicImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (verify()) {

                    UploadFile.uploadFile(filePath, fileName, onUploadOver);
                }

            }
        });

        phoneNumberView = view.findViewById(R.id.textView_phone_number);
        callButton = view.findViewById(R.id.imageButton_dial);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumberView.getText()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

    private void request(String livePhotoUrl) {

        if (Const.userid < 0) {

            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
            return;
        }

        PrefStore prefStore = PrefStore.getInstance(getContext());
        String token = prefStore.getPref("Authorization", null);

        Map<String, String> params = new HashMap<>();
        params.put("address", addressView.getText().toString());
        params.put("area", districtSpinner.getSelectedItem().toString());
        params.put("city", citySpinner.getSelectedItem().toString());
        params.put("idCard", idcardView.getText().toString());
        params.put("photo", livePhotoUrl);
        params.put("plateNo", plateView.getText().toString());
        params.put("province", provinceSpinner.getSelectedItem().toString());
        params.put("tel", phoneView.getText().toString());

        OkHttpUtils okHttp = OkHttpUtils.getInstance();

        okHttp.syncPostByUrladdHeaderToken(Connectinfo.querytelurl, params, token, new OkHttpUtils.FuncJsonString() {
            @Override
            public void onResponse(String result) {

                JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
                int code = jsonObject.get("code").getAsInt();
                String msg = jsonObject.get("msg").getAsString();

                if (code == 200) {

                    String plateNo = jsonObject.getAsJsonObject("data").get("plateNo").getAsString();
                    String tel = jsonObject.getAsJsonObject("data").get("tel").getAsString();
                    phoneNumberView.setText(tel);

                    phoneNumberView.setVisibility(View.VISIBLE);
                    callButton.setVisibility(View.VISIBLE);

                } else {

                    phoneNumberView.setVisibility(View.GONE);
                    callButton.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                }
            }
        });

//        String filePath = FileUtil.getRealPathFromURI(getContext(), imageUri);
//
//        File file = new File(filePath);
//
//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("file", file.getName(),
//                        RequestBody.create(MediaType.parse("multipart/form-data"), file))
//                .addFormDataPart("names", name)
//                .addFormDataPart("cardId", idcard)
//                .addFormDataPart("userId", String.valueOf(Const.userid))
//                .addFormDataPart("tel", tel)
//                .addFormDataPart("address", address)
//                .addFormDataPart("plates", plate)
//                .build();
//
//        Request request = new Request.Builder()
//                .header("Authorization", token)
//                .url(Connectinfo.querytelurl)
//                .post(requestBody)
//                .build();
//
//        new OkHttpClient().newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//
//                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                Log.d("TAG", "onResponse: " + response.body().string());
//
//                Toast.makeText(getActivity(), response.body().string(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }

    private boolean verify() {

        String plate = plateView.getText().toString();
        String name = nameView.getText().toString();
        String phone = phoneView.getText().toString();
        String idcard = idcardView.getText().toString();
        String address = addressView.getText().toString();

        if (plate.equals("")) {

            Toast.makeText(getContext(), "请输入车牌号", Toast.LENGTH_SHORT).show();
            return false;
        }

//        if (name.equals("")) {
//
//            Toast.makeText(getContext(), "请输入姓名", Toast.LENGTH_SHORT).show();
//            return false;
//        }

        if (phone.equals("")) {

            Toast.makeText(getContext(), "请输入电话号", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (idcard.equals("")) {

            Toast.makeText(getContext(), "请输入身份证号", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (address.equals("")) {

            Toast.makeText(getContext(), "请输入地址信息", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (imageUri == null) {

            Toast.makeText(getContext(), "请拍摄现场照片", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            livePicImageView.setImageURI(imageUri);
            filePath = FileUtil.getRealPathFromURI(getContext(), imageUri);
            int index = filePath.lastIndexOf("/") + 1;
            if (index >= 0) {
                fileName = filePath.substring(index);
            }
        }
    }

    private void loadAddressIntoSpinner() {

        InputStream is = getResources().openRawResource(R.raw.cities);

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        Gson gson = new GsonBuilder().create();

        cities = gson.fromJson(reader, Cities[].class);

        provinceList = getProvinceList(cities);
        cityList = getCityList(cities, provinceList.get(0));
        districtList = getDistrictList(cities, provinceList.get(0), cityList.get(0));

        provinceAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, provinceList);
        provinceSpinner.setAdapter(provinceAdapter);

        cityAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, cityList);
        citySpinner.setAdapter(cityAdapter);

        districtAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, districtList);
        districtSpinner.setAdapter(districtAdapter);

        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String province = provinceList.get(i);
                cityList = getCityList(cities, province);
                cityAdapter.notifyDataSetChanged();
                citySpinner.setSelection(0);

                String cityName = cityList.get(0);
                districtList = getDistrictList(cities, province, cityName);
                districtAdapter.notifyDataSetChanged();
                districtSpinner.setSelection(0);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String province = provinceList.get(provinceSpinner.getSelectedItemPosition());
                String cityName = cityList.get(i);
                districtList = getDistrictList(cities, province, cityName);
                districtAdapter.notifyDataSetChanged();
                districtSpinner.setSelection(0);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private List<String> getProvinceList(Cities[] cities) {

        if (provinceList == null) {

            provinceList = new ArrayList<>();
        } else {

            provinceList.clear();
        }

        for (Cities city : cities
        ) {

            provinceList.add(city.getProvinceName());
        }

        return provinceList;
    }

    private List<String> getCityList(Cities[] cities, String province) {

        if (cityList == null) {

            cityList = new ArrayList<>();
        } else {

            cityList.clear();
        }

        for (Cities city : cities
        ) {

            if (city.getProvinceName().equals(province)) {

                for (Cities.MallCityListBean bean : city.getMallCityList()
                ) {

                    cityList.add(bean.getCityName());
                }

                break;
            }
        }

        return cityList;
    }

    private List<String> getDistrictList(Cities[] cities, String province, String cityName) {

        if (districtList == null) {

            districtList = new ArrayList<>();
        } else {

            districtList.clear();
        }

        for (Cities city : cities
        ) {

            if (city.getProvinceName().equals(province)) {

                for (Cities.MallCityListBean bean : city.getMallCityList()
                ) {

                    if (bean.getCityName().equals(cityName)) {

                        for (Cities.MallCityListBean.MallAreaListBean areaBean : bean.getMallAreaList()
                        ) {

                            districtList.add(areaBean.getAreaName());

                        }
                        break;
                    }
                }

                break;
            }
        }

        return districtList;
    }

    private Uri createImageUri(Context context) {

        String name = "car" + System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, name);
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, name + ".jpeg");
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        return uri;
    }
}
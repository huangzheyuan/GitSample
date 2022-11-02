package com.example.smartcity.view.car.query;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.view.car.query.fragments.carmanagement.CarManagementFragment;
import com.example.smartcity.view.car.query.fragments.checkfragment.CheckFragment;
import com.example.smartcity.view.car.query.fragments.CheckNotificationFragment;
import com.example.smartcity.view.car.query.fragments.mycheck.MyCheckReservationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CheckCarActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment currentFragment;

    private Fragment checkNotificationFragment;
    private Fragment checkFragment;
    private Fragment myCheckReservationFragment;
    private Fragment carManagementFragment;

    private TextView headerTitleView;
    private String[] headerTitles = new String[]{"预约须知", "立即预约", "我的预约", "车辆管理"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_car);

        headerTitleView = findViewById(R.id.headerTitle);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }

        checkNotificationFragment = new CheckNotificationFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_holder, checkNotificationFragment, "checkNotificationFragment");
        currentFragment = checkNotificationFragment;
        fragmentTransaction.show(checkNotificationFragment);

        fragmentTransaction.commit();

        setHeaderTitle(0);

        bottomNavigationView = findViewById(R.id.car_check_bottom_view);


        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            fragmentTransaction = fragmentManager.beginTransaction();


            switch (item.getItemId()) {

                case R.id.menu_item_car_check_notification:

                    setHeaderTitle(0);
                    fragmentTransaction.hide(currentFragment);
                    currentFragment = checkNotificationFragment;
                    fragmentTransaction.show(currentFragment);

                    break;
                case R.id.menu_item_car_check:

                    setHeaderTitle(1);
                    fragmentTransaction.hide(currentFragment);
                    if (checkFragment == null) {

                        checkFragment = new CheckFragment();
                        fragmentTransaction.add(R.id.fragment_holder, checkFragment, "checkFragment");
                    }
                    currentFragment = checkFragment;
                    fragmentTransaction.show(currentFragment);

                    break;
                case R.id.menu_item_car_check_reservation:
                    setHeaderTitle(2);
                    fragmentTransaction.hide(currentFragment);

                    if (myCheckReservationFragment == null) {
                        myCheckReservationFragment = new MyCheckReservationFragment();
                        fragmentTransaction.add(R.id.fragment_holder, myCheckReservationFragment, "myCheckReservationFragment");
                    }
                    currentFragment = myCheckReservationFragment;
                    fragmentTransaction.show(currentFragment);
                    break;
                case R.id.menu_item_car_management:
                    setHeaderTitle(3);
                    fragmentTransaction.hide(currentFragment);

                    if (carManagementFragment == null) {

                        carManagementFragment = new CarManagementFragment();
                        fragmentTransaction.add(R.id.fragment_holder, carManagementFragment, "carManagementFragment");
                    }

                    currentFragment = carManagementFragment;
                    fragmentTransaction.show(currentFragment);
                    break;
            }

            fragmentTransaction.commit();

            return true;
        });


    }

    public void setHeaderTitle(int index){

        headerTitleView.setText(headerTitles[index]);
    }

    public void selectTab(int index) {

        int itemId = -1;
        switch (index) {

            case 0:
                itemId = R.id.menu_item_car_check_notification;
                break;
            case 1:
                itemId = R.id.menu_item_car_check;

                break;
            case 2:
                itemId = R.id.menu_item_car_check_reservation;

                break;
            case 3:
                itemId = R.id.menu_item_car_management;

                break;
        }
        bottomNavigationView.setSelectedItemId(itemId);

//        fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.hide(currentFragment);
//
//        switch (index) {
//
//            case 0:
//                if(checkNotificationFragment == null){
//
//                    checkNotificationFragment = new CheckNotificationFragment();
//                }
//                currentFragment = checkNotificationFragment;
//                break;
//            case 1:
//                if(checkFragment == null){
//
//                    checkFragment = new CheckFragment();
//                }
//                currentFragment = checkFragment;
//                break;
//            case 2:
//                if(myCheckReservationFragment == null){
//
//                    myCheckReservationFragment = new MyCheckReservationFragment();
//                }
//                currentFragment = myCheckReservationFragment;
//                break;
//            case 3:
//                if(carManagementFragment == null){
//
//                    carManagementFragment = new CarManagementFragment();
//                }
//                currentFragment = carManagementFragment;
//                break;
//        }
//
//        fragmentTransaction.show(currentFragment);
//        fragmentTransaction.commit();
    }

}
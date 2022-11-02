package com.example.smartcity.view.car.movement;

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
import com.example.smartcity.view.car.movement.fragment.CarMovementFragment;
import com.example.smartcity.view.car.movement.fragment.CarMovementHistoryFragment;
import com.example.smartcity.view.car.query.fragments.carmanagement.CarManagementFragment;
import com.example.smartcity.view.car.query.fragments.checkfragment.CheckFragment;
import com.example.smartcity.view.car.query.fragments.mycheck.MyCheckReservationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CarMovementActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment currentFragment;
    private String[] headerTitles = new String[]{"自助移车", "历史记录"};
    private TextView headerTitleView;
    private Fragment moveFragment, historyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_movement);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }
        headerTitleView = findViewById(R.id.headerTitle);

        moveFragment = new CarMovementFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_holder, moveFragment, "movementFragment");
        currentFragment = moveFragment;
        fragmentTransaction.show(moveFragment);

        fragmentTransaction.commit();

        setHeaderTitle(0);

        bottomNavigationView = findViewById(R.id.car_movement_bottom_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            fragmentTransaction = fragmentManager.beginTransaction();


            switch (item.getItemId()) {

                case R.id.menu_item_car_movement:

                    setHeaderTitle(0);
                    fragmentTransaction.hide(currentFragment);
                    currentFragment = moveFragment;
                    fragmentTransaction.show(currentFragment);

                    break;
                case R.id.menu_item_car_movement_history:

                    setHeaderTitle(1);
                    fragmentTransaction.hide(currentFragment);
                    if (historyFragment == null) {

                        historyFragment = new CarMovementHistoryFragment();
                        fragmentTransaction.add(R.id.fragment_holder, historyFragment, "movementHistoryFragment");
                    }
                    currentFragment = historyFragment;
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
}
package com.example.smartcity.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Const;
import com.example.smartcity.fragment.DangjianFragment;
import com.example.smartcity.fragment.EnviromentFragment;
import com.example.smartcity.fragment.HomeFragment;
import com.example.smartcity.fragment.MeFragment;
import com.example.smartcity.fragment.NewsFragment;
import com.example.smartcity.fragment.ServiceFragment;
import com.example.smartcity.fragment.v2.AllServiceFragment;
import com.example.smartcity.util.PrefStore;

import java.lang.reflect.Method;
import java.security.Permission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnCheckedChangeListener {

    private ViewPager main_viewPager;
    private RadioGroup main_tab_RadioGroup;
    private RadioButton radio_home, radio_service,
            radio_enviroment, radio_news, radio_me;
    private ArrayList<Fragment> fragmentList;
    private TextView t;
    private static final int PERMISSION_REQUEST = 10;

    private String permissions[] = {Manifest.permission.INTERNET, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE};
    private int[] radio_ids = new int[]{R.id.radio_home, R.id.radio_service, R.id.radio_enviroment, R.id.radio_news, R.id.radio_me};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.nobar);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }




//        if (Build.VERSION.SDK_INT >= 30){
//            if (!Environment.isExternalStorageManager()){
//                Intent getpermission = new Intent();
//                getpermission.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
//                startActivity(getpermission);
//            }
//        }
        t = findViewById(R.id.headerTitle);
//			Toolbar toolbar =  findViewById(R.id.toolbar);
//			toolbar.setTitle(R.string.app_name);
//			setSupportActionBar(toolbar);
//
//			//设置 Logo
//			toolbar.setLogo(R.mipmap.ic_launcher);
//			// 设置主标题
//			toolbar.setTitle("主页");
//			// 设置副标题
//			 toolbar.setSubtitle("Sub Title");
//			//设置toolbar,利用Toolbar代替ActionBar
//
//			//设置导航按钮
//			 toolbar.setNavigationIcon(R.mipmap.ic_launcher_round);
//			getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示左边的小箭头
//			getSupportActionBar().setDisplayShowHomeEnabled(true);//显示home图片
//
//
//			//设置导航Button点击事件
//			toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					Toast.makeText(getApplication(), "点击导航栏", Toast.LENGTH_SHORT).show();
//				}
//			});
        InitView();
        InitViewPager();
    }


    private void applyPermissions(){


        List<String> permissionList = new ArrayList<>();

        for (int i = 0; i < permissions.length; i++) {

            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {

                permissionList.add(permissions[i]);
            }
        }

        if(permissionList.size() > 0){

            String[] itemsArray = new String[permissionList.size()];
            ActivityCompat.requestPermissions(this, permissionList.toArray(itemsArray), PERMISSION_REQUEST);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        applyPermissions();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //重写onMenuOpened()，通过反射使其图标可见
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            //case Menu.FIRST:
            case R.id.index:
                Toast.makeText(MainActivity.this, "点击主页菜单", Toast.LENGTH_SHORT).show();
                break;
            //case Menu.FIRST+1:
            case R.id.weather:
                Toast.makeText(MainActivity.this, "点击天气菜单", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void InitView() {
        main_tab_RadioGroup = findViewById(R.id.main_tab_RadioGroup);
        radio_home = findViewById(R.id.radio_home);
        radio_service = findViewById(R.id.radio_service);
        radio_enviroment = findViewById(R.id.radio_enviroment);
        radio_news = findViewById(R.id.radio_news);
        radio_me = findViewById(R.id.radio_me);
        main_tab_RadioGroup.check(R.id.radio_home);
        main_tab_RadioGroup.setOnCheckedChangeListener(this);//单选按钮的容器设置监听器
    }

    public void InitViewPager() {
        main_viewPager = findViewById(R.id.main_ViewPager);

        fragmentList = new ArrayList<Fragment>();

        Fragment homeFragment = new HomeFragment();
//        Fragment ServiceFragment = new ServiceFragment();
        Fragment allServiceFragment = new AllServiceFragment();
        Fragment DangjianFragment = new DangjianFragment();
        Fragment newsFragment = new NewsFragment();
        Fragment meFragment = new MeFragment();

        fragmentList.add(homeFragment);
//        fragmentList.add(ServiceFragment);
        fragmentList.add(allServiceFragment);
        fragmentList.add(DangjianFragment);
        fragmentList.add(newsFragment);
        fragmentList.add(meFragment);
        main_viewPager.setCurrentItem(0);
        main_viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), 0, fragmentList));
        main_viewPager.addOnPageChangeListener(new MyListner());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        PrefStore prefStore = PrefStore.getInstance(this);
        prefStore.clearPref();
        Const.clear();
    }

    public class MyAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> list;
        FragmentManager fm;

        public MyAdapter(@NonNull FragmentManager fm, int behavior, ArrayList<Fragment> list) {
            super(fm, behavior);
            this.fm = fm;
            this.list = list;
        }

        @Override
        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }


    public class MyListner implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int arg0) {

            int current = main_viewPager.getCurrentItem();
            switch (current) {
                case 0:
                    main_tab_RadioGroup.check(R.id.radio_home);
                    break;
                case 1:
                    main_tab_RadioGroup.check(R.id.radio_service);
                    break;
                case 2:
                    main_tab_RadioGroup.check(R.id.radio_enviroment);
                    break;
                case 3:
                    main_tab_RadioGroup.check(R.id.radio_news);
                    break;
                case 4:
                    main_tab_RadioGroup.check(R.id.radio_me);
                    break;
            }
        }

    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
        int current = 0;
        switch (checkId) {
            case R.id.radio_home:
                t.setText("主页");
                current = 0;
                break;
            case R.id.radio_service:
                t.setText("全部服务");
                current = 1;
                break;
            case R.id.radio_enviroment:
                t.setText("党建");
                current = 2;
                break;
            case R.id.radio_news:
                t.setText("新闻");
                current = 3;
                break;
            case R.id.radio_me:
                t.setText("我的");
                current = 4;
                break;
        }
        if (main_viewPager.getCurrentItem() != current) {
            main_viewPager.setCurrentItem(current);
        }

    }


    public void select(int page) {
        main_tab_RadioGroup.check(radio_ids[page]);
    }
}

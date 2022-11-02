package com.example.smartcity.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.smartcity.R;

public class shangmenhuishou extends AppCompatActivity {
    private android.widget.Spinner spinner;
    private android.widget.Button riqi;
    private android.widget.Button time;
    private android.widget.TextView pic;
    private android.widget.Button submit;
    private ArrayAdapter<String> arrayAdapter;
    private String[] type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shangmenhuishou);
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |=View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }
        spinner = (Spinner) findViewById(R.id.spinner);
        riqi = (Button) findViewById(R.id.riqi);
        time = (Button) findViewById(R.id.time);
        pic = (TextView) findViewById(R.id.pic);
        submit = (Button) findViewById(R.id.submit);

            time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(shangmenhuishou.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            time.setText(hourOfDay + ":" + minute);
                        }
                    }, 0, 0, true);
                    timePickerDialog.show();
                }
            });
            pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pic.setText("");
                    pic.setBackgroundResource(R.drawable.zhan4);
                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(shangmenhuishou.this, "提交成功！", Toast.LENGTH_SHORT).show();
                }
            });
            type = new String[]{"有害垃圾", "其他垃圾", "餐饮垃圾", "干垃圾"};
            arrayAdapter = new ArrayAdapter<>(shangmenhuishou.this, android.R.layout.simple_list_item_1, type);
            spinner.setAdapter(arrayAdapter);

        }




}
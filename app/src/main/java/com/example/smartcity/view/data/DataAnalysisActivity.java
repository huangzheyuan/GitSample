package com.example.smartcity.view.data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.smartcity.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


public class DataAnalysisActivity extends AppCompatActivity {

    private BarChart chart;

    private final int[] colors = new int[]{
            ColorTemplate.VORDIPLOM_COLORS[0],
            ColorTemplate.VORDIPLOM_COLORS[1],
            ColorTemplate.VORDIPLOM_COLORS[2]
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_analysis);

        TextView headerView = findViewById(R.id.headerTitle);
        headerView.setText("数据分析");

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
        }

        chart = findViewById(R.id.barChart_data_analysis);

//        initMulti();
        initBar();

    }

    private void initBar() {

        //        chart.setDrawBarShadow(false);
//        chart.setDrawValueAboveBar(true);

        chart.getDescription().setEnabled(false);
        chart.setMaxVisibleValueCount(60);
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(false);

        ValueFormatter xAxisFormatter = new DayAxisValueFormatter(chart);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setTypeface(tfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        xAxis.setValueFormatter(xAxisFormatter);

        ValueFormatter custom = new MyValueFormatter("");

        YAxis leftAxis = chart.getAxisLeft();
//        leftAxis.setTypeface(tfLight);
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawGridLines(false);
//        rightAxis.setTypeface(tfLight);
        rightAxis.setLabelCount(8, false);
        rightAxis.setValueFormatter(custom);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

//        XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);
//        mv.setChartView(chart);
//        chart.setMarker(mv);

        setData(8, 50);
    }

/*
    private void initMulti() {
        chart.setDrawGridBackground(false);
        chart.getDescription().setEnabled(false);
        chart.setDrawBorders(false);

        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setDrawAxisLine(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getXAxis().setDrawAxisLine(false);
        chart.getXAxis().setDrawGridLines(false);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        setData(8);
    }
*/

/*    private void setData(int count) {

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            ArrayList<Entry> values = new ArrayList<>();


            for (int j = 0; j < 3; j++) {
                values.add(new Entry(i, (float) (i * 10 + 10)));
            }
            LineDataSet d = new LineDataSet(values, String.valueOf(2014 + i));
            d.setLineWidth(2.5f);
            d.setCircleRadius(4f);

            int color = colors[i % colors.length];
            d.setColor(color);
            d.setCircleColor(color);
            dataSets.add(d);

            ((LineDataSet) dataSets.get(0)).enableDashedLine(10, 10, 0);
            ((LineDataSet) dataSets.get(0)).setColors(ColorTemplate.VORDIPLOM_COLORS);
            ((LineDataSet) dataSets.get(0)).setCircleColors(ColorTemplate.VORDIPLOM_COLORS);

        }

        LineData data = new LineData(dataSets);
        chart.setData(data);
    }*/

    private void setData(int count, float range) {

        float start = 1f;

        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = (int) start; i < start + count; i++) {

//            float val = (float) (Math.random() * (range + 1));
//            if (Math.random() * 100 < 25) {
////                values.add(new BarEntry(i, val, getResources().getDrawable(R.drawable.star)));
////            } else {
////                values.add(new BarEntry(i, val));
////            }
//
//                values.add(new BarEntry(i, val));
//
//            }

            float val = (float) (Math.random() * (range + 1));
            values.add(new BarEntry(i, val));


            BarDataSet set1;

            if (chart.getData() != null &&
                    chart.getData().getDataSetCount() > 0) {
                set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
                set1.setValues(values);
                chart.getData().notifyDataChanged();
                chart.notifyDataSetChanged();

            } else {

//                set1 = new BarDataSet(values, "The year 2017");
                set1 = new BarDataSet(values, "");
                set1.setDrawIcons(false);
                set1.setColors(ColorTemplate.MATERIAL_COLORS);

                int startColor1 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
                int startColor2 = ContextCompat.getColor(this, android.R.color.holo_blue_light);
                int startColor3 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
                int startColor4 = ContextCompat.getColor(this, android.R.color.holo_green_light);
                int startColor5 = ContextCompat.getColor(this, android.R.color.holo_red_light);
                int endColor1 = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
                int endColor2 = ContextCompat.getColor(this, android.R.color.holo_purple);
                int endColor3 = ContextCompat.getColor(this, android.R.color.holo_green_dark);
                int endColor4 = ContextCompat.getColor(this, android.R.color.holo_red_dark);
                int endColor5 = ContextCompat.getColor(this, android.R.color.holo_orange_dark);

                List<GradientColor> gradientColors = new ArrayList<>();
                gradientColors.add(new GradientColor(startColor1, endColor1));
                gradientColors.add(new GradientColor(startColor2, endColor2));
                gradientColors.add(new GradientColor(startColor3, endColor3));
                gradientColors.add(new GradientColor(startColor4, endColor4));
                gradientColors.add(new GradientColor(startColor5, endColor5));

                set1.setGradientColors(gradientColors);
                ArrayList<IBarDataSet> dataSets = new ArrayList<>();
                dataSets.add(set1);

                BarData data = new BarData(dataSets);
                data.setValueTextSize(10f);
                data.setBarWidth(0.9f);

                chart.setData(data);
            }
        }
    }

}
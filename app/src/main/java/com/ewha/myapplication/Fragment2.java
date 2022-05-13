package com.ewha.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Fragment2 extends Fragment {
    BarChart chart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment2, container, false);

        initUI(rootView);

        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        chart = rootView.findViewById(R.id.barchart);
        chart.setDrawValueAboveBar(true);

        // 확대나 터치 상호작용 x
        chart.setTouchEnabled(false);

        chart.getDescription().setEnabled(false);
        // 격자 구조 삽입 여부
        chart.setDrawGridBackground(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        // x축 label string으로 변경
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                String Xvalue = String.valueOf((int)value) + " - " + ((int)value + 4) + "시";
                return Xvalue;
            }
        });

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setLabelCount(6, false);
        // 차트 좌측 최소값 설정
        leftAxis.setAxisMinValue(0.0f);
        // granularity ~ 단위마다 선
        leftAxis.setGranularityEnabled(false);
        leftAxis.setGranularity(1f);
        // 오른쪽 Y축 안 보이도록 설정
        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);

        // 차트 범례 설정
        Legend legend2 = chart.getLegend();
        legend2.setEnabled(false);

        // 밑에서부터 올라오는 애니메이션
        chart.animateXY(1000, 1000);

        setData2();
    }

    private void setData2() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0.0f, 0));
        entries.add(new BarEntry(4.0f, 2.5f));
        entries.add(new BarEntry(8.0f, 3));
        entries.add(new BarEntry(12.0f, 4));
        entries.add(new BarEntry(16.0f, 3.3f));
        entries.add(new BarEntry(20.0f, 1));

        BarDataSet dataSet2 = new BarDataSet(entries, "Sinus Function");
        dataSet2.setColor(Color.parseColor("#80FFA400"));

        BarData data = new BarData(dataSet2);
        data.setValueTextSize(10f);
        data.setDrawValues(false);

        // 막대 너비 설정
        data.setBarWidth(2.0f);

        chart.setData(data);
        chart.invalidate();
    }
}
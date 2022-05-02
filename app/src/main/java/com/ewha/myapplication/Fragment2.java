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

        // 확대 x
        chart.setTouchEnabled(false);

        chart.getDescription().setEnabled(false);
        chart.setDrawGridBackground(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(false);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setLabelCount(6, false);
        leftAxis.setAxisMinValue(0.0f);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setGranularity(1f);


        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);

        Legend legend2 = chart.getLegend();
        legend2.setEnabled(false);

        chart.animateXY(1500, 1500);

        setData2();
    }

    private void setData2() {

        ArrayList<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(1.0f, 0));
        entries.add(new BarEntry(2.0f, 2.5f));
        entries.add(new BarEntry(3.0f, 3));
        entries.add(new BarEntry(4.0f, 4));
        entries.add(new BarEntry(5.0f, 3.3f));
        entries.add(new BarEntry(6.0f, 1));

        BarDataSet dataSet2 = new BarDataSet(entries, "Sinus Function");
        dataSet2.setColor(Color.parseColor("#80FFA400"));

        BarData data = new BarData(dataSet2);
        data.setValueTextSize(10f);
        data.setDrawValues(false);
        data.setBarWidth(0.5f);

        chart.setData(data);
        chart.invalidate();
    }


}
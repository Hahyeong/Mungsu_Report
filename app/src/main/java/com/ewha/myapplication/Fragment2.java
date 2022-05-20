package com.ewha.myapplication;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ewha.myapplication.data.movementdata;
import com.ewha.myapplication.network.RetrofitClient;
import com.ewha.myapplication.network.ServiceApi;
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
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment2 extends Fragment {
    BarChart chart;

    private Button daily;
    private Button weekly;
    private Button monthly;

    // 특정 행동 패턴 횟수 알림 팝업 버튼
    private Button eating;
    private Button bark;
    private Button poo;
    private Button abnormal;

    AlertDialog newDialog;

    private ServiceApi service;
    private TextView textViewResult;

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
        leftAxis.setAxisMaximum(4.0f);
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



        daily = rootView.findViewById(R.id.daily);
        weekly = rootView.findViewById(R.id.weekly);
        monthly = rootView.findViewById(R.id.monthly);

        setData2();
        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData2();
            }
        });
        weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData3();
            }
        });
        monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData4();
            }
        });

        // movementdata 가져오기 test
        // ServiceApi 객체 생성
        service = RetrofitClient.getClient().create(ServiceApi.class);

        textViewResult = rootView.findViewById(R.id.movementdatatest);
        showmovement();

        // 특정 행동 패턴 횟수 팝업
        eating = rootView.findViewById(R.id.eatingbutton);
        bark = rootView.findViewById(R.id.barkbutton);
        poo = rootView.findViewById(R.id.poobutton);
        abnormal = rootView.findViewById(R.id.abnormalbutton);

        AlertDialog.Builder newDialog = new AlertDialog.Builder(rootView.getContext(), android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

        eating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newDialog.setMessage("오늘 반려견의 식사 및 음수 횟수: 3회");
                newDialog.setPositiveButton(Html.fromHtml("<font color='#FFA400'>확인</font>"), null);
                newDialog.show();
            }
        });

        bark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newDialog.setMessage("오늘 반려견의 짖음 횟수: 5회");
                newDialog.setPositiveButton(Html.fromHtml("<font color='#FFA400'>확인</font>"), null);
                newDialog.show();
            }
        });

        poo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newDialog.setMessage("오늘 반려견의 배변 횟수: 2회");
                newDialog.setPositiveButton(Html.fromHtml("<font color='#FFA400'>확인</font>"), null);
                newDialog.show();
            }
        });

        abnormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newDialog.setMessage("오늘 반려견의 특이행동 횟수: 2회");
                newDialog.setPositiveButton(Html.fromHtml("<font color='#FFA400'>확인</font>"), null);
                newDialog.show();
            }
        });
    }


    private void showmovement() {

        service.getPost().enqueue(new Callback<movementdata>() {
            @Override
            public void onResponse(Call<movementdata> call, Response<movementdata> response) {
                movementdata result = response.body();
                String content = "";
                content += result.getResult();
                textViewResult.append(content);
            }
            @Override
            public void onFailure(Call <movementdata> call, Throwable t) {
                Log.e("movement data 불러오기 오류", t.getMessage());
                t.printStackTrace();
            }
        });
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

    private void setData3() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0.0f, 1));
        entries.add(new BarEntry(4.0f, 2.5f));
        entries.add(new BarEntry(8.0f, 3));
        entries.add(new BarEntry(12.0f, 3));
        entries.add(new BarEntry(16.0f, 3.3f));
        entries.add(new BarEntry(20.0f, 2));

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

    private void setData4() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0.0f, 1));
        entries.add(new BarEntry(4.0f, 2.0f));
        entries.add(new BarEntry(8.0f, 3.5f));
        entries.add(new BarEntry(12.0f, 4));
        entries.add(new BarEntry(16.0f, 3.3f));
        entries.add(new BarEntry(20.0f, 1.5f));

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
package com.ewha.myapplication;

import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


// 바차트 및 일간, 주간, 월간 세그먼트 버튼
import com.github.mikephil.charting.charts.BarChart;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BarChart barChart = (BarChart) findViewById(R.id.barchart);

        //프래그먼트 관련 선언
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        // 앱이 실행되면 첫 번째 프래그먼트 노출
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        // 탭이 선택되었을 때 이벤트를 받아 처리하는 함수
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab1:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment1).commit();

                        return true;
                    case R.id.tab2:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment2).commit();

                        return true;
                    case R.id.tab3:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment3).commit();

                        return true;
                }

                return false;
            }
        });
    }
}



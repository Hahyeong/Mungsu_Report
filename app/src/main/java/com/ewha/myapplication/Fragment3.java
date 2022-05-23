package com.ewha.myapplication;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class Fragment3 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment3, container, false);

        initUI(rootView);

        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        Button changeserver = rootView.findViewById(R.id.changeserver);
        Button changestreamkey = rootView.findViewById(R.id.changestreamkey);

        AlertDialog.Builder newDialog = new AlertDialog.Builder(rootView.getContext(), android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

        final EditText editserver = new EditText(rootView.getContext());
        final EditText editkey = new EditText(rootView.getContext());

        changeserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newDialog.setMessage("수집 서버 링크 변경");
                if (editserver.getParent() != null){
                    ((ViewGroup) editserver.getParent()).removeView(editserver);
                }
                newDialog.setView(editserver);
                newDialog.setPositiveButton(Html.fromHtml("<font color='#FFA400'>입력</font>"), null);
                newDialog.setNegativeButton(Html.fromHtml("<font color='#FFA400'>취소</font>"), null);
                newDialog.show();
            }
        });

        changestreamkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newDialog.setMessage("스트림 키 변경");
                if (editkey.getParent() != null){
                    ((ViewGroup) editkey.getParent()).removeView(editkey);
                }
                newDialog.setView(editkey);
                newDialog.setPositiveButton(Html.fromHtml("<font color='#FFA400'>입력</font>"), null);
                newDialog.setNegativeButton(Html.fromHtml("<font color='#FFA400'>취소</font>"), null);
                newDialog.show();
            }
        });
    }
}
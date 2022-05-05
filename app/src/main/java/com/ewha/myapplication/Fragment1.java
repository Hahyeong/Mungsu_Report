package com.ewha.myapplication;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// 웹뷰
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.webkit.WebSettings;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.MediaController;
import android.widget.VideoView;

import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// 프래그먼트 1 서버 소켓 test
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.lang.Runnable;

public class Fragment1 extends Fragment {


    // 프래그먼트 1 서버 소켓 test
    EditText editText;
    TextView textView2;
    TextView textView3;
    Handler handler = new Handler();

    // 웹뷰 test
    WebView webview1;
    WebSettings webSettings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);

        initUI(rootView);

        return rootView;
    }


    private void initUI(ViewGroup rootView) {
        webview1 = (WebView) rootView.findViewById(R.id.webview1);
        webview1.setWebViewClient(new WebViewClientClass());

        webSettings = webview1.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webview1.loadUrl("http://10.0.2.2:8080");
    }

    private class WebViewClientClass extends WebViewClient {
        // SSL 인증서 무시
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        // 페이지 내에서만 url 이동하게끔 만듬
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
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

// AWS IVS VIEWER
import com.amazonaws.ivs.player.*;

public class Fragment1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);

        initUI(rootView);

        return rootView;
    }

    // AWS IVS VIEWER
    PlayerView playerView;
    Player player;

    private void initUI(ViewGroup rootView) {
        playerView = rootView.findViewById(R.id.playerView);
        player = playerView.getPlayer();

        player.load(Uri.parse("https://fcc3ddae59ed.us-west-2.playback.live-video.net/api/video/v1/us-west-2.893648527354.channel.DmumNckWFTqz.m3u8"));
    }
}
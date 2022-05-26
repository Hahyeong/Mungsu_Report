package com.ewha.myapplication;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
    RecyclerView recyclerView;
    DiaryAdapter adapter;
    Context context;

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

        player.load(Uri.parse("https://3c90cab980bd.ap-northeast-2.playback.live-video.net/api/video/v1/ap-northeast-2.828771652167.channel.tvG5mIHLOH9t.m3u8"));

        //반려견 일기
        recyclerView = rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DiaryAdapter();

        adapter.addItem(new Diary("08:57", "밥을 먹었어요."));
        adapter.addItem(new Diary("09:14", "화장실을 갔다왔어요."));
        adapter.addItem(new Diary("12:38", "왈왈! 짖었어요."));
        adapter.addItem(new Diary("13:02", "왈왈! 짖었어요."));
        adapter.addItem(new Diary("13:07", "화장실을 갔다왔어요."));
        adapter.addItem(new Diary("15:21", "이상행동을 보였어요." + System.lineSeparator() + "리포트 탭에서 확인하세요!"));

        recyclerView.setAdapter(adapter);

    }
}

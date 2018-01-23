package com.example.cartoon.fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.cartoon.R;
import com.example.cartoon.image.BannerImageLoader;
import com.example.cartoon.stop.StopMusic;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 石头 on 2017/12/7.
 */

public class Radio_wode extends Fragment {
    private View view;
    private Banner ban;
    private MediaPlayer diaPlayer;
    private Button btn_zanting;
    private Button btn_kaishi;
    private StopMusic stopMusic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.radio_wode, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ban = (Banner) view.findViewById(R.id.jiahao_ban);
        btn_zanting = (Button) view.findViewById(R.id.btn_zanting);
        btn_kaishi = (Button) view.findViewById(R.id.btn_kaishi);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        stopMusic = new StopMusic(getActivity());
        stopMusic.start();

        btn_zanting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMusic.stop();
            }
        });
        btn_kaishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMusic.start();
            }
        });
        List<Integer> li = new ArrayList<>();
        //进行放入
        li.add(R.drawable.jj3);
        li.add(R.drawable.jj2);
        li.add(R.drawable.dian);
        li.add(R.drawable.jj4);
        li.add(R.drawable.jj5);
        li.add(R.drawable.jj6);
        li.add(R.drawable.jj7);
        li.add(R.drawable.jj8);
        li.add(R.drawable.jj10);
        ban.setImageLoader(new BannerImageLoader());
        ban.setImages(li);
        ban.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopMusic.stop();
    }
}

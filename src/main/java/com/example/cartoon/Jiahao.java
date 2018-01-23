package com.example.cartoon;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.cartoon.image.BannerImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class Jiahao extends AppCompatActivity implements View.OnClickListener {

    private Banner ban;
    /**
     * 点击返回
     */
    private Button jiahao_Fanhui;
    private Button jiahao_yinyue;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//音乐进来就播放
        mediaPlayer = MediaPlayer.create(Jiahao.this, R.raw.xiaoshihou);
        mediaPlayer.start();

        setContentView(R.layout.activity_jiahao);
        initView();
        List<Integer> li = new ArrayList<>();
//进行放入

        li.add(R.drawable.guaile);
        li.add(R.drawable.xiao2);
        li.add(R.drawable.xiao3);
        li.add(R.drawable.xiao4);
        li.add(R.drawable.xiao5);

        li.add(R.drawable.za);
        li.add(R.drawable.za1);
        li.add(R.drawable.za2);
        li.add(R.drawable.za3);
        li.add(R.drawable.za4);
        li.add(R.drawable.za5);
        li.add(R.drawable.za6);
        li.add(R.drawable.za7);
        li.add(R.drawable.za8);
        li.add(R.drawable.za9);
        li.add(R.drawable.za11);
        li.add(R.drawable.za12);
        li.add(R.drawable.za13);
        li.add(R.drawable.za14);
        li.add(R.drawable.za10);
        li.add(R.drawable.mza16);
        li.add(R.drawable.xiao7);
        ban.setImageLoader(new BannerImageLoader());
        ban.setImages(li);
        ban.start();


    }

    private void initView() {
        ban = (Banner) findViewById(R.id.jiahao_ban);
        jiahao_Fanhui = (Button) findViewById(R.id.jiahao_fanhui);
        jiahao_Fanhui.setOnClickListener(this);
        jiahao_yinyue = (Button) findViewById(R.id.jiahao_yinyue);
        jiahao_yinyue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.jiahao_fanhui:
                mediaPlayer.stop();
                finish();
                break;
            case R.id.jiahao_yinyue:
                mediaPlayer.stop();
                break;
        }
    }
}

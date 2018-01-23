package com.example.cartoon;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.cartoon.fragment.Radio_shouye;
import com.example.cartoon.fragment.Radio_wode;
import com.example.cartoon.fragment.Radio_xiaoxi;
import com.example.cartoon.fragment.Radio_xiazai;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout frame_Layout;
    /**
     * 首页
     */
    private RadioButton rb_Shouye;
    /**
     * 消息
     */
    private RadioButton rb_jiahao;
    /**
     * 加号
     */
    private RadioButton rb_Xiaoxi;
    /**
     * 下载
     */
    private RadioButton rb_Xiazai;
    /**
     * 我的
     */
    private RadioButton rb_Wode;
    private RadioGroup radio_Group;
    private ImageView draw_Img;
    private ListView draw_List;
    private LinearLayout menu;
    private DrawerLayout draw_layout;
    private List<String> datas;
    private MediaPlayer diaPlayer;
//    生日快乐歌曲  adiaPlayer
    private MediaPlayer adiaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Radio_shouye()).commit();
//        获得控件
        initView();
        draw_layout.openDrawer(menu);
        adiaPlayer = MediaPlayer.create(MainActivity.this, R.raw.shengri);
        adiaPlayer.start();

//        draw_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                adiaPlayer.stop();
//            }
//        });

        datas = new ArrayList<String>();
//        进行侧拉里面添加数据
        datas.add("祝");
        datas.add("妈");
        datas.add("妈");
        datas.add("生");
        datas.add("日");
        datas.add("快");
        datas.add("乐");
        datas.add("❤");
        datas.add("妈");
        datas.add("妈");
        datas.add("您");
        datas.add("辛");
        datas.add("苦");
        datas.add("了");
        datas.add("❤");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
        draw_List.setAdapter(adapter);

        draw_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adiaPlayer.stop();
                draw_layout.closeDrawer(menu);
            }
        });
    }

    private void initView() {
        frame_Layout = (FrameLayout) findViewById(R.id.frame_layout);
        frame_Layout.setOnClickListener(this);
        rb_Shouye = (RadioButton) findViewById(R.id.rb_shouye);
        rb_Shouye.setOnClickListener(this);
        rb_Xiaoxi = (RadioButton) findViewById(R.id.rb_xiaoxi);
        rb_Xiaoxi.setOnClickListener(this);
        rb_jiahao = (RadioButton) findViewById(R.id.rb_jiahao);
        rb_jiahao.setOnClickListener(this);
        rb_Xiazai = (RadioButton) findViewById(R.id.rb_xiazai);
        rb_Xiazai.setOnClickListener(this);
        rb_Wode = (RadioButton) findViewById(R.id.rb_wode);
        rb_Wode.setOnClickListener(this);
        radio_Group = (RadioGroup) findViewById(R.id.radio_group);
        draw_Img = (ImageView) findViewById(R.id.draw_img);
        draw_Img.setOnClickListener(this);
        draw_List = (ListView) findViewById(R.id.draw_list);
//        draw_List.setOnClickListener(this);
        menu = (LinearLayout) findViewById(R.id.menu);
        draw_layout = (DrawerLayout) findViewById(R.id.draw);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rb_shouye:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Radio_shouye()).commit();
//                diaPlayer.stop();
                break;
            case R.id.rb_xiaoxi:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Radio_xiaoxi()).commit();
//                diaPlayer.stop();
                break;
            case R.id.rb_jiahao:
                startActivity(new Intent(MainActivity.this, Jiahao.class));
//                diaPlayer.stop();
                break;
            case R.id.rb_xiazai:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Radio_xiazai()).commit();
//                diaPlayer.stop();
                break;
            case R.id.rb_wode:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Radio_wode()).commit();
//点击进行播放音乐
//                diaPlayer = MediaPlayer.create(MainActivity.this, R.raw.wenrouxiang);
//                diaPlayer.start();
                break;
            case R.id.draw_img:
                adiaPlayer.stop();
                draw_layout.closeDrawer(menu);
                break;
            case R.id.draw_list:
                draw_layout.closeDrawer(menu);
                break;
        }
    }
}

package com.example.cartoon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.cartoon.net.NetTypeUtils;

public class TiaoActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    int time = 5;
    /**
     * 11111111
     */
    private TextView text_tiao;
    private TextView tiaozhuan_dian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiao);
        initView();
//        给跳转二字设置点击事件  跳转
        tiaozhuan_dian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //首先判断有没有连接网络
                boolean result = NetTypeUtils.isConn(TiaoActivity.this);
                if (result) {
                    //进行数据请求
                    Intent intent = new Intent(TiaoActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                } else {
                    NetTypeUtils.openNetSettingDg(TiaoActivity.this);
                }
            }
        });
//利用倒计时跳转
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                text_tiao.setText(time + "" + "秒  ");
                time--;
                if (time == -2) {
                    text_tiao.setVisibility(View.GONE);
                    handler.removeCallbacksAndMessages(null);
                    //首先判断有没有连接网络
                    boolean result = NetTypeUtils.isConn(TiaoActivity.this);
                    if (result) {
                        //进行数据请求
                        Intent intent = new Intent(TiaoActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        return;
                    } else {
                        NetTypeUtils.openNetSettingDg(TiaoActivity.this);
                        handler.removeCallbacksAndMessages(null);
                    }
                }
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }
    private void initView() {
        text_tiao = (TextView) findViewById(R.id.tv);
        tiaozhuan_dian = (TextView) findViewById(R.id.tiaozhuan_dian);
    }
//    销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
//        Timer
    }
}


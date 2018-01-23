package com.example.cartoon;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cartoon.fragment.Radio_xiazai;

public class XiazaiSousuoActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 点我返回
     */
    private Button xizai_Fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiazai_sousuo);
        initView();
    }

    private void initView() {
        xizai_Fanhui = (Button) findViewById(R.id.xizai_fanhui);
        xizai_Fanhui.setOnClickListener(this);
    }

    @RequiresApi
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.xizai_fanhui:
//                Intent intent = new Intent(XiazaiSousuoActivity.this, MainActivity.class);
                finish();
                Toast.makeText(this, "我会认真的", Toast.LENGTH_SHORT).show();
//                startActivity(intent);
//                startActivity(new Intent(XiazaiSousuoActivity.this,Radio_xiazai.class));
//                                     startActivity(new Intent(getActivity(), XiazaiSousuoActivity.class));
                break;
        }
    }
}

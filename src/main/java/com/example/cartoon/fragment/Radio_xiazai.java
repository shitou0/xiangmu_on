package com.example.cartoon.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cartoon.R;
import com.example.cartoon.XiazaiSousuoActivity;

/**
 * Created by 石头 on 2017/12/7.
 */

public class Radio_xiazai extends Fragment  {
    private View view;
    /**
     * 搜索
     */
    private EditText xiazai_EditSousuo;
    /**
     * 确认
     */
    private Button xiazai_BtnQueren;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.radio_xiazai, container, false);


        initView(view);

        xiazai_BtnQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = xiazai_EditSousuo.getText().toString();

                if (s.isEmpty()){
                    Toast.makeText(getActivity(), "请在上面输入你想要的话，再点击", Toast.LENGTH_LONG).show();
                }else {
                    intent = new Intent(getActivity(), XiazaiSousuoActivity.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }

    private void initView(View view) {
        xiazai_EditSousuo = (EditText) view.findViewById(R.id.xiazai_edit_sousuo);
        xiazai_BtnQueren = (Button) view.findViewById(R.id.xiazai_btn_queren);
//        xiazai_BtnQueren.setOnClickListener(this);
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            default:
//                break;
//            case R.id.xiazai_btn_queren:
//                        if (xiazai_EditSousuo == null){
//                            Toast.makeText(getActivity(), "你到到底搜不搜索，搜索框不能为空啊,你是在逗我吗，找打！", Toast.LENGTH_LONG).show();
//                        }else {
//                            intent = new Intent(getActivity(), XiazaiSousuoActivity.class);
//                            startActivity(intent);
//                        }
//                break;
//        }
//    }
}

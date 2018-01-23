package com.example.cartoon.table;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cartoon.R;
import com.google.zxing.activity.CaptureActivity;
import com.google.zxing.common.BitmapUtils;

/**
 * Created by 石头 on 2017/12/7.
 */

public class Table_shanghai extends Fragment implements View.OnClickListener {
    private EditText mContent;
    private Button mCreate,mScan;
    private ImageView mImage;
    private final static int REQ_CODE = 1028;
    private TextView mHint;
    private TextView mResult;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.table_shanghai, container, false);


        initView(view);
        return view;
    }

    private void initView(View view) {
        mContent = (EditText)view.findViewById(R.id.edt_content);
        mCreate = (Button)view.findViewById(R.id.btn_create);
        mScan = (Button)view.findViewById(R.id.btn_scan);

        mImage = (ImageView)view.findViewById(R.id.iv_image);
        mHint = (TextView)view.findViewById(R.id.tv_hint);
        mResult = (TextView)view.findViewById(R.id.tv_result);

        mCreate.setOnClickListener(this);
        mScan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_create :
                //生成二维码
                String content = mContent.getText().toString().trim();
                Bitmap bitmap = null;
                try {
                    bitmap = BitmapUtils.create2DCode(content);
                    mImage.setVisibility(View.VISIBLE);
                    mHint.setVisibility(View.GONE);
                    mImage.setImageBitmap(bitmap);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_scan :
                //扫码
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent,REQ_CODE);
                break;

            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            mImage.setVisibility(View.VISIBLE);
            mContent.setVisibility(View.GONE);

            String result = data.getStringExtra(CaptureActivity.SCAN_QRCODE_RESULT);
            Bitmap bitmap = data.getParcelableExtra(CaptureActivity.SCAN_QRCODE_BITMAP);

            mResult.setText("扫描结果为：" + result);
            showToast("扫码结果："+result);
            if (bitmap != null) {
                mImage.setImageBitmap(bitmap);
            }

        }
    }

    private void showToast(String msg) {
        Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT).show();
    }
}
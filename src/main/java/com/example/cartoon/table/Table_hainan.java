package com.example.cartoon.table;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.cartoon.R;
import com.example.cartoon.adapter.Table_Hainan_Baseadapter;
import com.example.cartoon.bean.Table_Hainan_Bean;
import com.example.cartoon.image.BannerImageLoader;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.youth.banner.Banner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 石头 on 2017/12/7.
 */

public class Table_hainan extends Fragment {
    private View view;
    private PullToRefreshGridView pull_ref;
    //网址地址
    private String path = "http://v3.wufazhuce.com:8000/api/reading/index/?version=3.5.0&platform=android";
    //页数
    private int pageIndex = 1;
    //操作的类型 1：下拉刷新  2：上拉加载
    private int type = 1;
    //    给Bean包设置一个集合
    private List<Table_Hainan_Bean.DataBean.SerialBean> list = new ArrayList<>();
    private Table_Hainan_Bean myBean;
    private Table_Hainan_Baseadapter adapter;
    private Banner ban;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.table_hainan, container, false);
        initView(view);

        List<String> li=new ArrayList<>();
        li.add("http://image.wufazhuce.com/placeholder-author-avatar.png");
        li.add("http://image.wufazhuce.com/Fm7qZ68h0XjMceY1WWp7-ykCX29R");
        li.add("http://image.wufazhuce.com/placeholder-author-avatar.png");
        li.add("http://image.wufazhuce.com/placeholder-author-avatar.png");
        li.add("http://image.wufazhuce.com/FpkOOh3DQ-wgQ1gPCSXPKn8aqmBa");
        li.add("http://image.wufazhuce.com/Fic6UIE-X_7mwyvJ8rky2vjrQXxD");

        ban.setImageLoader(new BannerImageLoader());
        ban.setImages(li);
        ban.start();

        pull_ref.setMode(PullToRefreshBase.Mode.BOTH);
// 点击事件
        pull_ref.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> pullToRefreshBase) {
                type = 1;
                //重新请求第一页的数据 10条数据
                String path = "http://v3.wufazhuce.com:8000/api/reading/index/?version=3.5.0&platform=android" ;
                new asyncTask().execute(path);
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> pullToRefreshBase) {
                type = 2;
//                pageIndex++;
                //进行地址的拼接
                String path = "http://v3.wufazhuce.com:8000/api/reading/index/?version=3.5.0&platform=android";
                //执行异步请求
                new asyncTask().execute(path);
            }
        });
        new asyncTask().execute(path);
        return view;
    }
    class asyncTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            try {
//    获取网络
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
//   设置延时
                connection.setReadTimeout(5000);
                connection.setConnectTimeout(5000);
                int responseCode = connection.getResponseCode();
//  进行判断是否是 200
                if (responseCode == 200){
                    InputStream inputStream = connection.getInputStream();
//  进行解析
                    String s = streamToString(inputStream, "utf-8");
                    return s;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        //相当于主线程
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//获得利用 gson
            Gson gson = new Gson();
            myBean = gson.fromJson(s, Table_Hainan_Bean.class);

//代表刷新
            if (type == 1) {
//如果是刷新的话
                list.clear();//清除原有的数据
            }

            setAdapter();list.addAll(myBean.getData().getSerial());//添加新的数据
            pull_ref.onRefreshComplete();
        }
    }
    // 定义一个适配器
    public void setAdapter() {
        if (adapter == null) {
            Log.d("SSSSSSSSSSSSSS", "+++++++listtttttt++++++++" + list);
//            adapter = new List_Baseadapter(getActivity(), list);
            adapter = new Table_Hainan_Baseadapter(getActivity(),list);

            pull_ref.setAdapter(adapter);
        } else {
            //否则刷新适配器
            adapter.notifyDataSetChanged();
        }
    }
    //解析数据
    private String streamToString(InputStream inputStream, String s) {
//        进行trycatch
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String a = "";
            StringBuffer stringBuffer = new StringBuffer();
            while ((a = bufferedReader.readLine()) != null) {
                stringBuffer.append(a);
            }
            bufferedReader.close();
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private void initView(View view) {
        pull_ref = (PullToRefreshGridView) view.findViewById(R.id.hainan_grid);
        ban = (Banner) view.findViewById(R.id.ban);
    }
}

package com.example.cartoon.table;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cartoon.R;
import com.example.cartoon.adapter.Table_shipin_Baseadapter;
import com.example.cartoon.bean.Table_ShipinMybean;
import com.example.cartoon.view.XListView;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 石头 on 2017/12/7.
 */

public class Table_shipin extends Fragment {
    private View view;
    private XListView x_list;
//    网络地址
    private String path ="http://gank.io/api/data/Android/10/1";
    //页数
    private int pageIndex = 1;
    //操作的类型 1：下拉刷新  2：上拉加载
    private int type = 1;
    //    给Bean包设置一个集合
    private List<Table_ShipinMybean.ResultsBean> list = new ArrayList<>();
    private Table_shipin_Baseadapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.table_shipin, container, false);
        initView(view);
//        设置上拉加载
        x_list.setPullLoadEnable(true);
        //设置支持下拉刷新
        x_list.setPullRefreshEnable(true);
        x_list.setXListViewListener(new XListView.IXListViewListener() {
//            刷新
            @Override
            public void onRefresh() {
                type = 1;
                //重新请求第一页的数据 10条数据
                pageIndex = 1;
                String path = "http://gank.io/api/data/Android/10/" + pageIndex;
                new asyncTask().execute(path);
            }
//加载
            @Override
            public void onLoadMore() {
                type = 2;
                pageIndex++;
                //进行地址的拼接
                String path = "http://gank.io/api/data/Android/10/" + pageIndex;
                //执行异步请求
                new asyncTask().execute(path);
            }
        });

        new asyncTask().execute(path);
        return view;
    }
    class asyncTask extends AsyncTask<String,Void,String>{

//子线程
        @Override
        protected String doInBackground(String... params) {

            try {
                URL url = new URL(params[0]);
                 HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                int responseCode = connection.getResponseCode();
                if (responseCode == 200){
                    InputStream inputStream = connection.getInputStream();
                    String s = StreamToString(inputStream,"UTF-8");
                    return s;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
//主线程
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Gson gson = new Gson();
            Table_ShipinMybean myBean = gson.fromJson(s, Table_ShipinMybean.class);

            if (type == 1) {//代表刷新
                //如果是刷新的话
                list.clear();//清除原有的数据
                list.addAll(myBean.getResults());//添加新的数据
                setAdapter();
                //关闭xlistview的头部视图--延迟关闭
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        x_list.stopRefresh();//关闭
                        // 获得此时此刻时间
                        Date date = new Date(System.currentTimeMillis());
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String s = sdf.format(date);
                        x_list.setRefreshTime(s);//设置刷新时间
                    }
                }, 1000);
            } else if (type == 2) {//代表加载更多
                //得到数据
                list.addAll(myBean.getResults());//datas.addAll(0,result.getData());//将新请求的数据添加到集合的最前面
                //设置适配器
                setAdapter();
                //关闭xlistview的底部视图--延迟关闭
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        x_list.stopLoadMore();
                    }
                }, 1000);
            } else {
            }

        }
    }
    public void setAdapter() {
        if (adapter == null) {
            Log.d("SSSSSSSSSSSSSS", "+++++++listtttttt++++++++" + list);
            adapter = new Table_shipin_Baseadapter(getActivity(), list);

            x_list.setAdapter(adapter);
        } else {
            //否则刷新适配器
            adapter.notifyDataSetChanged();
        }
    }
//解析
    private String StreamToString(InputStream inputStream, String s) {

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
        x_list = (XListView) view.findViewById(R.id.tab_shipin_Xlistview);
    }
}

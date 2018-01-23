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
import com.example.cartoon.adapter.Table_Beijing_Baseadapter;
import com.example.cartoon.bean.Table_Beijing;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

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

public class Table_beijing extends Fragment {
    private View view;
    private PullToRefreshGridView pull_ref;
    //网址地址
    private String path = "http://ic.snssdk.com/2/article/v25/stream/?category=news_world&count=20&min_behot_time=1455523059&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455523440&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";
    //页数
    private int pageIndex = 1;
    //操作的类型 1：下拉刷新  2：上拉加载
    private int type = 1;
    //    给Bean包设置一个集合
    private List<Table_Beijing.DataBean> list = new ArrayList<>();
    private Table_Beijing myBean;
    private Table_Beijing_Baseadapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.table_beijing, container, false);
        initView(view);
// 设置Mode   Mode设置为Mode.BOTH后，下拉和上拉都会执行onRefresh()中的方法了
       /* pull_ref.setMode(PullToRefreshBase.Mode.BOTH);
       然后重写两个方法
        pull_ref.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
        1.向下
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> pullToRefreshBase) {
            }
        2.向上
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> pullToRefreshBase) {
            }
        });*/

// 设置Mode   Mode设置为Mode.BOTH后，下拉和上拉都会执行onRefresh()中的方法了
        pull_ref.setMode(PullToRefreshBase.Mode.BOTH);
// 点击事件
        pull_ref.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> pullToRefreshBase) {
                type = 1;
   //重新请求第一页的数据 10条数据
                String path = "http://ic.snssdk.com/2/article/v25/stream/?category=news_world&count=20&min_behot_time=1455523059&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455523440&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000" ;
                new asyncTask().execute(path);
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> pullToRefreshBase) {
                type = 2;
//                pageIndex++;
   //进行地址的拼接
                String path = "http://ic.snssdk.com/2/article/v25/stream/?category=news_world&count=20&min_behot_time=1455523059&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455523440&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";
   //执行异步请求
                new asyncTask().execute(path);
            }
        });
        new asyncTask().execute(path);
        return view;
    }
    class asyncTask extends AsyncTask<String,Void,String>{
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
            myBean = gson.fromJson(s, Table_Beijing.class);
//代表刷新
            if (type == 1) {
//如果是刷新的话
                list.clear();//清除原有的数据
            }
            list.addAll(myBean.getData());//添加新的数据
            setAdapter();
            pull_ref.onRefreshComplete();
        }
    }
// 定义一个适配器
    public void setAdapter() {
        if (adapter == null) {
            Log.d("SSSSSSSSSSSSSS", "+++++++listtttttt++++++++" + list);
//            adapter = new List_Baseadapter(getActivity(), list);
            adapter = new Table_Beijing_Baseadapter(getActivity(),list);

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
//获得控件
    private void initView(View view) {
        pull_ref = (PullToRefreshGridView) view.findViewById(R.id.beijing_pull);
    }
}

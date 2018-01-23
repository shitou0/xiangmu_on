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
import android.widget.AdapterView;

import com.example.cartoon.R;
import com.example.cartoon.adapter.Guanzhu_Baseadapter;
import com.example.cartoon.bean.Guanzhu_MyBean;
import com.example.cartoon.view.XListView;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 石头 on 2017/12/7.
 */

public class Table_tuijian extends Fragment {
    private View view;
    private XListView x_list;
    //页数
    private int pageIndex = 1;
    //操作的类型 1：下拉刷新  2：上拉加载
    private int type = 1;
    //    给Bean包设置一个集合
    private List<Guanzhu_MyBean.DataBean> list = new ArrayList<>();
    private Guanzhu_Baseadapter adapter;
    private Guanzhu_MyBean myBean;

    private String path = "http://ic.snssdk.com/2/article/v25/stream/?category=news_tech&count=20&min_behot_time=1455522427&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455522784&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.table_tuijian, container, false);
        initView(view);
        //        设置上拉加载
        x_list.setPullLoadEnable(true);
        //设置支持下拉刷新
        x_list.setPullRefreshEnable(true);


        x_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
//给xlist获得的空间设置一个点击事件
        x_list.setXListViewListener(new XListView.IXListViewListener() {
            //            刷新
            @Override
            public void onRefresh() {
                type = 1;
                //重新请求第一页的数据 10条数据
                pageIndex = 1;
                String path = "http://ic.snssdk.com/2/article/v25/stream/?category=news_tech&count=20&min_behot_time=1455522427&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455522784&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";
                new asyncTask().execute(path);
            }

            //加载
            @Override
            public void onLoadMore() {
                type = 2;
                pageIndex++;
                //进行地址的拼接
                String path = "http://ic.snssdk.com/2/article/v25/stream/?category=news_tech&count=20&min_behot_time=1455522427&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455522784&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000" ;
                //执行异步请求
                new asyncTask().execute(path);
            }
        });
        new asyncTask().execute(path);
        return view;
    }

    class asyncTask extends AsyncTask<String, Void, String> {
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
                Log.d("SSS", "++++++++++++++++++" + responseCode);
//                判断是否是200响应码
                if (responseCode == 200) {
//解析
                    InputStream inputStream = connection.getInputStream();
                    String s = streamToString(inputStream, "utf-8");
                    Log.d("SSS", "+++++sssss++++" + s);
                    return s;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        // 主线程
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Gson gson = new Gson();
            myBean = gson.fromJson(s, Guanzhu_MyBean.class);

            if (type == 1) {//代表刷新
                //如果是刷新的话
                list.clear();//清除原有的数据
                list.addAll(myBean.getData());//添加新的数据
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
                list.addAll(myBean.getData());//datas.addAll(0,result.getData());//将新请求的数据添加到集合的最前面
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
            adapter = new Guanzhu_Baseadapter(getActivity(), list);
            x_list.setAdapter(adapter);
        } else {
            //否则刷新适配器
            adapter.notifyDataSetChanged();
        }
    }
    //解析数据
    private String streamToString(InputStream inputStream, String s) {
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
        x_list = (XListView) view.findViewById(R.id.guanzhu_XListView);
    }
}

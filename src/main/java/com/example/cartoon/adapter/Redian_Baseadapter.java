package com.example.cartoon.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cartoon.bean.Table_Redian_Bean;

import java.util.List;

/**
 * Created by 石头 on 2017/12/8.
 */

public class Redian_Baseadapter extends BaseAdapter {

    Context context;
    List<Table_Redian_Bean.DataBean> list;

    public Redian_Baseadapter(Context context, List<Table_Redian_Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }
//    List<Table_Redian_Bean.ResultBean.DataBean> list;


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, android.R.layout.activity_list_item, null);
        }
        TextView text1 = (TextView) convertView.findViewById(android.R.id.text1);
        text1.setText(list.get(position).getTitle());
        return convertView;
    }
}

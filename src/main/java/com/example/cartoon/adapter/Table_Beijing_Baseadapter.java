package com.example.cartoon.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cartoon.R;
import com.example.cartoon.bean.Table_Beijing;

import java.util.List;

/**
 * Created by 石头 on 2017/12/13.
 */

public class Table_Beijing_Baseadapter extends BaseAdapter {


    Context context;
    List<Table_Beijing.DataBean> list;

    public Table_Beijing_Baseadapter(Context context, List<Table_Beijing.DataBean> list) {
        this.context = context;
        this.list = list;
    }

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
        viewHolder holder;
        if (convertView == null){
            holder = new viewHolder();
            convertView=View.inflate(context, R.layout.item_table_beijing,null);
            holder.beijing_name = (TextView) convertView.findViewById(R.id.beijing_name);
            holder.beijing_neirong = (TextView) convertView.findViewById(R.id.beijing_neirong);
            convertView.setTag(holder);
        }else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.beijing_name.setText(list.get(position).getTitle());
        holder.beijing_neirong.setText(list.get(position).getAbstractX());
        return convertView;
    }
    class viewHolder{
        TextView beijing_name;
        TextView beijing_neirong;

    }
}

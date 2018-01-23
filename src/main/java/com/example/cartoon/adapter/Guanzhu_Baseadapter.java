package com.example.cartoon.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cartoon.R;
import com.example.cartoon.bean.Guanzhu_MyBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

/**
 * Created by 石头 on 2017/12/7.
 */

public class Guanzhu_Baseadapter extends BaseAdapter {
    Context context;
    List<Guanzhu_MyBean.DataBean> list;

    public Guanzhu_Baseadapter(Context context, List<Guanzhu_MyBean.DataBean> list) {
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
        if (convertView == null) {
            holder = new viewHolder();
            convertView = View.inflate(context, R.layout.item_table_tuijian2, null);
            holder.item_text11 = (TextView) convertView.findViewById(R.id.item_title11);
            holder.item_imtro1 = (TextView) convertView.findViewById(R.id.item_imtro11);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.item_text11.setText(list.get(position).getTitle());
//        holder.item_imtro1.setText(Integer.parseInt(list.get(position).getImtro().trim()));
        holder.item_imtro1.setText(list.get(position).getAbstractX());
        return convertView;

    }
    class viewHolder {
        TextView item_text11;
        TextView item_imtro1;
    }
}

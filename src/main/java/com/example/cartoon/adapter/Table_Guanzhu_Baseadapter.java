package com.example.cartoon.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cartoon.R;
import com.example.cartoon.bean.Table_Guanzhu_Mybean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

/**
 * Created by 石头 on 2017/12/9.
 */

public class Table_Guanzhu_Baseadapter extends BaseAdapter {

    Context context;
    List<Table_Guanzhu_Mybean.DataBean> list;

    public Table_Guanzhu_Baseadapter(Context context, List<Table_Guanzhu_Mybean.DataBean> list) {
        this.context = context;
        this.list = list;
//重点
        options=new DisplayImageOptions.Builder()
                .cacheInMemory(true)//使用内存缓存
                .cacheOnDisk(true)//使用磁盘缓存
                .showImageOnLoading(R.mipmap.ic_launcher)//设置正在下载的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)//url为空或请求的资源不存在时
                .showImageOnFail(R.mipmap.ic_launcher)//下载失败时显示的图片
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片格式
                .displayer(new RoundedBitmapDisplayer(20))//设置圆角图片
                .build();
    }

    //2.设置显示图片的类
    // 重点
    private DisplayImageOptions options;


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
            convertView = View.inflate(context, R.layout.item_table_guanzhu,null);

//            holder.item_image = (ImageView) convertView.findViewById(R.id.item_image);
            holder.item_text1 = (TextView) convertView.findViewById(R.id.item_text1);
            holder.item_time = (TextView) convertView.findViewById(R.id.item_time);
            convertView.setTag(holder);
        }else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.item_text1.setText(list.get(position).getSource());
        holder.item_time.setText(list.get(position).getAbstractX());

        //获取图片路径
//        String picPath=list.get(position).getThumbnail_pic_s();

        //使用ImageLoader框架  options参数是上面有参里面自己定义的一个
//        ImageLoader.getInstance().displayImage(picPath,holder.item_image,options);

        return convertView;
    }
    class viewHolder{
        ImageView item_image;
        TextView item_text1;
        TextView item_time;

    }
}

package com.example.cartoon.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cartoon.R;
import com.example.cartoon.bean.Table_ShipinMybean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

/**
 * Created by 石头 on 2017/12/9.
 */

public class Table_shipin_Baseadapter extends BaseAdapter {

    Context context;
    List<Table_ShipinMybean.ResultsBean> list;

    public Table_shipin_Baseadapter(Context context, List<Table_ShipinMybean.ResultsBean> list) {
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

    //只有文本
    private final int ONLY_TITLE=0;
    //有图片且在右边
    private final int ONLY_IMAGE_RIGHT=1;
    //2.设置显示图片的类
    // 重点
    private DisplayImageOptions options;





    @Override
    public int getItemViewType(int position) {
        //根据数据来判断，显示哪种类型
        List<String> picurls=list.get(position).getImages();
        if(picurls==null){
            return ONLY_TITLE;//返回文本类型
        }else if(picurls!=null && picurls.size()==1){//集合长度是1
            return  ONLY_IMAGE_RIGHT;//返回图片类型
        }else{
            return ONLY_TITLE;//返回文本类型
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
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
        //判断条目的类型
        int type=getItemViewType(position);
        Log.d("zzz","type:"+type+" position:"+position);
//进行判断  只有文字
        if(type==ONLY_TITLE){
            ViewHolderTitle holderTitle;
            if(convertView==null){
                holderTitle=new ViewHolderTitle();
                convertView=View.inflate(context,R.layout.item_table_shipin_title,null);
                holderTitle.tvTitle=(TextView) convertView.findViewById(R.id.item_text1);
                convertView.setTag(holderTitle);
            }else{
                holderTitle=(ViewHolderTitle) convertView.getTag();
            }
            //显示数据
            holderTitle.tvTitle.setText(list.get(position).getDesc());
            return  convertView;

// 否则有图片
        }else if(type==ONLY_IMAGE_RIGHT){
            ViewHolderImage holderImage;
            if(convertView==null){
                holderImage=new ViewHolderImage();
                convertView=View.inflate(context,R.layout.item_table_shipin_img,null);
                holderImage.tvTitle=(TextView) convertView.findViewById(R.id.item_text1);
                holderImage.img=(ImageView) convertView.findViewById(R.id.item_image);

                convertView.setTag(holderImage);
            }else{
                holderImage=(ViewHolderImage) convertView.getTag();
            }
            //显示数据
            holderImage.tvTitle.setText(list.get(position).getDesc());
            //显示图片
            ImageLoader.getInstance().displayImage(list.get(position).getImages().get(0),holderImage.img,options);
            Log.d("zzz","type:"+type+"   "+list.get(position).getImages().get(0));
            return  convertView;
        }else{
        }
        return convertView;
    }
}
//    有几个分类就建立几个  ViewHolder
class ViewHolderTitle{
    TextView tvTitle;
}
class ViewHolderImage{
    ImageView img;
    TextView tvTitle;
}

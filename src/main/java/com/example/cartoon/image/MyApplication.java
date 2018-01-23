package com.example.cartoon.image;

import android.app.Application;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * Created by 石头 on 2017/12/9.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("SSSS","程序入口点");
        //sd卡上缓存目录-android指定的缓存路径 android/data/<package>/cache
        File cachefile=getExternalCacheDir();
        //自定义的缓存路径
//        File cachefile=new File(Environment.getExternalStorageDirectory().getPath()+"/abc/a");
        //进行框架初使化操作-全局配置
        assert cachefile != null;
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(480, 800)//缓存图片最大的长和宽
                .threadPoolSize(2)//线程池的数量
                .threadPriority(4)
                .memoryCacheSize(5*1024*1024)//设置内存缓存区大小
                .diskCacheSize(20*1024*1024)//设置sd卡缓存区大小
                .diskCache(new UnlimitedDiscCache(cachefile))//自定义磁盘缓存目录
                .writeDebugLogs()//打印日志内容
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//给缓存的文件名进行md5加密处理
                .build();
        ImageLoader.getInstance().init(configuration);
    }
}

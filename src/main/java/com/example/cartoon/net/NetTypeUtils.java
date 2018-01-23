package com.example.cartoon.net;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**网络工具类
 * Created by 石头 on 2017/12/9.
 */

public class NetTypeUtils { public static boolean isConn(Context context){

    //1.得到网络判断的系统服务
    ConnectivityManager manager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    //2.得到网络信息类
    NetworkInfo info=manager.getActiveNetworkInfo();
    if(info!=null && info.isAvailable()){
        return true;
    }else{
        return false;
    }
}
    public static  void openNetSettingDg(final Context context){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("设置网络");
        builder.setMessage("是否要打开网络连接？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //跳转到设置页面
                Intent intent;
                // 先判断当前系统版本
                if(android.os.Build.VERSION.SDK_INT > 10){  // 3.0以上
                    intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                }else{
                    intent = new Intent();
                    intent.setClassName("com.android.settings", "com.android.settings.WirelessSettings");
                }
                context.startActivity(intent);
            }
        });
        builder.setNegativeButton("取消",null);

        builder.show();
    }
}

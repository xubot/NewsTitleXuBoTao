package com.example.administrator.newstitlexubotao.Uilt;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2017/2/20.
 */

public class Connectivity {
    public static Boolean connectivity(Context context)
    {
        //得到网络的管理类
        ConnectivityManager service = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //得到网路的状态
        NetworkInfo network = service.getActiveNetworkInfo();
        if(network!=null&&network.isConnected())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

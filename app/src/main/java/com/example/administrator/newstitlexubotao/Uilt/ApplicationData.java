package com.example.administrator.newstitlexubotao.Uilt;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import org.xutils.x;

/**
 * Created by Administrator on 2017/2/16.
 */

public class ApplicationData extends Application{
    public   static  boolean flag;
    @Override
    public void onCreate() {
        flag=true;
        //初始化xutils
        x.Ext.init(this);
        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(build);

        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.d("zzz", deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
        // 默认设置为日间模式
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}

package com.example.administrator.newstitlexubotao.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.newstitlexubotao.Activity.CollectActivity;
import com.example.administrator.newstitlexubotao.Activity.LoginActivity;
import com.example.administrator.newstitlexubotao.Activity.MainActivity;
import com.example.administrator.newstitlexubotao.R;
import com.example.administrator.newstitlexubotao.Uilt.EventbusData;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/2/10.
 */

public class MineFragment extends Fragment{
    private static final String TAG = "MainActivity";
    private static final String APP_ID = "1105602574";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;
    private ImageView imageqq;
    private ImageView imagearow;
    private ImageView imagepione;
    private ImageView imageweixin;
    private TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.mine_fragment, null);
        //传入参数APPID和全局Context上下文
        mTencent = Tencent.createInstance(APP_ID,getActivity().getApplicationContext());
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        LinearLayout linearStar= (LinearLayout) inflate.findViewById(R.id.linearStar);
        linearStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),CollectActivity.class));
            }
        });
        tv = (TextView) inflate.findViewById(R.id.tv);
        imageweixin = (ImageView) inflate.findViewById(R.id.imageweixin);
        //QQ登录
        imageqq = (ImageView) inflate.findViewById(R.id.imageqq);
        imageqq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
                 官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
                 第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
                mIUiListener = new BaseUiListener();
                //all表示获取所有权限
                mTencent.login(MineFragment.this,"all", mIUiListener);
            }
        });

        //注册
        imagearow = (ImageView) inflate.findViewById(R.id.imagearow);
        imagearow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                //startActivity(new Intent(getActivity(), LoginActivity.class));
                startActivityForResult(intent,1000);
            }
        });

        //手机登录
        imagepione = (ImageView) inflate.findViewById(R.id.imagepione);
        imagepione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        //夜间
        LinearLayout linearLayout= (LinearLayout) inflate.findViewById(R.id.linearNight);
        EventBus.getDefault().post(new EventbusData(linearLayout));
    }

    /**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            Toast.makeText(getActivity(), "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken,expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getContext(),qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG,response.toString());
                        JSONObject object=(JSONObject) response;
                        String nickname = object.optString("nickname");
                        String figureurl_qq_2 = object.optString("figureurl_qq_2");
                        Log.e(TAG,nickname+"    "+figureurl_qq_2 );
                        imagepione.setVisibility(View.GONE);
                        imageqq.setVisibility(View.GONE);
                        imagearow.setVisibility(View.GONE);
                        ImageLoader.getInstance().displayImage(figureurl_qq_2,imageweixin);
                        tv.setText(nickname);
                    }
                    @Override
                    public void onError(UiError uiError) {

                        Log.e(TAG,"登录失败"+uiError.toString());
                    }
                    @Override
                    public void onCancel() {
                        Log.e(TAG,"登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(getActivity(), "授权失败",  Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(getActivity(), "授权取消", Toast.LENGTH_SHORT).show();

        }
    }
    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_LOGIN){
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

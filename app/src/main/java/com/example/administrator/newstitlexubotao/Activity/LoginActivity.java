package com.example.administrator.newstitlexubotao.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.newstitlexubotao.R;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

import static anetwork.channel.http.NetworkSdkSetting.context;

public class LoginActivity extends AppCompatActivity {

    private EditText phone1;
    private String phone;
    private TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone1 = (EditText) findViewById(R.id.phone);
        log = (TextView) findViewById(R.id.log);
        SMSSDK.initSDK(this, "1c2290f43e7d8", "c2d399adba7fc3ecb86dec402d533fbc");
        final TextView code = (TextView) findViewById(R.id.code);
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开注册页面
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
                        // 解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            @SuppressWarnings("unchecked")
                            HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country");
                            phone = (String) phoneMap.get("phone");
                            Log.d("zzz", country + "       " + phone);
                        }
                    }
                });
                registerPage.show(LoginActivity.this);
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phone1.getText().toString().equals(phone))
                {
                    Toast.makeText(LoginActivity.this, "aaaaaa", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    //startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    setResult(2000,intent);
                    finish();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "ssssss", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
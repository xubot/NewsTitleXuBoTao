package com.example.administrator.newstitlexubotao.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.administrator.newstitlexubotao.R;
import com.example.administrator.newstitlexubotao.Uilt.Connectivity;
import com.example.administrator.newstitlexubotao.fragment.HomeFragment;
import com.example.administrator.newstitlexubotao.fragment.NetworkInfo;

public class WebViewActivity extends AppCompatActivity {

    private Boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent intent = getIntent();
        String url = intent.getStringExtra("key");
        WebView webView= (WebView) findViewById(R.id.webView);
        //得到网络类
        flag = Connectivity.connectivity(this);
        if(flag)
        {
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
            settings.setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            if(url==null)
            {
                Toast.makeText(this, "暂无详细信息", Toast.LENGTH_SHORT).show();
            }
            webView.loadUrl(url);
        }
        else
        {
           webView.setBackgroundResource(R.drawable.not_found_loading);
           Toast.makeText(this, "你已经进入了无网次元", Toast.LENGTH_SHORT).show();
        }
    }
}

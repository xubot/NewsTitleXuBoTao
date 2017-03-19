package com.example.administrator.newstitlexubotao.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.administrator.newstitlexubotao.R;
import com.example.administrator.newstitlexubotao.Uilt.Connectivity;
import com.example.administrator.newstitlexubotao.Uilt.SildingFinishLayout;
import com.example.administrator.newstitlexubotao.fragment.HomeFragment;
import com.example.administrator.newstitlexubotao.fragment.NetworkInfo;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class WebViewActivity extends SwipeBackActivity {
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setScrimColor(Color.TRANSPARENT);
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);


        Intent intent = getIntent();
        String url = intent.getStringExtra("key");
        WebView webView= (WebView) findViewById(R.id.webView);
        //得到网络类
        Boolean flag= Connectivity.connectivity(this);
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
    @Override
    public void onBackPressed() {
        scrollToFinishActivity();
    }
}

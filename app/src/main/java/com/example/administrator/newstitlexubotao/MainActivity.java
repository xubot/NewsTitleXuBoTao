package com.example.administrator.newstitlexubotao;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.newstitlexubotao.fragment.LinkMan_Fragment;
import com.example.administrator.newstitlexubotao.fragment.Message_Fragment;
import com.example.administrator.newstitlexubotao.fragment.Mine_Fragment;
import com.example.administrator.newstitlexubotao.fragment.Space_Fragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity  implements View.OnClickListener{
    private List<TextView> textViewList=new ArrayList<>();
    private TextView message;
    private TextView linkman;
    private TextView space;
    private TextView mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflateView();
        //初次页面
        addFragment(new Message_Fragment());
        message.setTextColor(Color.RED);

    }
    //布局控件
    public void inflateView() {
        //得到控件
        message = (TextView) findViewById(R.id.message);
        linkman = (TextView) findViewById(R.id.linkman);
        space = (TextView) findViewById(R.id.space);
        mine = (TextView) findViewById(R.id.mine);
        textViewList.add(message);
        textViewList.add(linkman);
        textViewList.add(space);
        textViewList.add(mine);
        for(TextView tv:textViewList)
        {
            tv.setOnClickListener(this);
        }
    }
    //添加fragment布局
    public void addFragment(Fragment fragment) {
        //得到fragment的管理类
        FragmentManager manager = getSupportFragmentManager();
        //开启一个事务
        FragmentTransaction replace = manager.beginTransaction().replace(R.id.framelayout,fragment);
        //提交事务
        replace.commit();
    }
    //控件的点击监听
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.message :
                addFragment(new Message_Fragment());
                message.setTextColor(Color.RED);
                linkman.setTextColor(Color.BLACK);
                space.setTextColor(Color.BLACK);
                mine.setTextColor(Color.BLACK);
                break;
            case R.id.linkman :
                addFragment(new LinkMan_Fragment());
                linkman.setTextColor(Color.RED);
                message.setTextColor(Color.BLACK);
                space.setTextColor(Color.BLACK);
                mine.setTextColor(Color.BLACK);
                break;
            case R.id.space :
                addFragment(new Space_Fragment());
                space.setTextColor(Color.RED);
                linkman.setTextColor(Color.BLACK);
                message.setTextColor(Color.BLACK);
                mine.setTextColor(Color.BLACK);
                break;
            case R.id.mine :
                addFragment(new Mine_Fragment());
                mine.setTextColor(Color.RED);
                linkman.setTextColor(Color.BLACK);
                space.setTextColor(Color.BLACK);
                message.setTextColor(Color.BLACK);
                break;
        }
    }
}

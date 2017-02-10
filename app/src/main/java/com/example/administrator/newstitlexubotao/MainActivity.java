package com.example.administrator.newstitlexubotao;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.newstitlexubotao.fragment.LinkMan_Fragment;
import com.example.administrator.newstitlexubotao.fragment.Message_Fragment;
import com.example.administrator.newstitlexubotao.fragment.Mine_Fragment;
import com.example.administrator.newstitlexubotao.fragment.Space_Fragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity  implements View.OnClickListener{
    private List<LinearLayout> linearLayoutList=new ArrayList<>();
    private TextView message;
    private TextView linkman;
    private TextView space;
    private TextView mine;
    private ImageView imagemessage;
    private ImageView imagelinkman;
    private ImageView imagespace;
    private ImageView imagemine;

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
        LinearLayout lmessage= (LinearLayout) findViewById(R.id.llmessage);
        LinearLayout llinkman= (LinearLayout) findViewById(R.id.lllinkman);
        LinearLayout lspace= (LinearLayout) findViewById(R.id.llspace);
        LinearLayout lmine= (LinearLayout) findViewById(R.id.llmine);
        message = (TextView) findViewById(R.id.message);
        linkman = (TextView) findViewById(R.id.linkman);
        space = (TextView) findViewById(R.id.space);
        mine = (TextView) findViewById(R.id.mine);
        imagemessage = (ImageView) findViewById(R.id.imagemessage);
        imagelinkman = (ImageView) findViewById(R.id.imagelinkman);
        imagespace = (ImageView) findViewById(R.id.imagespace);
        imagemine = (ImageView) findViewById(R.id.imagemine);
        linearLayoutList.add(lmessage);
        linearLayoutList.add(llinkman);
        linearLayoutList.add(lspace);
        linearLayoutList.add(lmine);
        for(LinearLayout lll:linearLayoutList)
        {
            lll.setOnClickListener(this);
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
            case R.id.llmessage :
                addFragment(new Message_Fragment());
                message.setTextColor(Color.RED);
                linkman.setTextColor(Color.BLACK);
                space.setTextColor(Color.BLACK);
                mine.setTextColor(Color.BLACK);
                imagemessage.setImageResource(R.drawable.my_lottery_icon);
                imagelinkman.setImageResource(R.drawable.wait_pay);
                imagespace.setImageResource(R.drawable.wait_refund);
                imagemine.setImageResource(R.drawable.bottom_tab_user_update);
                break;
            case R.id.lllinkman :
                addFragment(new LinkMan_Fragment());
                linkman.setTextColor(Color.RED);
                message.setTextColor(Color.BLACK);
                space.setTextColor(Color.BLACK);
                mine.setTextColor(Color.BLACK);
                imagemessage.setImageResource(R.drawable.wait_evaluate);
                imagelinkman.setImageResource(R.drawable.public_contact_service_icon);
                imagespace.setImageResource(R.drawable.wait_refund);
                imagemine.setImageResource(R.drawable.bottom_tab_user_update);
                break;
            case R.id.llspace :
                addFragment(new Space_Fragment());
                space.setTextColor(Color.RED);
                linkman.setTextColor(Color.BLACK);
                message.setTextColor(Color.BLACK);
                mine.setTextColor(Color.BLACK);
                imagemessage.setImageResource(R.drawable.wait_evaluate);
                imagelinkman.setImageResource(R.drawable.wait_pay);
                imagespace.setImageResource(R.drawable.my_collection_icon);
                imagemine.setImageResource(R.drawable.bottom_tab_user_update);
                break;
            case R.id.llmine :
                addFragment(new Mine_Fragment());
                mine.setTextColor(Color.RED);
                linkman.setTextColor(Color.BLACK);
                space.setTextColor(Color.BLACK);
                message.setTextColor(Color.BLACK);
                imagemessage.setImageResource(R.drawable.wait_evaluate);
                imagelinkman.setImageResource(R.drawable.wait_pay);
                imagespace.setImageResource(R.drawable.wait_refund);
                imagemine.setImageResource(R.drawable.bottom_tab_user_selected);
                break;
        }
    }
}

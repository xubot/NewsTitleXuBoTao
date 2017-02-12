package com.example.administrator.newstitlexubotao.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.newstitlexubotao.Adater.Message_titleAdater;
import com.example.administrator.newstitlexubotao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/10.
 */

public class Message_Fragment extends Fragment {
    private String[] titleArr=new String[]{"推荐","热点","阳光宽频","北京","社会","娱乐","问答","图片"};
    private List<Fragment> fragmentList=new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager viewPage;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.message_fragment, null);
        //得到控件
        tabLayout = (TabLayout) inflate.findViewById(R.id.tablayout);
        viewPage = (ViewPager) inflate.findViewById(R.id.viewpager);
        //将fragment存入集合中
        inflateData();
        //将fragment添加给viewpager
        Message_titleAdater message_titleAdater = new Message_titleAdater(getChildFragmentManager(),this,fragmentList,titleArr);
        //设置适配器
        viewPage.setAdapter(message_titleAdater);
        //将viewpaget添加建tabLayout中
        tabLayout.setupWithViewPager(viewPage);
        //设置tabLayout的样式
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        return inflate;
    }

    public void inflateData() {
        for (int i=0;i<titleArr.length;i++)
        {
            Message_Fragment_Title message_fragment_title = new Message_Fragment_Title();
            fragmentList.add(message_fragment_title);
        }
    }
}

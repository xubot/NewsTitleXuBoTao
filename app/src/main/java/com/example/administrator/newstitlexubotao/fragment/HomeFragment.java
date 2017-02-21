package com.example.administrator.newstitlexubotao.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.newstitlexubotao.Adater.HomeTitleAdater;
import com.example.administrator.newstitlexubotao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/10.
 */

public class HomeFragment extends Fragment{
    private String[] titleArr=new String[]{"房产","足球","娱乐","体育","财经","科技","电影","汽车","笑话","游戏","时尚","情感","精选","电台","ABN","数码","移动","彩票","教育","论坛","旅游","手机","博客","社会","家居","暴雪游戏","亲子","CBA","消息","军事"};
    private String[] UrlID=new String[]{"T1348647909107","T1399700447917","T1348648517839","T1348649079062","T1348648756099","T1348649580692","T1348648650048","T1348654060988","T1350383429665","T1348654151579","T1348650593803","T1348650839000","T1370583240249","T1379038288239","T1348649145984","T1348649776727","T1351233117091","T1356600029035","T1348654225495","T1349837670307","T1348654204705","T1348649654285","T1349837698345","T1348648037603","T1348654105308","T1397016069906","T1397116135282","T1348649475931","T1371543208049","T1348648141035"};
    private List<String> titleList=new ArrayList<>();
    private List<Fragment> fragmentList=new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager viewPage;
    private HomeTitleAdater homeTitleAdater;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.home_fragment, null);
        //得到控件
        tabLayout = (TabLayout) inflate.findViewById(R.id.tablayout);
        viewPage = (ViewPager) inflate.findViewById(R.id.viewpager);
        //将fragment存入集合中
        inflateData();
        //将fragment添加给viewpager
        homeTitleAdater = new HomeTitleAdater(getFragmentManager(),this,fragmentList,titleArr);
        //设置适配器
        viewPage.setAdapter(homeTitleAdater);
        //将viewpager给tabLayout
        tabLayout.setupWithViewPager(viewPage);
        viewPage.setOffscreenPageLimit(3);
        //设置标题的显示模式
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        return inflate;
    }

    public void inflateData() {
        for (int i=0;i<titleArr.length;i++)
        {
            HomeFragmentMessage home_fragment_message = new HomeFragmentMessage();
            Bundle bundle = new Bundle();
            bundle.putString("id",UrlID[i]);
            home_fragment_message.setArguments(bundle);
            fragmentList.add(home_fragment_message);
        }
    }
}

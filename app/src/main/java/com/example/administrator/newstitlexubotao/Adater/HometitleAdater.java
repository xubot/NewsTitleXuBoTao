package com.example.administrator.newstitlexubotao.Adater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.newstitlexubotao.fragment.HomeFragment;

import java.util.List;

/**
 * Created by Administrator on 2017/2/12.
 */
public class HomeTitleAdater extends FragmentPagerAdapter{

    //String[] titleArr
    private final FragmentManager fragmentManager;
    private final HomeFragment h_fragment;
    private final List<Fragment> fragmentList;
    //private final List<String> titleList;
    private String[] titleArr;

    public HomeTitleAdater(FragmentManager fragmentManager, HomeFragment h_fragment, List<Fragment> fragmentList, String[] titleArr) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        this.h_fragment = h_fragment;
        this.fragmentList = fragmentList;
        //this.titleList = titleList;
        this.titleArr=titleArr;
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }
    @Override
    public int getCount() {
        return fragmentList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titleArr[position];
    }
}

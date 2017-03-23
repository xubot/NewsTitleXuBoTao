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
    private final List<Fragment> fragmentList;
    private String[] titleArr;

    public HomeTitleAdater(FragmentManager fragmentManager, HomeFragment homeFragment, List<Fragment> fragmentList, String[] titleArr) {
        super(fragmentManager);
        this.fragmentList = fragmentList;
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

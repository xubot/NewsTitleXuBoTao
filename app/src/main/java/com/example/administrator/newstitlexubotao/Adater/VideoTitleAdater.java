package com.example.administrator.newstitlexubotao.Adater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/18.
 */
public class VideoTitleAdater extends FragmentPagerAdapter{
    private final FragmentManager childFragmentManager;
    private final FragmentActivity activity;
    private List<Fragment> fragmentList=new ArrayList<>();
    private String[] arr;

    public VideoTitleAdater(FragmentManager childFragmentManager, FragmentActivity activity) {
        super(childFragmentManager);
        this.childFragmentManager = childFragmentManager;
        this.activity = activity;
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
        return arr[position];
    }

    public void setData(List<Fragment> data, String[] arr) {
        this.arr = arr;
        if(data!=null)
        {
            fragmentList.addAll(data);
        }
    }
}

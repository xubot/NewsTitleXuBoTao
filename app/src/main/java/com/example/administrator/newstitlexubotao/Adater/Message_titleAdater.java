package com.example.administrator.newstitlexubotao.Adater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.newstitlexubotao.fragment.Message_Fragment;

import java.util.List;

/**
 * Created by Administrator on 2017/2/12.
 */
public class Message_titleAdater extends FragmentPagerAdapter{

    private final FragmentManager childFragmentManager;
    private final Message_Fragment message_fragment;
    private final String[] titleArr;
    private final List<Fragment> fragmentList;
    public Message_titleAdater(FragmentManager childFragmentManager, Message_Fragment message_fragment, List<Fragment> fragmentList, String[] titleArr) {
        super(childFragmentManager);
        this.childFragmentManager = childFragmentManager;
        this.message_fragment = message_fragment;
        this.titleArr = titleArr;
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }
    @Override
    public int getCount() {
        return fragmentList.size();
    }
    public CharSequence getCharSequence(int position)
    {
        return titleArr[position];
    }
}

package com.example.administrator.newstitlexubotao.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.newstitlexubotao.Adater.VideoTitleAdater;
import com.example.administrator.newstitlexubotao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/10.
 */

public class VideoFragment extends Fragment {
    private String[] arr=new String[]{"热点视频","娱乐视频","搞笑视频","精品视频"};
    private String[] arrUrl=new String[]{"V9LG4B3A0","V9LG4CHOR","V9LG4E6VR","00850FRB"};
    private List<Fragment> fragmentList=new ArrayList<>();
    private VideoTitleAdater videoTitleAdater;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        TabLayout tabLayout= (TabLayout) view.findViewById(R.id.tab);
        ViewPager viewPager= (ViewPager) view.findViewById(R.id.vpager);
        initData();
        videoTitleAdater = new VideoTitleAdater(getChildFragmentManager(),getActivity());
        viewPager.setAdapter(videoTitleAdater);
        videoTitleAdater.setData(fragmentList,arr);
        videoTitleAdater.notifyDataSetChanged();
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initData() {
        for (int i=0;i<arr.length;i++)
        {
            VideoFragmentMessage videoFragmentMessage = new VideoFragmentMessage();
            Bundle bundle = new Bundle();
            bundle.putString("keyurl",arrUrl[i]);
            videoFragmentMessage.setArguments(bundle);
            fragmentList.add(videoFragmentMessage);
        }
    }
}

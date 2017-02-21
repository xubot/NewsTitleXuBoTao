package com.example.administrator.newstitlexubotao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.newstitlexubotao.Adater.VideoJcAdater;
import com.example.administrator.newstitlexubotao.Bean.VideoBean;
import com.example.administrator.newstitlexubotao.R;
import com.example.administrator.newstitlexubotao.Uilt.DataInterface;
import com.example.administrator.newstitlexubotao.Uilt.RequestXUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by Administrator on 2017/2/18.
 */

public class VideoFragmentMessage extends Fragment implements DataInterface<VideoBean>,PullToRefreshListView.OnRefreshListener2{

    private PullToRefreshListView pullToRefreshListView;
    private VideoJcAdater videoJcAdater;
    private int num=1;
    private int number=10;
    private Boolean flag=false;
    private String path;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment_title, null);
        initView(view);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        String pathid = arguments.getString("keyurl");
        path = "http://c.3g.163.com/nc/video/list/"+pathid+"/n/"+num+"-"+number+".html";
        RequestXUtils.utils(path, VideoBean.class,this);
    }

    private void initView(View view) {
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.listview1);
        videoJcAdater = new VideoJcAdater(getActivity());
        pullToRefreshListView.setAdapter(videoJcAdater);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshListView.setOnRefreshListener(this);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        num=1;
        number=10;
        flag=true;
        RequestXUtils.utils(path, VideoBean.class,this);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        num+=10;
        number+=10;
        flag=false;
        RequestXUtils.utils(path, VideoBean.class,this);
    }

    @Override
    public void setdata(List<VideoBean> t) {
        Log.d("zzz2", t.toString());
        videoJcAdater.setData(t,flag);
        videoJcAdater.notifyDataSetChanged();
        pullToRefreshListView.onRefreshComplete();
    }
}

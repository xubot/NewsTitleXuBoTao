package com.example.administrator.newstitlexubotao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.example.administrator.newstitlexubotao.Adater.HomeMessageAdater;
import com.example.administrator.newstitlexubotao.Bean.HomeMagessBean;
import com.example.administrator.newstitlexubotao.R;
import com.example.administrator.newstitlexubotao.Uilt.DataInterface;
import com.example.administrator.newstitlexubotao.Uilt.RequestXUtils;
import com.example.administrator.newstitlexubotao.Activity.WebViewActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Administrator on 2017/2/12.
 */

public class HomeFragmentMessage extends Fragment implements DataInterface<HomeMagessBean>,PullToRefreshListView.OnRefreshListener2 {

    private HomeMessageAdater homeMessageAdater;
    private PullToRefreshListView prl;
    private int index=0;
    private int num=20;
    private boolean flag=false;
    private View inflate;
    private String encode;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.home_fragment_message, null);
        initView(inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        encode = URLEncoder.encode(id);
        String url = setUrl(index,num);
        RequestXUtils.utils(url, HomeMagessBean.class,this);
    }

    @NonNull
    private String setUrl(int index,int num) {
        return "http://c.m.163.com/nc/article/headline/"+encode+"/"+index+"-"+num+".html";
    }

    private void initView(View view) {
        prl = (PullToRefreshListView) view.findViewById(R.id.message_Listview);
        homeMessageAdater = new HomeMessageAdater(getActivity());
        prl.setAdapter(homeMessageAdater);
        prl.setMode(PullToRefreshBase.Mode.BOTH);
        prl.setOnRefreshListener(this);
    }
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        index=0;
        num=20;
        flag=true;
        RequestXUtils.utils(setUrl(index,num), HomeMagessBean.class,this);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        index+=20;
        num+=20;
        flag=false;
        RequestXUtils.utils(setUrl(index,num), HomeMagessBean.class,this);
    }
    public void setdata(final List<HomeMagessBean> homeMagessBean) {
        Log.d("zzz", homeMagessBean.toString());
        homeMessageAdater.addData(homeMagessBean,flag);
        homeMessageAdater.notifyDataSetChanged();
        prl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("key", homeMagessBean.get(position).getUrl_3w());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.keep);
            }
        });
        prl.onRefreshComplete();
    }
}

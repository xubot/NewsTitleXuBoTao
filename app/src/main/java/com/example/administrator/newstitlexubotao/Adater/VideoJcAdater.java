package com.example.administrator.newstitlexubotao.Adater;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.administrator.newstitlexubotao.Bean.VideoBean;
import com.example.administrator.newstitlexubotao.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by Administrator on 2017/2/20.
 */
public class VideoJcAdater extends BaseAdapter{
    private FragmentActivity activity;
    private List<VideoBean> videoList=new ArrayList<>();

    public VideoJcAdater(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return videoList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null)
        {
            convertView=View.inflate(activity, R.layout.video_jc_listview,null);
            viewHolder=new ViewHolder();
            viewHolder.jc= (JCVideoPlayer) convertView.findViewById(R.id.jc);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.jc.setUp(videoList.get(position).getMp4_url(),videoList.get(position).getTitle());
        viewHolder.jc.ivThumb.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageLoader.getInstance().displayImage(videoList.get(position).getTopicImg(), viewHolder.jc.ivThumb);
        return convertView;
    }
    public void setData(List<VideoBean> data, Boolean flag) {
        if(data!=null)
        {
            if(flag)
            {
                videoList.clear();
            }
            videoList.addAll(data);
        }
    }
    class ViewHolder
    {
        JCVideoPlayer jc;
    }
}

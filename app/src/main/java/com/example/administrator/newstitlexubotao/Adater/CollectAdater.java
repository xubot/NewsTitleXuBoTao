package com.example.administrator.newstitlexubotao.Adater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.administrator.newstitlexubotao.Activity.CollectActivity;
import com.example.administrator.newstitlexubotao.Bean.SQLData;
import com.example.administrator.newstitlexubotao.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 */

public class CollectAdater extends BaseAdapter{
    private final CollectActivity collectActivity;
    private final List<SQLData> datas;

    public CollectAdater(CollectActivity collectActivity, List<SQLData> datas) {
        this.collectActivity = collectActivity;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
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
        convertView=View.inflate(collectActivity, R.layout.home_message_listview_twe,null);
        TextView title= (TextView) convertView.findViewById(R.id.title);
        TextView source= (TextView) convertView.findViewById(R.id.source);
        TextView time= (TextView) convertView.findViewById(R.id.time);
        ImageView imageView= (ImageView) convertView.findViewById(R.id.imageView);

        title.setText(datas.get(position).getTitle());
        source.setText(datas.get(position).getSource());
        time.setText(datas.get(position).getTime());
        ImageLoader.getInstance().displayImage(datas.get(position).getImg(),imageView);
        return convertView;
    }
}

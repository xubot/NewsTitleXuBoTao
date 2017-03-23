package com.example.administrator.newstitlexubotao.Adater;


import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.newstitlexubotao.Bean.HomeMagessBean;
import com.example.administrator.newstitlexubotao.Bean.SQLData;
import com.example.administrator.newstitlexubotao.R;
import com.example.administrator.newstitlexubotao.Uilt.ApplicationData;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.DbManager;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */
public class HomeMessageAdater extends BaseAdapter {
    private static int TYPE1=1;
    private static int TYPE2=2;
    private static int TYPE3=3;
    private FragmentActivity activity;
    private List<HomeMagessBean> list=new ArrayList<>();
    private List<SQLData> sqlDataList=new ArrayList<>();
    private DbUtils dbUtils;


    public HomeMessageAdater(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        HomeMagessBean item = getItem(position);
        if (item.getImgextra()!=null && item.getImgextra().size() > 0) {
            return TYPE1;
        } else {
            return TYPE2;
        }
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public HomeMagessBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view = View.inflate(activity, R.layout.popupwindow, null);

        setSqlData(position);
        //创建数据库
        dbUtils = DbUtils.create(activity, "data.db", 1, new DbUtils.DbUpgradeListener() {
            @Override
            public void onUpgrade(DbUtils dbUtils, int i, int i1) {

            }
        });
        //创建表格
        try {
            dbUtils.createTableIfNotExist(SQLData.class);
        } catch (DbException e) {
            e.printStackTrace();
        }


        //收藏的控件
        TextView collect= (TextView) view.findViewById(R.id.collect);
        //收藏的监听
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(activity, "点击了", Toast.LENGTH_SHORT).show();
                //添加数据库
                try {
                    dbUtils.saveAll(sqlDataList);
                    Toast.makeText(activity, "成功", Toast.LENGTH_SHORT).show();
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        });
        
        //得到popupwindow的对象
        final PopupWindow popupWindow = new PopupWindow(view, 50, 20);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));

        MyView2 myView2=null;
        MyView3 myView3=null;
        if(convertView==null)
        {
            if(getItemViewType(position)==TYPE1)
            {
                convertView=View.inflate(activity, R.layout.home_message_listview_three,null);
                myView3=new MyView3();
                myView3.title= (TextView) convertView.findViewById(R.id.title);
                myView3.source= (TextView) convertView.findViewById(R.id.source);
                myView3.time= (TextView) convertView.findViewById(R.id.time);
                myView3.img1= (ImageView) convertView.findViewById(R.id.imageView1);
                myView3.img2= (ImageView) convertView.findViewById(R.id.imageView2);
                myView3.img3= (ImageView) convertView.findViewById(R.id.imageView3);
                myView3.close2= (ImageView) convertView.findViewById(R.id.close2);

                //点击弹出对话框
                final View finalConvertView = convertView;
                myView3.close2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.showAsDropDown(v);
                    }
                });
                convertView.setTag(myView3);
            }
            else
            {
                convertView=View.inflate(activity, R.layout.home_message_listview_twe,null);
                myView2=new MyView2();
                myView2.title= (TextView) convertView.findViewById(R.id.title);
                myView2.source= (TextView) convertView.findViewById(R.id.source);
                myView2.time= (TextView) convertView.findViewById(R.id.time);
                myView2.img= (ImageView) convertView.findViewById(R.id.imageView);
                myView2.close1= (ImageView) convertView.findViewById(R.id.close1);
                //点击弹出对话框
                myView2.close1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.showAsDropDown(v);
                    }
                });
                convertView.setTag(myView2);
            }
        }
        else
        {
            if(getItemViewType(position)==TYPE1)
            {
                myView3= (MyView3) convertView.getTag();
            }
            else
            {
                myView2= (MyView2) convertView.getTag();
            }
        }
        if(getItemViewType(position)==TYPE1)
        {
            myView3.source.setText(list.get(position).getSource());
            myView3.title.setText(list.get(position).getTitle());
            myView3.time.setText(list.get(position).getPtime());
            DisplayImageOptions options = getImageOptions();
            ImageLoader.getInstance().displayImage(list.get(position).getImgsrc(),myView3.img1,options);
            ImageLoader.getInstance().displayImage(list.get(position).getImgextra().get(0).getImgsrc(),myView3.img2,options);
            ImageLoader.getInstance().displayImage(list.get(position).getImgextra().get(1).getImgsrc(),myView3.img3,options);
        }
        else
        {
            myView2.source.setText(list.get(position).getSource());
            myView2.title.setText(list.get(position).getTitle());
            myView2.time.setText(list.get(position).getPtime());
            DisplayImageOptions options = getImageOptions();
            ImageLoader.getInstance().displayImage(list.get(position).getImgsrc(),myView2.img,options);
        }
        return convertView;
    }

    private void setSqlData(int position) {
        String title = list.get(position).getTitle();
        String source = list.get(position).getSource();
        String imgsrc = list.get(position).getImgsrc();
        String ptime = list.get(position).getPtime();
        SQLData sqlData = new SQLData(title, imgsrc, source, ptime);
        sqlDataList.add(sqlData);
    }
    private DisplayImageOptions getImageOptions() {
        return new DisplayImageOptions.Builder()
                        .showStubImage(R.drawable.not_article_loading)         //加载开始默认的图片
                        .showImageOnFail(R.drawable.not_answer_loading)        //加载图片出现问题，会显示该图片
                        .cacheInMemory()                                       //缓存用
                        .cacheOnDisc()                                         //缓存用
                        .build();
    }
    public void addData(List<HomeMagessBean> homeMagessBean, boolean flag) {
        if(homeMagessBean !=null)
        {
            if(flag)
            {
                list.clear();
            }
            list.addAll(homeMagessBean);
            Log.d("zzz1", list.toString());
        }
    }
    class MyView2 {
        TextView title;
        ImageView img;
        TextView source;
        TextView time;
        ImageView close1;
    }
    class MyView3 {
        TextView title;
        ImageView img1;
        ImageView img2;
        ImageView img3;
        TextView source;
        TextView time;
        ImageView close2;
    }
}

package com.example.administrator.newstitlexubotao.Activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.newstitlexubotao.R;
import com.example.administrator.newstitlexubotao.Uilt.Connectivity;
import com.example.administrator.newstitlexubotao.fragment.NetworkInfo;
import com.example.administrator.newstitlexubotao.fragment.VideoFragment;
import com.example.administrator.newstitlexubotao.fragment.HomeFragment;
import com.example.administrator.newstitlexubotao.fragment.MineFragment;
import com.example.administrator.newstitlexubotao.fragment.ConcernFragment;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class MainActivity extends FragmentActivity  implements View.OnClickListener{
    private List<LinearLayout> linearLayoutList=new ArrayList<>();
    private List<ImageView> imageViewList=new ArrayList<>();
    private List<TextView> textViewList=new ArrayList<>();
    private LinearLayout lmessage, llinkman, lspace, lmine;
    private TextView message,linkman,space,mine;
    private ImageView imagemessage, imagelinkman, imagespace, imagemine;
    private int[] clickBefore=new int[]{R.drawable.b_newhome_tabbar,R.drawable.b_newvideo_tabbar,R.drawable.b_newcare_tabbar,R.drawable.b_newnologin_tabbar};
    private int[] clickAfter=new int[]{R.drawable.b_newhome_tabbar_press,R.drawable.b_newvideo_tabbar_press,R.drawable.b_newcare_tabbar_press,R.drawable.b_newnologin_tabbar_press};
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private HomeFragment homeFragment;
    private VideoFragment videoFragment;
    private ConcernFragment concernFragment;
    private MineFragment mineFragment;
    private  Fragment fragment;
    private Boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        inflateView();
        //得到网络类
        flag = Connectivity.connectivity(this);
        if(flag)
        {
            //初次页面
            if (homeFragment==null){
                homeFragment =new HomeFragment();
            }
            addFragment(homeFragment);
            setBackground(0);
        }
        else
        {
            addFragment(new NetworkInfo());
        }
    }

    //布局控件
    public void inflateView() {
        //得到控件(LinearLayout的控件)
        lmessage = (LinearLayout) findViewById(R.id.llmessage);
        llinkman = (LinearLayout) findViewById(R.id.lllinkman);
        lspace = (LinearLayout) findViewById(R.id.llspace);
        lmine = (LinearLayout) findViewById(R.id.llmine);
        //得到下面汉字的控件
        message = (TextView) findViewById(R.id.message);
        linkman = (TextView) findViewById(R.id.linkman);
        space = (TextView) findViewById(R.id.space);
        mine = (TextView) findViewById(R.id.mine);
        //得到下面图片的控件
        imagemessage = (ImageView) findViewById(R.id.imagemessage);
        imagelinkman = (ImageView) findViewById(R.id.imagelinkman);
        imagespace = (ImageView) findViewById(R.id.imagespace);
        imagemine = (ImageView) findViewById(R.id.imagemine);
        addControl();
    }

    public void addControl() {
        //向集合中赋值（LayoutList）
        linearLayoutList.add(lmessage);
        linearLayoutList.add(llinkman);
        linearLayoutList.add(lspace);
        linearLayoutList.add(lmine);
        //向集合中赋值（imageView）
        imageViewList.add(imagemessage);
        imageViewList.add(imagelinkman);
        imageViewList.add(imagespace);
        imageViewList.add(imagemine);
        //向集合中赋值（ textView）
        textViewList.add(message);
        textViewList.add(linkman);
        textViewList.add(space);
        textViewList.add(mine);
        //给当前布局设置点击监听
        for(LinearLayout lll:linearLayoutList)
        {
            lll.setOnClickListener(this);
        }
    }
    //添加fragment布局
    public void addFragment(Fragment f) {
        //得到fragment的管理类
        manager = getSupportFragmentManager();
        //开启一个事务
        transaction = manager.beginTransaction();
        //赋值
        if (fragment!=null){
            transaction.hide(fragment);
        }if(!f.isAdded()){
            //添加当前fragment
            transaction.add(R.id.framelayout,f);
        }
        //显示当前事务
        transaction.show(f);
        //提交事务
        transaction.commit();
        //给空槽赋值
        fragment=f;
    }

    //控件的点击监听
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.llmessage :
                if(flag)
                {
                    //初次页面
                    if (homeFragment==null){
                        homeFragment =new HomeFragment();
                    }
                    addFragment(homeFragment);
                }
                else
                {
                    addFragment(new NetworkInfo());
                }
                JCVideoPlayer.releaseAllVideos();
                setBackground(0);
                break;
            case R.id.lllinkman :
                if(flag)
                {
                    //初次页面
                    if (videoFragment==null){
                        videoFragment =new VideoFragment();
                    }
                    addFragment(videoFragment);
                }
                else
                {
                    addFragment(new NetworkInfo());
                }
                JCVideoPlayer.releaseAllVideos();
                setBackground(1);
                break;
            case R.id.llspace :
                if (concernFragment==null){
                    concernFragment =new ConcernFragment();
                }
                addFragment(concernFragment);
                JCVideoPlayer.releaseAllVideos();
                setBackground(2);
                break;
            case R.id.llmine :
                if (mineFragment==null){
                    mineFragment =new MineFragment();
                }
                addFragment(mineFragment);
                JCVideoPlayer.releaseAllVideos();
                setBackground(3);
                break;
        }
    }
    //设置颜色（点击时）
    public void setBackground(int index) {
        for(int i=0;i<linearLayoutList.size();i++)
        {
            if(i==index)
            {
                imageViewList.get(i).setImageResource(clickAfter[i]);
                textViewList.get(i).setTextColor(Color.RED);
            }
            else
            {
                imageViewList.get(i).setImageResource(clickBefore[i]);
                textViewList.get(i).setTextColor(Color.BLACK);
            }
        }
    }
}

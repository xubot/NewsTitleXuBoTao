package com.example.administrator.newstitlexubotao.Adater;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.newstitlexubotao.Bean.Data;
import com.example.administrator.newstitlexubotao.R;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;
import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */

public class MyAdataer extends BaseAdapter{
    private final FragmentActivity activity;
    private final List<Data> dataList;
    private TextView name;
    private TextView down;

    public MyAdataer(FragmentActivity activity, List<Data> dataList) {
        this.activity = activity;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView=View.inflate(activity, R.layout.listview,null);
        name = (TextView) convertView.findViewById(R.id.name);
        down = (TextView) convertView.findViewById(R.id.down);
        name.setText(dataList.get(position).getName().toString());
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url =dataList.get(position).getUrl();
                RequestParams params = new RequestParams(url);
                //保存到sdcard
                params.setSaveFilePath(Environment.getExternalStorageDirectory()+"/app/");
                //自动文件命令
                params.setAutoRename(true);
                //下载
                x.http().post(params, new Callback.ProgressCallback<File>() {

                    private int currentsum;
                    private int totalsum;
                    private ProgressDialog progressDialog = new ProgressDialog(activity);;

                    @Override
                    public void onWaiting() {
                        Log.d("zzz11","onWaiting");
                    }

                    @Override
                    public void onStarted() {
                        Log.d("zzz11","onStarted");
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isDownloading) {
                        //文件总大小和当前进度
                        totalsum = new Long(total).intValue();
                        currentsum = new Long(current).intValue();

                        progressDialog.setMessage("正在下载当前应用 ,请耐心等待");
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressDialog.show();

                        progressDialog.setMax(totalsum);
                        progressDialog.setProgress(currentsum);
                    }

                    @Override
                    public void onSuccess(File result) {
                        if(totalsum==currentsum)
                        {
                            progressDialog.setProgress(0);
                        }
                        progressDialog.dismiss();
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(result), "application/vnd.android.package-archive");
                        activity.startActivity(intent);
                        Toast.makeText(activity, "安装成功", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }
                    @Override
                    public void onCancelled(CancelledException cex) {

                    }
                    @Override
                    public void onFinished() {
                        Log.d("zzz11", "onFinished");
                    }
                });
            }
        });
        return convertView;
    }
}

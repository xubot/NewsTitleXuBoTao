package com.example.administrator.newstitlexubotao.Activity;

import android.hardware.camera2.DngCreator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.newstitlexubotao.Adater.CollectAdater;
import com.example.administrator.newstitlexubotao.Bean.SQLData;
import com.example.administrator.newstitlexubotao.R;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;

import java.util.List;

public class CollectActivity extends AppCompatActivity {

    private DbUtils dbUtils;
    private List<SQLData> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        dbUtils = DbUtils.create(this, "data.db");

        try {
            datas = dbUtils.findAll(SQLData.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        ListView collectListview= (ListView) findViewById(R.id.collectListview);
        final CollectAdater collectAdater = new CollectAdater(CollectActivity.this, datas);
        collectListview.setAdapter(collectAdater);
        collectListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    dbUtils.delete(SQLData.class, WhereBuilder.b("id", "=",position));
                    datas.remove(position);
                    collectAdater.notifyDataSetChanged();
                } catch (DbException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
    }
}

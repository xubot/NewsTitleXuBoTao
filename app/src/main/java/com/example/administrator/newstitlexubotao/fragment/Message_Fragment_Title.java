package com.example.administrator.newstitlexubotao.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.newstitlexubotao.R;

/**
 * Created by Administrator on 2017/2/12.
 */

public class Message_Fragment_Title extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.message_fragment_title, null);
        ListView listView= (ListView) inflate.findViewById(R.id.message_Listview);
        return inflate;
    }
    class Task extends AsyncTask<String ,String ,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}

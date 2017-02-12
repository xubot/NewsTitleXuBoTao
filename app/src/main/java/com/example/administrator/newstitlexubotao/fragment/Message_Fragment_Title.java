package com.example.administrator.newstitlexubotao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.newstitlexubotao.R;

/**
 * Created by Administrator on 2017/2/12.
 */

public class Message_Fragment_Title extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.message_fragment_title, null);
        return inflate;
    }
}

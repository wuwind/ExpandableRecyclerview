package com.wuwind.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wuwind.banner.R;


public class Fragment5 extends BaseFragment {

    View contentView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        contentView = inflater.inflate(R.layout.banner_view, container, false);
        TextView textView = contentView.findViewById(R.id.text);
        textView.setText("5555555555");
        return contentView;
    }
}

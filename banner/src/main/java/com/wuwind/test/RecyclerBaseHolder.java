package com.wuwind.test;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wuhf on 2017/5/18.
 */

public abstract class RecyclerBaseHolder<T> extends RecyclerView.ViewHolder {

    public RecyclerBaseHolder(View itemView) {
        super(itemView);
    }

    public void setData(T data){
        refreshView(data);
    }

    public abstract void refreshView(T data);


}

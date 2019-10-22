package com.wuwind.test;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.wuwind.banner.R;

import java.util.ArrayList;
import java.util.List;

public class MorePopView {
    static List<LauncherMoreBean> mDatas = new ArrayList<>();

    public static void showView(Context context, View view) {
        for (int i = 0; i < 15; i++) {
            LauncherMoreBean bean = new LauncherMoreBean(R.mipmap.ic_launcher);
            mDatas.add(bean);
        }
        int[] xy = new int[2];
        view.getLocationInWindow(xy);
        xy[0] += view.getWidth();

        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setBackgroundColor(Color.BLACK);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        recyclerView.setLayoutParams(layoutParams);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
        MAdapter mAdapter = new MAdapter();
        recyclerView.setAdapter(mAdapter);
        PopupWindow window = new PopupWindow(recyclerView, 385, 180);
        window.showAtLocation(view, Gravity.NO_GRAVITY, xy[0]+5,  xy[1] - 90);
    }

    static class MAdapter extends RecyclerBaseAdapter<LauncherMoreBean> {

        public MAdapter() {
            super(mDatas);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            FrameLayout frameLayout = new FrameLayout(parent.getContext());
            FrameLayout.LayoutParams frameLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 90);
//            frameLayoutParams.gravity = Gravity.CENTER;
            frameLayout.setLayoutParams(frameLayoutParams);
            View view = new ImageView(parent.getContext());
            int size = 38;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(size, size);
            layoutParams.gravity = Gravity.CENTER;
            view.setLayoutParams(layoutParams);
            frameLayout.setBackgroundColor(Color.GRAY);
            frameLayout.addView(view);
            return getViewHolder(frameLayout);
        }

        @Override
        public int itemLayout() {
            return 0;
        }

        @Override
        public RecyclerBaseHolder getViewHolder(View itemView) {
            return new MHolder((itemView));
        }
    }

    static class MHolder extends RecyclerBaseHolder<LauncherMoreBean> {
        ImageView imgView;

        public MHolder(View itemView) {
            super(itemView);
            imgView = (ImageView) ((ViewGroup) itemView).getChildAt(0);
        }

        @Override
        public void refreshView(LauncherMoreBean data) {
            if (data.appInfo != null) {
                imgView.setImageDrawable(data.appInfo.getAppIcon());
            } else {
                imgView.setImageResource(data.drawableId);
            }
        }
    }
}

package com.wuwind.banner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LoopViewPager extends RelativeLayout {
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    List<Fragment> mDatas;
    FragmentManager fm;
    PagerAdapter pagerAdapter;
    private AutoPlayViewPager autoPlayViewPager;
    private GradientDrawable circleDrawable;
    private GradientDrawable selectDrawable;
    private LinearLayout indicatorLayout;
    private int selectedWidth = 12;
    private int indicatorMarginBottom = 10;

    public LoopViewPager(Context context) {
        super(context);
        init();
    }

    private void init() {
        circleDrawable = ShapeUtil.drawCircle(Color.parseColor("#aaffffff"), 10, 0, 0);
        selectDrawable = ShapeUtil.drawRoundRect(Color.WHITE, 10);
        autoPlayViewPager = new AutoPlayViewPager(getContext());
        autoPlayViewPager.setId(generateViewId());
        autoPlayViewPager.setAnimation(null);
        addView(autoPlayViewPager);

        indicatorLayout = new LinearLayout(getContext());
        GradientDrawable gradientDrawable = ShapeUtil.drawRoundRect(Color.parseColor("#33000000"), 20);
        indicatorLayout.setBackgroundDrawable(gradientDrawable);
        indicatorLayout.setPadding(10, 5, 10, 5);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp.bottomMargin = indicatorMarginBottom;
        indicatorLayout.setLayoutParams(lp);

        addView(indicatorLayout);
        autoPlayViewPager.setListener(new AutoPlayViewPager.PageListener() {
            @Override
            public void change() {
                int currentItem = autoPlayViewPager.getCurrentItem();
                for (int i = 0; i < indicatorLayout.getChildCount(); i++) {
                    View childAt = indicatorLayout.getChildAt(i);
                    setSelect(childAt, currentItem % indicatorLayout.getChildCount() == i);
                }
            }
        });
    }

    public static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    private void setSelect(View view, boolean selected) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = selected ? selectedWidth : 6;
        view.setBackgroundDrawable(selected ? selectDrawable : circleDrawable);
        view.setLayoutParams(layoutParams);
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoopViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setData(FragmentManager fm, final List<Fragment> datas) {
        mDatas = datas;
        this.fm = fm;
        PagerAdapter adapter;
//        if (datas.size() > 2) {
            adapter = new DynamicFragmentAdapter(fm, datas);
//        } else {
//            adapter = new FragmentPagerAdapter(fm) {
//                @Override
//                public int getCount() {
//                    return datas.size();
//                }
//
//                @Override
//                public Fragment getItem(int position) {
//                    return datas.get(position);
//                }
//
//            };
//        }
        setAdapter(adapter, datas.size());
    }

    public void setAdapter(PagerAdapter loopFragmentPagerAdapter, int size) {
        this.pagerAdapter = loopFragmentPagerAdapter;
        autoPlayViewPager.setAdapter(loopFragmentPagerAdapter);
        initIndicator(size);
    }

    private void initIndicator(int size) {
        indicatorLayout.removeAllViews();
        for (int i = 0; i < size; i++) {
            View view = new View(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(6, 6);
            if (i != 0)
                lp.leftMargin = 10;
            lp.weight = 1;
            view.setLayoutParams(lp);
            view.setBackgroundDrawable(circleDrawable);
            indicatorLayout.addView(view);
        }
        View childAt = indicatorLayout.getChildAt(autoPlayViewPager.getCurrentItem());
        setSelect(childAt, true);
    }

    public void start() {
        autoPlayViewPager.start();
    }

    public void remove(Fragment fragment) {
//        int index = mDatas.indexOf(fragment);
//        indicatorLayout.removeViewAt(index == mDatas.size()-1 ? 0 : index + 1);
        mDatas.remove(fragment);
        autoPlayViewPager.getAdapter().notifyDataSetChanged();
        initIndicator(mDatas.size());
    }

    public void add(Fragment fragment) {
        mDatas.add(fragment);
        autoPlayViewPager.getAdapter().notifyDataSetChanged();
        initIndicator(mDatas.size());
    }
}


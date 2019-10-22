package com.wuwind.banner;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack
 */
public class LoopFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> listFragment = new ArrayList<>();
    private int size;
    FragmentManager fm;
    public LoopFragmentPagerAdapter(FragmentManager fm, List<Fragment> listData) {
        super(fm);
        this.fm = fm;
        setDatas(listData);
    }

    public void setDatas(List<Fragment> listData) {
        size = listData.size();
//        for (Fragment listDatum : listData) {
//            listFragment.add(listDatum);
//        }
        listFragment = listData;
//        if (size < 3) {
//            int dx = 3 - size;
//            for (int i = 0; i < dx; i++) {
//                Class<?> aClass = Class.forName(listData.get(i % listData.size()).getClass().getCanonicalName());
//                Object o = aClass.newInstance();
//                listFragment.add((Fragment) o);
//            }
//        }
    }

    public int getSize() {
        return size;
    }

    @Override
    public Fragment getItem(int i) {
        return this.listFragment.get(i);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % listFragment.size();
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public int getCount() {
        return listFragment.size() == 1 ? 1 : 1000;
    }

}

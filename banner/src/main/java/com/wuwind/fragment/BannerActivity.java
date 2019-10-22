package com.wuwind.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wuwind.banner.LoopViewPager;
import com.wuwind.banner.R;
import com.wuwind.view.RotateImageView;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        final LoopViewPager pager = findViewById(R.id.viewPager);
        List<Fragment> list = new ArrayList<>();
        list.add(new FragmentOne());
        list.add(new FragmentTwo());
        final FragmentThree fragmentThree = new FragmentThree();
        fragmentThree.setHideListener(new FragmentThree.HideListener() {
            @Override
            public void hide() {
                Log.e("ba","hide");
                pager.remove(fragmentThree);
            }
        });
        list.add(fragmentThree);
//
//        list.add(new Fragment5());
//        list.add(new Fragment6());
//        list.add(new Fragment7());
//        list.add(new Fragment8());
//        list.add(new Fragment9());
//        pager.setAdapter(new LoopFragmentPagerAdapter(getSupportFragmentManager(), list), list.size());
        pager.setData(getSupportFragmentManager(), list);

////        viewPager.setDirection(AutoPlayViewPager.Direction.LEFT);// 设置播放方向
////        viewPager.setCurrentItem(500); // 设置每个Item展示的时间
        pager.start(); // 开始轮播

        final RotateImageView btnAi = findViewById(R.id.btn_ai);


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAi.toggle();
                pager.add(new Fragment5());
            }
        });

    }
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */

            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);


            //用fresco加载图片简单用法，记得要写下面的createImageView方法
//            Uri uri = Uri.parse((String) path);
//            imageView.setImageURI(uri);
        }

    }
    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> list;

        public ViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            position = getPostion(position);
//
//            return super.instantiateItem(container, position);
//        }

        @Override
        public Fragment getItem(int position) {
//            position = getPostion(position);
            return list.get(position);
        }

        private int getPostion(int position) {
            return position % list.size();
        }

//        public void update(List<Fragment> list) {
//            if (this.list != null)
//                this.list.clear();
//            if (list != null)
//                this.list = list;
//        }

//        public long getItemId(int position) {
//            position = getPostion(position);
//            return (long)position;
//        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}

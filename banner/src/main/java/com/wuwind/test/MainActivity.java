package com.wuwind.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wuwind.banner.R;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    public static Method getMethod(Class clazz, String methodName,
                                   Class... classes) throws Exception {
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(methodName, classes);
        } catch (NoSuchMethodException e) {
            try {
                method = clazz.getMethod(methodName, classes);
            } catch (NoSuchMethodException ex) {
                if (clazz.getSuperclass() == null) {
                    return method;
                } else {
                    method = getMethod(clazz.getSuperclass(), methodName,
                            classes);
                }
            }
        }
        return method;
    }

    /**
     * @param obj        调整方法的对象
     * @param methodName 方法名
     * @param classes    参数类型数组
     * @param objects    参数数组
     * @return 方法的返回值
     */
    public static Object invoke(final Object obj, final String methodName,
                                final Class[] classes, final Object[] objects) {
        try {
            Method method = getMethod(obj.getClass(), methodName, classes);
            method.setAccessible(true);// 调用private方法的关键一句话
            return method.invoke(obj, objects);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object invoke(final Object obj, final String methodName,
                                final Class[] classes) {
        return invoke(obj, methodName, classes, new Object[]{});
    }

    public static Object invoke(final Object obj, final String methodName) {
        return invoke(obj, methodName, new Class[]{}, new Object[]{});
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        GridView gridview = findViewById(R.id.gridview);
//        try {
//            Method trackMotionScroll = getMethod(AbsListView.class, "trackMotionScroll", int.class, int.class);
//            Log.e("tag", trackMotionScroll.getName());
//            trackMotionScroll.setAccessible(true);
//            trackMotionScroll.invoke(gridview,-4,-5);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View viewById = findViewById(R.id.tv);
        MorePopView.showView(this, viewById);
    }

    class A {
        private void printlnA(String s,int a) {
            System.out.println(s+a);
        }
    }

    class B extends A {
        private void printlnB() {
            System.out.println("b");
        }
    }
}

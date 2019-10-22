package com.wuwind.test;

/**
 * Created by Wuhf on 2019/9/24.
 * Description ：主页更多bean
 */
public class LauncherMoreBean {
    public int type;
    public AppInfo appInfo;
    public int drawableId;

    public LauncherMoreBean(int drawableId) {
        this.drawableId = drawableId;
    }

    public LauncherMoreBean(AppInfo appInfo) {
        this.appInfo = appInfo;
    }
}

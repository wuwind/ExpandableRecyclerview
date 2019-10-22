package com.wuwind.test;

import android.content.Intent;
import android.graphics.drawable.Drawable;

/**
 * Created by Yezx on 2019-04-30 0030.
 */
public class AppInfo {
    private String packageName ;    //应用程序所对应的包名
    private String activityName ;    //应用程序所对应的类名
    private String appLabel;    //应用程序标签
    private Drawable appIcon ;  //应用程序图像
    private Intent intent ;     //启动应用程序的Intent ，一般是Action为Main和Category为Lancher的Activity

    public AppInfo(){}

    public String getPackageName(){
        return packageName ;
    }
    public void setPackageName(String packageName){
        this.packageName = packageName ;
    }
    public String getActivityName(){
        return activityName ;
    }
    public void setActivityName(String activityName){
        this.activityName = activityName ;
    }
    public String getAppLabel() {
        return appLabel;
    }
    public void setAppLabel(String appName) {
        this.appLabel = appName;
    }
    public Drawable getAppIcon() {
        return appIcon;
    }
    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }
    public Intent getIntent() {
        return intent;
    }
    public void setIntent(Intent intent) {
        this.intent = intent;
    }
}

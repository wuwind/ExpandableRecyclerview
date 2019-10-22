package com.wuwind.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class RotateImageView extends ImageView {

    private ObjectAnimator objectAnimator;
    private long mCurrentPlayTime;
    private int mDuration = 5000;

    public RotateImageView(Context context) {
        super(context);
        init();
    }

    private void init() {
        objectAnimator = ObjectAnimator.ofFloat(this, "rotation", 360f);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setDuration(mDuration);
    }

    public RotateImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RotateImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setDuration(int duration) {
        this.mDuration = duration;
        if (null != objectAnimator) {
            objectAnimator.setDuration(duration);
        }
    }

    public void toggle() {
        if (objectAnimator.isStarted())
            stop();
        else
            start();
    }

    public void stop() {
        if (objectAnimator.isStarted()) {
            mCurrentPlayTime = objectAnimator.getCurrentPlayTime();
            objectAnimator.cancel();
        }
    }

    public void start() {
        if (!objectAnimator.isStarted()) {
            objectAnimator.setCurrentPlayTime(mCurrentPlayTime);
            objectAnimator.start();
        }
    }

}

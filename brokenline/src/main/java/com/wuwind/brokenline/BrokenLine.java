package com.wuwind.brokenline;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class BrokenLine extends View {

    private int h;
    private int w;
    private Paint circlePaint;
    private Paint linePaint;
    private int radius;
    private float[] value = new float[]{28, 32, 30, 36, 31, 25, 29};
    private String[] bottomStr = new String[]{"今天", "明天", "星期一", "星期一", "星期一", "星期一", "星期一"};
    private int num;
    private float max = 0;
    private float min = 0;
    private int textH = 25;
    private String topStr;
    private int padingLeft = 40;
    private String suffix = "℃";


    public BrokenLine(Context context) {
        super(context);
        init();
    }

    private void init() {
        radius = 4;
        num = value.length;
        circlePaint = new Paint();
        linePaint = new Paint();
        circlePaint.setColor(Color.YELLOW);
        linePaint.setColor(Color.WHITE);
        linePaint.setAntiAlias(true);
        linePaint.setTextSize(15);
        linePaint.setStrokeWidth(2);
        getMinMax();
    }

    private void getMinMax() {
        if (value == null || value.length < 1)
            return;
        max = min = value[0];
        for (float i : value) {
            if (max < i)
                max = i;
            if (min > i)
                min = i;
        }
    }

    public BrokenLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BrokenLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.h = h;
        this.w = w - padingLeft * 2;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int rx = 0;
        int ry = 0;
        rx += padingLeft;
        int[] point = new int[2];
        for (int i = 0; i < num; i++) {
            ry = (int) ((max - value[i]) * (h - (radius + textH) * 2) / (max - min) + radius + textH);
            canvas.drawCircle(rx, ry, radius, circlePaint);
            if (i > 0)
                canvas.drawLine(point[0], point[1], rx, ry, linePaint);
            topStr = value[i] + suffix;
            canvas.drawText(bottomStr[i], rx - linePaint.measureText(bottomStr[i]) / 2, h - 5, linePaint);
            canvas.drawText(topStr, rx - linePaint.measureText(topStr) / 2, 15, linePaint);
            point[0] = rx;
            point[1] = ry;
            rx += w / (num - 1);
        }
    }

    private void loge(String s) {
        Log.e(BrokenLine.class.getSimpleName(), s);
    }

    public void setTextSize(int size) {
        this.linePaint.setTextSize(size);
    }

    public void setValue(float[] value) {
        this.value = value;
        getMinMax();
    }

    public void setBottomValue(String[] value) {
        this.bottomStr = value;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}

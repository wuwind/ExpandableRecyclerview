package com.wuwind.brokenline;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
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
    private int textH = 30;
    private String topStr;
    private int paddingLeft = 40;
    private int paddingTop = 10;
    private String suffix = "℃";
    private String bgColor = "#aa000000";
    private int textSize = 15;

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
        linePaint.setTextSize(textSize);
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
        this.w = w;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawRoundRect(canvas);
        int rx = 0;
        int ry = 0;
        int useWidth = w - paddingLeft * 2;
        rx += paddingLeft;
        int[] point = new int[2];
        for (int i = 0; i < num; i++) {
            ry = (int) ((max - value[i]) * (h - (radius + textH) * 2) / (max - min) + radius + textH);
            canvas.drawCircle(rx, ry, radius, circlePaint);
            if (i > 0)
                canvas.drawLine(point[0], point[1], rx, ry, linePaint);
            topStr = value[i] + suffix;
            canvas.drawText(bottomStr[i], rx - linePaint.measureText(bottomStr[i]) / 2, h - paddingTop, linePaint);
            canvas.drawText(topStr, rx - linePaint.measureText(topStr) / 2, textSize + paddingTop, linePaint)
            ;
            point[0] = rx;
            point[1] = ry;
            rx += useWidth / (num - 1);
        }
    }

    private void drawRoundRect(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor(bgColor));
        canvas.drawColor(Color.TRANSPARENT);
        paint.setStrokeWidth((float) 3.0);
        paint.setStyle(Paint.Style.FILL);
        RectF r2 = new RectF();
        r2.left = 0;
        r2.top = 0;
        r2.right = w;
        r2.bottom = h;
        canvas.drawRoundRect(r2, 10, 10, paint);
    }

    public void setTextSize(int size) {
        this.textSize = size;
        this.linePaint.setTextSize(size);
    }

    public void setValue(float[] value) {
        loge("setValue");
        this.value = value;
        getMinMax();
    }

    private void loge(String s) {
        Log.e(BrokenLine.class.getSimpleName(), s);
    }

    public void setBottomValue(String[] value) {
        this.bottomStr = value;
        loge("setBottomValue");
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setLineSize(int width) {
        this.linePaint.setStrokeWidth(width);
    }

    public void setCircleSize(int size) {
        this.radius = size;
    }

    public void setTextH(int textH) {
        this.textH = textH;
    }

    public void setBgColor(String color) {
        this.bgColor = color;
    }

    public void setPaddingLeft(int padding) {
        this.paddingLeft = padding;
    }

    public void setPaddingTop(int padding) {
        this.paddingTop = padding;
    }
}

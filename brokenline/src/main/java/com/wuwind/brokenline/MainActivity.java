package com.wuwind.brokenline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        BrokenLine brokenLine = findViewById(R.id.broken);
         float[] value = new float[]{28, 44, 56, 77, 31, 25, 29};
         String[] bottomStr = new String[]{"今天", "星期一", "星期一", "星期一", "星期一", "星期一", "星期一"};
        brokenLine.setValue(value);
        brokenLine.setBottomValue(bottomStr);
    }


}

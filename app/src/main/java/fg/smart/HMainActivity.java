package fg.smart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fg.expandablerecyclerview.model.ExpandableBean;
import fg.mylibrary.R;
import fg.smart.model.ItemBean;
import fg.smart.model.TitleBean;
import fg.smart.view.SmartQuickSettingView;

public class HMainActivity extends AppCompatActivity {

    TitleBean mode2;
    SmartQuickSettingView smartQuickSettingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        smartQuickSettingView = findViewById(R.id.rv);
        List<ExpandableBean> modes = new ArrayList<>();
        mode2 = new TitleBean();
//        mode2.setExpand(false);
        mode2.name = "安防";
        mode2.isTitle = true;
        for (int i = 0; i < 5; i++) {
            ItemBean d = new ItemBean();
            d.name = "客厅" + i;
            d.setParent(mode2);
            mode2.modeItems.add(d);
        }
        modes.add(mode2);
        TitleBean mode3 = new TitleBean();
        mode3.name = "卧室";
        mode3.isTitle = true;
        for (int i = 0; i < 8; i++) {
            ItemBean d = new ItemBean();
            d.name = "家居" + i;
            d.setParent(mode3);
            mode3.modeItems.add(d);
        }
        modes.add(mode3);
        smartQuickSettingView.setSrcData(modes);
    }

    public void show(View v) {
        smartQuickSettingView.addFirst(mode2);
    }


}

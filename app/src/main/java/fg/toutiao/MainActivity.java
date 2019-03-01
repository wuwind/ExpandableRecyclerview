package fg.toutiao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import fg.expandablerecyclerview.model.ExpandableBean;
import fg.expandablerecyclerview.view.ExpandableRecyclerView;
import fg.mylibrary.R;
import fg.toutiao.model.Mode;
import fg.toutiao.model.ModeItem;

public class MainActivity extends AppCompatActivity {

    private ExpandableRecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.rv);
        List<ExpandableBean> modes = new ArrayList<>();
        Mode mode = new Mode();
        mode.name = "快捷方式";
        for (int i = 0; i < 20; i++) {
            ModeItem d = new ModeItem();
            d.name = "k家居"+i;
            d.setParent(mode);
            mode.modeItems.add(d);
        }
        modes.add(mode);
        Mode mode2 = new Mode();
        mode2.name = "智能家居";
        for (int i = 0; i < 30; i++) {
            ModeItem d = new ModeItem();
            d.name = "家居"+i;
            d.setParent(mode2);
            mode2.modeItems.add(d);
        }
        modes.add(mode2);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = mRecyclerView.getAdapter().getItemViewType(position);
//                Log.e("tag","itemViewType:"+itemViewType);
                if(itemViewType == 0)
                    return 3;
                else
                    return 1;
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setDatas(modes, true);
    }
}

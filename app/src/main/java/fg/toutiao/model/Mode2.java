package fg.toutiao.model;


import java.util.ArrayList;
import java.util.List;

import fg.expandablerecyclerview.model.ExpandableBean;
import fg.toutiao.view.ModeView2;

public class Mode2 extends ExpandableBean<ModeView2> {
    public boolean shortCut;
    public String name;
    public List<ModeItem> modeItems = new ArrayList<>();
    public String tag = "点击添加到快捷方式";

    @Override
    public List<? extends ExpandableBean> getExpandableItemList() {
        return modeItems;
    }


    @Override
    public String toString() {
        return name+"\n";
    }
}

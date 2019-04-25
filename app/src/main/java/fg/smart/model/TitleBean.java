package fg.smart.model;


import java.util.ArrayList;
import java.util.List;

import fg.expandablerecyclerview.model.ExpandableBean;
import fg.smart.view.TitleView;

public class TitleBean extends ExpandableBean<TitleView> {
    public boolean shortCut;
    public String name;
    public List<ItemBean> modeItems = new ArrayList<>();
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

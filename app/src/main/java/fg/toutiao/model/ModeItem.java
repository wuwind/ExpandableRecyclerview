package fg.toutiao.model;


import java.util.List;

import fg.expandablerecyclerview.model.ExpandableBean;
import fg.toutiao.view.ModeItemView;

public class ModeItem extends ExpandableBean<ModeItemView> {

    public String name;
    public int type;

    @Override
    public List<? extends ExpandableBean> getExpandableItemList() {
        return null;
    }

    @Override
    public String toString() {
        return name+"\n";
    }
}

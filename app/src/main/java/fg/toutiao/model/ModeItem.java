package fg.toutiao.model;


import java.util.List;

import fg.expandablerecyclerview.model.ExpandableBean;
import fg.toutiao.view.ModeItemView;

public class ModeItem extends ExpandableBean<ModeItemView> implements Cloneable {

    public String name;
    public int type;
    public ModeItem cloneItem;

    @Override
    public List<? extends ExpandableBean> getExpandableItemList() {
        return null;
    }

    @Override
    public ModeItem clone() {
        try {
            cloneItem = (ModeItem) super.clone();
            cloneItem.cloneItem = this;
            return cloneItem;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return name + "\n";
    }
}

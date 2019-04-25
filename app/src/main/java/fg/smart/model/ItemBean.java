package fg.smart.model;


import java.util.List;

import fg.expandablerecyclerview.model.ExpandableBean;
import fg.smart.view.ItemView;

public class ItemBean extends ExpandableBean<ItemView> implements Cloneable {

    public String name;
    public int type;
    public ItemBean cloneItem;
    public transient boolean isSelected;

    @Override
    public List<? extends ExpandableBean> getExpandableItemList() {
        return null;
    }

    @Override
    public ItemBean clone() {
        try {
            cloneItem = (ItemBean) super.clone();
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

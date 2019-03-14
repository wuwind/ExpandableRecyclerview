package fg.expandablerecyclerview.model;

import java.util.List;

public abstract class ExpandableBean<T> {

    private int position;

    private boolean isExpand = true;
    private ExpandableBean parent;
    private ExpandableBean preParent;

    public ExpandableBean getPreParent() {
        return preParent;
    }

    public ExpandableBean getParent() {
        return parent;
    }

    public void setParent(ExpandableBean parent) {
        preParent = this.parent;
        this.parent = parent;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean isExpand) {
        this.isExpand = isExpand;
    }

    public abstract List<? extends ExpandableBean> getExpandableItemList();

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

package fg.expandablerecyclerview.model;

import java.util.List;

public abstract class ExpandableBean<T> {

    private int position;

    private boolean isExpand = false;

    public void setExpand(boolean isExpand){
        this.isExpand = isExpand;
    }

    public boolean isExpand(){
        return isExpand;
    }

    public abstract List<? extends ExpandableBean> getExpandableItemList();

    public void setPosition(int position){
        this.position = position;
    }

    public int getPosition(){
        return position;
    }
}

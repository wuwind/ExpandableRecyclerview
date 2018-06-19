package fg.expandablerecyclerview.view;

import android.support.annotation.LayoutRes;
import android.view.View;

import fg.expandablerecyclerview.adapter.AbstractAdapter;
import fg.expandablerecyclerview.model.ExpandableBean;


public abstract class AbstractAdapterView<T extends ExpandableBean> {

    private T expandableItem;
    private ExpandCollapseListener expandCollapseListener;

    @LayoutRes
    public abstract int getLayoutResId();

    public abstract void onBindViews(final View root);

    public abstract void onUpdateViews(T model);

    public void onUpdateViews(T expandableItem, int position) {
        this.expandableItem = expandableItem;
        this.expandableItem.setPosition(position);
        onUpdateViews(expandableItem);
    }

    public T getData(){
        return expandableItem;
    }

    public void setPosition(int position){
        expandableItem.setPosition(position);
    }

    public void setExpandCollapseListener(AbstractAdapter expandCollapseListener) {
        this.expandCollapseListener = expandCollapseListener;
    }

    protected void expandView() {
        if (null != expandCollapseListener) {
            expandCollapseListener.onExpanded(expandableItem.getPosition());
        }
    }

    protected void collapseView() {
        if (null != expandCollapseListener) {
            expandCollapseListener.onCollapsed(expandableItem.getPosition());
        }
    }

    protected void toggleExpandView() {
        if (expandableItem.isExpand()) {
            collapseView();
        } else {
            expandView();
        }
    }

    public interface ExpandCollapseListener {
        void onExpanded(int position);

        void onCollapsed(int position);
    }
}
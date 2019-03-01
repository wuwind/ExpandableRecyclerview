package fg.expandablerecyclerview.view;

import android.support.annotation.LayoutRes;
import android.view.View;

import java.util.List;

import fg.expandablerecyclerview.adapter.AbstractAdapter;
import fg.expandablerecyclerview.model.ExpandableBean;


public abstract class AbstractAdapterView<T extends ExpandableBean> {

    private List<ExpandableBean> datas;
    private T expandableItem;
    private ExpandCollapseListener expandCollapseListener;
    private AbstractAdapter adapter;

    public AbstractAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(AbstractAdapter adapter) {
        this.adapter = adapter;
    }

    @LayoutRes
    public abstract int getLayoutResId();

    public abstract void onBindViews(final View root);

    public void setOnClickListener(final View root) {
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleExpandView();
                root.setSelected(getData().isExpand());
            }
        });
    }

    protected void toggleExpandView() {
        if (expandableItem.isExpand()) {
            collapseView();
        } else {
            expandView();
        }
    }

    public T getData() {
        return expandableItem;
    }

    protected void collapseView() {
        if (null != expandCollapseListener) {
            expandCollapseListener.onCollapsed(expandableItem.getPosition());
        }
    }

    protected void expandView() {
        if (null != expandCollapseListener) {
            expandCollapseListener.onExpanded(expandableItem.getPosition());
        }
    }

    public void onUpdateViews(List<ExpandableBean> datas, T data, int position) {
        this.datas = datas;
        this.expandableItem = data;
        this.expandableItem.setPosition(position);
        onUpdateViews(expandableItem);
    }

    public abstract void onUpdateViews(T model);

    public List<ExpandableBean> getDatas() {
        return datas;
    }

    public void setPosition(int position) {
        expandableItem.setPosition(position);
    }

    public void setExpandCollapseListener(AbstractAdapter expandCollapseListener) {
        this.expandCollapseListener = expandCollapseListener;
    }

    public interface ExpandCollapseListener {
        void onExpanded(int position);

        void onCollapsed(int position);
    }
}
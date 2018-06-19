package fg.expandablerecyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import fg.expandablerecyclerview.model.ExpandableBean;
import fg.expandablerecyclerview.view.AbstractAdapterView;
import fg.expandablerecyclerview.view.BaseViewHolder;

/**
 * 一个可展开和收起的RecyclerView
 */

public abstract class AbstractAdapter extends RecyclerView.Adapter implements AbstractAdapterView.ExpandCollapseListener {

    AdapterItemUtil adapterItemUtil = new AdapterItemUtil();
    //显示的数据集
    private List<ExpandableBean> mDataList;
    private Class mItemType;
    private boolean isExpandAll;

    public AbstractAdapter(List<ExpandableBean> mDataList) {
        this(mDataList, false);
    }

    public AbstractAdapter(List<ExpandableBean> mDataList, boolean isExpandAll) {
        setDatas(mDataList, isExpandAll);
    }

    public void setDatas(List<ExpandableBean> dataList, boolean isExpandAll) {
        this.mDataList = dataList;
        this.isExpandAll = isExpandAll;
        if (isExpandAll) {
            checkExpandAll(new ArrayList<>(mDataList));
        }
        notifyDataSetChanged();
    }

    private void checkExpandAll(List<ExpandableBean> dataList) {
        for (ExpandableBean expandableItem : dataList) {
            expandableItem.setExpand(true);
            List expandableItemList = expandableItem.getExpandableItemList();
            if (null != expandableItemList) {
                mDataList.addAll(mDataList.indexOf(expandableItem) + 1, expandableItemList);
                checkExpandAll(expandableItemList);
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AbstractAdapterView adapterView = null;
        try {
            adapterView = (AbstractAdapterView) mItemType.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new BaseViewHolder(parent.getContext(), parent, adapterView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        BaseViewHolder rcvHolder = (BaseViewHolder) holder;
        ExpandableBean expandableItem = mDataList.get(position);
        AbstractAdapterView abstractAdapterView = rcvHolder.getItem();
        abstractAdapterView.setExpandCollapseListener(this);
        rcvHolder.getItem().onUpdateViews(expandableItem, position);
    }

    //该方法只更改itemView的部分信息，不全部刷新
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            String str = (String) payloads.get(0);
            if (str.equals("change_position")) {
                BaseViewHolder rcvHolder = (BaseViewHolder) holder;
                rcvHolder.getItem().setPosition(position);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        Type genericSuperclass = mDataList.get(position).getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        mItemType = (Class) actualTypeArguments[0];
        return adapterItemUtil.getIntType(mDataList.get(position).getClass().getCanonicalName());
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public void onExpanded(int position) {
        if (position >= getItemCount())
            return;
        ExpandableBean item = mDataList.get(position);
        if (item.isExpand())
            return;
        item.setExpand(true);
        List<ExpandableBean> expandableItemList = item.getExpandableItemList();
        if (null == expandableItemList || expandableItemList.size() <= 0)
            return;
        mDataList.addAll(position + 1, expandableItemList);
        notifyItemRangeInserted(position + 1, expandableItemList.size());
        notifyItemRangeChanged(position + 1, getItemCount(), "change_position");
    }

    @Override
    public void onCollapsed(int position) {
        List<ExpandableBean> removeItems = new ArrayList<>();
        getCollapseDatas(position, removeItems);
        if(removeItems.isEmpty())
            return;
        mDataList.removeAll(removeItems);
        notifyItemRangeRemoved(position + 1, removeItems.size());
        notifyItemRangeChanged(position + 1, getItemCount(), "change_position");
    }

    private void getCollapseDatas(int position, List<ExpandableBean> items) {
        if (position >= getItemCount())
            return;
        ExpandableBean item = mDataList.get(position);
        if (!item.isExpand())
            return;
        item.setExpand(false);
        List<ExpandableBean> expandableItemList = item.getExpandableItemList();
        if (null == expandableItemList || expandableItemList.size() <= 0)
            return;
        for (ExpandableBean expandableItem : expandableItemList) {
            items.add(expandableItem);
            if (expandableItem.isExpand()) {
                getCollapseDatas(expandableItem.getPosition(), items);
            }
        }
    }

}

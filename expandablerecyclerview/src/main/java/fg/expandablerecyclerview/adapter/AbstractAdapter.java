package fg.expandablerecyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
    ItemTouchHelper itemTouchHelper;
    //显示的数据集
    private List<ExpandableBean> mDataList;
    private List<ExpandableBean> mDataSource;
    private Class mItemType;
    private boolean isExpandAll;
    private OnItemClick onItemClick;

    public AbstractAdapter(List<ExpandableBean> mDataList) {
        this(mDataList, false);
    }

    public AbstractAdapter(List<ExpandableBean> mDataList, boolean isExpandAll) {
        setDatas(mDataList, isExpandAll);
    }

    public void setDatas(List<ExpandableBean> dataList, boolean isExpandAll) {
        this.isExpandAll = isExpandAll;
        mDataSource = dataList;
        if (null == mDataList)
            mDataList = new ArrayList<>();
        mDataList.clear();
        if (null != mDataSource) {
            mDataList.addAll(mDataSource);
            checkExpandAll(new ArrayList<>(mDataList));
        }
        notifyDataSetChanged();
    }

    public int removeAndNotify(ExpandableBean bean) {
        List<ExpandableBean> beans = new ArrayList<ExpandableBean>();
        getExpandDatas(bean, beans);
        int pos = remove(bean);
        notifyItemRangeRemoved(pos, beans.size());
        return pos;
    }

    public int remove(ExpandableBean bean) {
        int pos = mDataList.indexOf(bean);
        if (pos < 0)
            return pos;
        ExpandableBean parent = bean.getParent();
        pos = mDataList.indexOf(bean);
        if (null != parent) {
            List expandableItemList = parent.getExpandableItemList();
            if (null != expandableItemList)
                expandableItemList.remove(bean);
        }
        List<ExpandableBean> beans = new ArrayList<ExpandableBean>();
        getExpandDatas(bean, beans);
        mDataList.removeAll(beans);
        mDataSource.remove(bean);
        return pos;
    }

    private void getExpandDatas(ExpandableBean bean, List<ExpandableBean> datas) {
        datas.add(bean);
        List<ExpandableBean> expandableItemList = bean.getExpandableItemList();
        if (expandableItemList != null) {
            for (ExpandableBean data : expandableItemList) {
                getExpandDatas(data, datas);
            }
        }
    }

    public int addAndNotify(ExpandableBean bean) {
        return addAndNotify(-1, bean);
    }

    public int addAndNotify(int index, ExpandableBean bean) {
        List<ExpandableBean> beans = new ArrayList<>();
        getExpandDatas(bean, beans);
        int pos = add(index, bean);
        notifyItemRangeInserted(pos, beans.size());
        return pos;
    }

    /**
     * @param bean
     * @param index ref parent not list
     * @return position of view
     */
    public int add(int index, ExpandableBean bean) {
        int pos = -1;
        if (null == bean)
            return pos;
        ExpandableBean parent = bean.getParent();
        if (null != parent) {
            List expandableItemList = parent.getExpandableItemList();
            index = (index < 0 || index > expandableItemList.size()) ? expandableItemList.size() : index;
            expandableItemList.add(index, bean);
            pos = mDataList.indexOf(parent);
            if (pos >= 0) {
                pos = pos + index + 1;
                mDataList.add(pos, bean);
            }
        } else {
            if (index < 0 || index >= mDataSource.size()) {
                index = mDataSource.size();
            }
            if(index > 0 ) {
                ExpandableBean expandableBean = mDataSource.get(index-1);
                List ex = expandableBean.getExpandableItemList();
                pos = mDataList.indexOf(expandableBean)+ 1 + (ex == null ? 0 : expandableBean.getExpandableItemList().size());
            } else {
                pos = 0;
            }
            mDataSource.add(index, bean);
            List<ExpandableBean> beans = new ArrayList<>();
            getExpandDatas(bean, beans);
            mDataList.addAll(pos, beans);
        }
        return pos;
    }

    public int add(ExpandableBean bean) {
        return add(-1, bean);
    }

    private void checkExpandAll(List<ExpandableBean> dataList) {
        for (ExpandableBean expandableItem : dataList) {
            if (!isExpandAll && !expandableItem.isExpand())
                continue;
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
            adapterView.setAdapter(this);
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
        if (removeItems.isEmpty())
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

    public List<ExpandableBean> getDataList() {
        return mDataList;
    }

    public List<ExpandableBean> getDataSource() {
        return mDataSource;
    }

    public int getRealPosition(ExpandableBean bean) {
        return mDataList.indexOf(bean);
    }

    public void attachToRecyclerView(RecyclerView mRecyclerView) {
        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback());
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    public ItemTouchHelper getItemTouchHelper() {
        return itemTouchHelper;
    }

    public void move(int fromPosition, int toPosition) {
        ExpandableBean fromBean = mDataList.get(fromPosition);
        ExpandableBean toBean = mDataList.get(toPosition);
        remove(fromBean);
        ExpandableBean parent = toBean.getParent();
        int index = toPosition;
        if (parent != null) {
            index = parent.getExpandableItemList().indexOf(toBean);
        }
        index = fromPosition < toPosition ? index + 1 : index;
        add(index, fromBean);
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void itemClick(ExpandableBean bean) {
        if(null != onItemClick)
            onItemClick.onClick(bean);
    }

    public interface OnItemClick {
        void onClick(ExpandableBean bean);
    }
}

package fg.smart.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fg.expandablerecyclerview.adapter.AbstractAdapter;
import fg.expandablerecyclerview.adapter.HeaderRecyclerAdapter;
import fg.expandablerecyclerview.model.ExpandableBean;
import fg.expandablerecyclerview.view.ExpandableRecyclerView;
import fg.mylibrary.R;
import fg.smart.model.ItemBean;

public class SmartQuickSettingView extends ExpandableRecyclerView {

    MAdapter mAdapter;
    MAdapter mHeagerAdapter;
    List<ExpandableBean> headerData = new ArrayList<>();
    List<ExpandableBean> srcData = new ArrayList<>();
    private ExpandableRecyclerView rvHeader;

    public SmartQuickSettingView(Context context) {
        super(context);
        init();
    }

    public void init() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        mAdapter = new MAdapter();
        View view = View.inflate(getContext(), R.layout.header_view, null);
        mAdapter.setHeaderView(view);

        mHeagerAdapter = new MAdapter();
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 3);
        rvHeader = view.findViewById(R.id.rv_header);
        rvHeader.setManagerAndAdapter(gridLayoutManager2, mHeagerAdapter);
        rvHeader.setDatas(headerData);
        mHeagerAdapter.attachToRecyclerView(rvHeader);

        setManagerAndAdapter(gridLayoutManager, mAdapter);
        setDatas(srcData);
        mAdapter.setOnItemClick(new AbstractAdapter.OnItemClick() {
            @Override
            public void onClick(ExpandableBean bean) {
                if(bean instanceof ItemBean) {
                    ItemBean temp = (ItemBean) bean;
                    ItemBean clone = temp.clone();
                    clone.setParent(null);
                    mHeagerAdapter.addAndNotify(clone);
                }

            }
        });
        mHeagerAdapter.setOnItemClick(new AbstractAdapter.OnItemClick() {
            @Override
            public void onClick(ExpandableBean bean) {
                if(bean instanceof ItemBean) {
                    ItemBean temp = (ItemBean) bean;
                    ItemBean clone = temp.cloneItem;
                    clone.isSelected = false;
                    mHeagerAdapter.removeAndNotify(bean);
                    mAdapter.notifyItemChanged(mAdapter.getRealPosition(clone));
                }
            }
        });
    }

    public SmartQuickSettingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SmartQuickSettingView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setSrcData(List<ExpandableBean> beans) {
        mAdapter.setDatas(beans, true);
    }

    public void setQuickData(List<ExpandableBean> beans) {
        mHeagerAdapter.setDatas(beans, true);
    }

    public void addFirst(ExpandableBean bean) {
        if (getMAdapter().getDataList().contains(bean))
            return;
        getMAdapter().addAndNotify(0, bean);
    }

    private class MAdapter extends HeaderRecyclerAdapter {
        MAdapter() {
            super(null);
        }
    }
}

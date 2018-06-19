package fg.expandablerecyclerview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.List;

import fg.expandablerecyclerview.adapter.AbstractAdapter;
import fg.expandablerecyclerview.model.ExpandableBean;

public class ExpandableRecyclerView extends RecyclerView {

    MAdapter mAdapter = new MAdapter();

    public ExpandableRecyclerView(Context context) {
        this(context, null);
    }

    public ExpandableRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandableRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        setAdapter(mAdapter);
    }

    public void setDatas(List<ExpandableBean> mDataList, boolean isExpandAll) {
        mAdapter.setDatas(mDataList, isExpandAll);
    }

    public void setDatas(List<ExpandableBean> mDataList) {
        mAdapter.setDatas(mDataList, false);
    }

    class MAdapter extends AbstractAdapter {
        public MAdapter() {
            super(null);
        }
    }
}

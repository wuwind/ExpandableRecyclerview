package fg.expandablerecyclerview.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fg.expandablerecyclerview.model.ExpandableBean;

public abstract class HeaderRecyclerAdapter extends AbstractAdapter {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_TITLE = 2;

    private View mHeaderView;

    public HeaderRecyclerAdapter(List datas) {
        super(datas);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER)
            return new Holder(mHeaderView);
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;
        final int pos = getRealPosition(viewHolder);
        super.onBindViewHolder(viewHolder, pos);
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView != null && position == 0)
            return TYPE_HEADER;
        if(null != mHeaderView)
            position--;
        super.getItemViewType(position);
        if(getDataList().get(position).isTitle)
            return TYPE_TITLE;
        return TYPE_NORMAL;
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER || getItemViewType(position) == TYPE_TITLE
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? super.getItemCount() : super.getItemCount() + 1;
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int remove(ExpandableBean bean) {
        return mHeaderView == null ? super.remove(bean) : super.remove(bean) + 1;
    }

    @Override
    public int add(int index, ExpandableBean bean) {
        return mHeaderView == null ? super.add(index, bean) : super.add(index, bean) + 1;
    }

    @Override
    public int add(ExpandableBean bean) {
        return mHeaderView == null ? super.add(bean) : super.add(bean) + 1;
    }

    @Override
    public int getRealPosition(ExpandableBean bean) {
        return mHeaderView == null ? super.getRealPosition(bean) : super.getRealPosition(bean) + 1;
    }


}

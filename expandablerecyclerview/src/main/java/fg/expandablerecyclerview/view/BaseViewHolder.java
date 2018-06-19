package fg.expandablerecyclerview.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import fg.expandablerecyclerview.model.ExpandableBean;


public class BaseViewHolder extends RecyclerView.ViewHolder {

    protected AbstractAdapterView<ExpandableBean> mItem;

    public BaseViewHolder(Context context, ViewGroup parent, AbstractAdapterView<ExpandableBean> item) {
        super(LayoutInflater.from(context).inflate(item.getLayoutResId(), parent, false));
        itemView.setClickable(true);
        mItem = item;
        mItem.onBindViews(itemView);
    }

    public AbstractAdapterView<ExpandableBean> getItem() {
        return mItem;
    }
}

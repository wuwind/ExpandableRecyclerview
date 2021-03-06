package fg.base;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wuhf on 2017/5/18.
 */

public abstract class RecyclerBaseAdapter<T> extends Adapter implements View.OnClickListener, View.OnLongClickListener {

    protected List<T> datas;
    private OnItemClickListener itemClickListener;
    private OnItemLongClickListener itemLongClickListener;

    public RecyclerBaseAdapter(List<T> datas) {
        this.datas = datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout(), parent, false);
        return getViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecyclerBaseHolder baseHolder = (RecyclerBaseHolder) holder;
        T data = datas.get(position);
        baseHolder.setData(data);
        holder.itemView.setTag(data);
        holder.itemView.setOnClickListener(this);
        holder.itemView.setOnLongClickListener(this);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public abstract int itemLayout();

    public abstract RecyclerBaseHolder getViewHolder(View itemView);

    public void notifyItemChanged(T t) {
        if (getItemCount() <= 0)
            return;
        for (int i = 0; i < getItemCount(); i++) {
            if (datas.get(i) == t) {
                notifyItemChanged(i);
                return;
            }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (null != itemLongClickListener) {
            itemLongClickListener.onItemLongClick(v, v.getTag());
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (null != itemClickListener) {
            itemClickListener.onItemClick(v, v.getTag());
        }
    }

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setLongClickListener(OnItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(View view, T data);
    }

    public interface OnItemLongClickListener<T> {
        void onItemLongClick(View view, T data);
    }
}

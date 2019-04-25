package fg.smart.view;

import android.content.Context;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;

import fg.expandablerecyclerview.view.AbstractAdapterView;
import fg.mylibrary.R;
import fg.smart.model.ItemBean;

public class ItemView extends AbstractAdapterView<ItemBean> {

    private TextView mName;
    private View ivChecked;
    private View ivDel;
    private TextView mExpand;

    @Override
    public int getLayoutResId() {
        return R.layout.item_view;
    }

    @Override
    public void onBindViews(View root) {
        mName = (TextView) root.findViewById(R.id.tv_item_section_name);
        ivChecked = root.findViewById(R.id.iv_checked);
        ivDel = root.findViewById(R.id.iv_del);
    }

    @Override
    public void onItemClick(View v) {
        getData().isSelected = !getData().isSelected;
        notifyItemChanged();
    }

    @Override
    public boolean onItemLongClick(View v) {
        if (!isFix() && null != getAdapter().getItemTouchHelper()) {
            Vibrator vibrator = (Vibrator) v.getContext().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(1000);
            getAdapter().getItemTouchHelper().startDrag(getViewHolder());
        }
        return false;
    }

    @Override
    public void onUpdateViews(ItemBean model) {
        mName.setText(model.name);
        ivDel.setVisibility(model.getParent() == null ? View.VISIBLE : View.GONE);
        ivChecked.setVisibility(model.getParent() != null && model.isSelected ? View.VISIBLE : View.GONE);

    }

}




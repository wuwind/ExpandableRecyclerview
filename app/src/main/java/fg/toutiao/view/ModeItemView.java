package fg.toutiao.view;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import fg.expandablerecyclerview.model.ExpandableBean;
import fg.expandablerecyclerview.view.AbstractAdapterView;
import fg.mylibrary.R;
import fg.toutiao.model.Mode;
import fg.toutiao.model.ModeItem;
import fg.utils.ButtonUtils;

public class ModeItemView extends AbstractAdapterView<ModeItem> {

    private TextView mName;
    private View ivDel;
    private TextView mExpand;

    @Override
    public int getLayoutResId() {
        return R.layout.item_section;
    }

    @Override
    public void onBindViews(View root) {
        mName = (TextView) root.findViewById(R.id.tv_item_section_name);
        ivDel = root.findViewById(R.id.iv_del);
    }

    @Override
    public void setOnClickListener(final View root) {
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click", "click" + v.getId());
                if (ButtonUtils.isFastDoubleClick(v))
                    return;
                List<ExpandableBean> datas = getDatas();
                ModeItem data = getData();
                if (data.getParent() == data.getPreParent())
                    return;
                int index = 0;
                int fromPosition;
                //移回
                if (data.getParent() == datas.get(0)) {
                    if(data.getPreParent() == null)
                        return;
                    fromPosition = getAdapter().remove(data);
                    data.setParent(data.getPreParent());
                } else {
                    fromPosition = getAdapter().remove(data);
                    data.setParent(datas.get(0));
                    index = -1;
                }
                int toPosition = getAdapter().add(index, data);
                getAdapter().notifyItemMoved(fromPosition, toPosition);
                getAdapter().notifyItemChanged(toPosition);
//                getAdapter().notifyItemInserted(toPosition);
            }
        });

        root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(!isFix()) {
                    Vibrator vibrator = (Vibrator) v.getContext().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(1000);
                    getAdapter().getItemTouchHelper().startDrag(getViewHolder());
                }
                return false;
            }
        });
    }

    @Override
    public boolean isFix() {
        return getData().getParent() != getDatas().get(0);
    }

    @Override
    public void onUpdateViews(ModeItem model) {
        mName.setText(model.name);
        if (model.getParent() instanceof Mode && (((Mode) model.getParent()).shortCut)) {
            ivDel.setVisibility(View.VISIBLE);
        } else {
            ivDel.setVisibility(View.GONE);
        }
    }

}




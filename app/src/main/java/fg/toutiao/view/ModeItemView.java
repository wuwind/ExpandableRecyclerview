package fg.toutiao.view;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fg.expandablerecyclerview.model.ExpandableBean;
import fg.expandablerecyclerview.view.AbstractAdapterView;
import fg.mylibrary.R;
import fg.toutiao.model.ModeItem;
import fg.utils.ButtonUtils;

public class ModeItemView extends AbstractAdapterView<ModeItem> {

    private TextView mName;
    private ImageView mArrow;
    private TextView mExpand;

    @Override
    public int getLayoutResId() {
        return R.layout.item_section;
    }

    @Override
    public void onBindViews(View root) {
        mName = (TextView) root.findViewById(R.id.tv_item_section_name);
    }

    @Override
    public void setOnClickListener(final View root) {
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click","click"+v.getId());
                if(ButtonUtils.isFastDoubleClick(v))
                    return;
                List<ExpandableBean> datas = getDatas();
                ModeItem data = getData();
                int fromPosition = getAdapter().remove(data);
                int index = 0;
                if(data.getParent() == datas.get(0)) {
                    data.setParent(datas.get(1));
                } else {
                    data.setParent(datas.get(0));
                    index = -1;
                }
                int toPosition = getAdapter().add(index, data);
                getAdapter().notifyItemMoved(fromPosition, toPosition);
                v.setEnabled(true);
            }
        });
    }

    @Override
    public void onUpdateViews(ModeItem model) {
        mName.setText(model.name);
    }

}

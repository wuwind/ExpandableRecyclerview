package fg.toutiao.view;

import android.view.View;
import android.widget.TextView;

import fg.expandablerecyclerview.view.AbstractAdapterView;
import fg.mylibrary.R;
import fg.toutiao.model.Mode;


public class ModeView extends AbstractAdapterView<Mode> {

    private TextView mName;
    private TextView tvTag;

    @Override
    public int getLayoutResId() {
        return R.layout.item_chapter;
    }

    @Override
    public void onBindViews(final View root) {
        mName = root.findViewById(R.id.tv_item_chapter_name);
        tvTag = root.findViewById(R.id.tv_tag);
    }

    @Override
    public void onItemClick(View v) {
        if("安防".equals(mName.getText().toString()))
            getAdapter().removeAndNotify(getData());
//                    toggleExpandView();
    }

    @Override
    public boolean onItemLongClick(View v) {
        return false;
    }

    @Override
    public void onUpdateViews(Mode model) {
        mName.setText(model.name);
        tvTag.setText(model.tag);
    }

}

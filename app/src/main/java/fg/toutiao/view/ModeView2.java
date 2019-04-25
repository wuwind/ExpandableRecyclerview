package fg.toutiao.view;

import android.view.View;
import android.widget.TextView;

import fg.expandablerecyclerview.view.AbstractAdapterView;
import fg.mylibrary.R;
import fg.toutiao.model.Mode2;


public class ModeView2 extends AbstractAdapterView<Mode2> {

    private TextView mName;
    private TextView tvTag;

    @Override
    public int getLayoutResId() {
        return R.layout.item_chapter2;
    }

    @Override
    public void onBindViews(final View root) {
        mName = root.findViewById(R.id.tv_item_chapter_name);
        tvTag = root.findViewById(R.id.tv_tag);
    }

    @Override
    public void onItemClick(View v) {
        if("客厅".equals(mName.getText().toString()))
            toggleExpandView();
    }

    @Override
    public boolean onItemLongClick(View v) {
        return false;
    }

    @Override
    public void onUpdateViews(Mode2 model) {
        mName.setText(model.name);
        tvTag.setText(model.tag);
    }

}

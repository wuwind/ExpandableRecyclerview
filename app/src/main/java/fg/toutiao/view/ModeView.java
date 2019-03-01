package fg.toutiao.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fg.expandablerecyclerview.view.AbstractAdapterView;
import fg.mylibrary.R;
import fg.toutiao.model.Mode;


public class ModeView extends AbstractAdapterView<Mode> {

    private TextView mName;
    private ImageView mArrow;

    @Override
    public int getLayoutResId() {
        return R.layout.item_chapter;
    }

    @Override
    public void onBindViews(final View root) {
        mName = root.findViewById(R.id.tv_item_chapter_name);
        mArrow = root.findViewById(R.id.iv_item_chapter_arrow);

    }

    @Override
    public void setOnClickListener(final View root) {
//        super.setOnClickListener(root);
    }

    @Override
    public void onUpdateViews(Mode model) {
        mName.setText(model.name);
        mArrow.setImageResource(model.isExpand() ? R.mipmap.ic_launcher_round : R.mipmap.ic_launcher);
    }

}

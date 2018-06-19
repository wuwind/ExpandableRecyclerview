package fg.mylibrary.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fg.expandablerecyclerview.view.AbstractAdapterView;
import fg.mylibrary.R;
import fg.mylibrary.model.Company;


public class CompanyView extends AbstractAdapterView<Company> {

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
        mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleExpandView();
                mArrow.setImageResource(getData().isExpand() ? R.mipmap.ic_launcher_round : R.mipmap.ic_launcher);
            }
        });
    }

    @Override
    public void onUpdateViews(Company model) {
        mName.setText(model.name);
        mArrow.setImageResource(model.isExpand() ? R.mipmap.ic_launcher_round : R.mipmap.ic_launcher);
    }

}

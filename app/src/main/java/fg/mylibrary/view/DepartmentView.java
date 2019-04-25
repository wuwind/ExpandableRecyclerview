package fg.mylibrary.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import fg.expandablerecyclerview.view.AbstractAdapterView;
import fg.mylibrary.R;
import fg.mylibrary.model.Department;

public class DepartmentView extends AbstractAdapterView<Department> {

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
        mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                toggleExpandView();
                Toast.makeText(v.getContext(), getData().name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(View v) {

    }

    @Override
    public boolean onItemLongClick(View v) {
        return false;
    }

    @Override
    public void onUpdateViews(Department model) {
        mName.setText(model.name);
    }

}

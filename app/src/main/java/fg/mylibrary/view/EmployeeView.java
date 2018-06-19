package fg.mylibrary.view;

import android.view.View;
import android.widget.TextView;

import fg.expandablerecyclerview.view.AbstractAdapterView;
import fg.mylibrary.R;
import fg.mylibrary.model.Employee;

public class EmployeeView extends AbstractAdapterView<Employee> {

    private TextView mName;

    @Override
    public int getLayoutResId() {
        return R.layout.item_employee;
    }

    @Override
    public void onBindViews(View root) {
        mName = (TextView) root.findViewById(R.id.tv_item_section_name);
    }

    @Override
    public void onUpdateViews(Employee model) {
        mName.setText(model.name);
    }
}

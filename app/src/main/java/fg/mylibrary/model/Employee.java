package fg.mylibrary.model;

import java.util.List;

import fg.expandablerecyclerview.model.ExpandableBean;
import fg.mylibrary.view.EmployeeView;

public class Employee extends ExpandableBean<EmployeeView> {
    public String name;

    @Override
    public List<? extends ExpandableBean> getExpandableItemList() {
        return null;
    }
}

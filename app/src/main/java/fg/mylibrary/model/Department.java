package fg.mylibrary.model;


import java.util.List;

import fg.expandablerecyclerview.model.ExpandableBean;
import fg.mylibrary.view.DepartmentView;

public class Department extends ExpandableBean<DepartmentView> {

    public String name;

    public List<Employee> employeeList;

    @Override
    public List<? extends ExpandableBean> getExpandableItemList() {
        return employeeList;
    }
}

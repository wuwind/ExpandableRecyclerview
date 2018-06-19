package fg.mylibrary.model;


import java.util.List;

import fg.expandablerecyclerview.model.ExpandableBean;
import fg.mylibrary.view.CompanyView;

public class Company extends ExpandableBean<CompanyView> {

    public String name;
    public List<Department> departments;


    @Override
    public List<? extends ExpandableBean> getExpandableItemList() {
        return departments;
    }

}

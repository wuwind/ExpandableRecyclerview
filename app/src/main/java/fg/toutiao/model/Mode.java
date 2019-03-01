package fg.toutiao.model;


import java.util.ArrayList;
import java.util.List;

import fg.expandablerecyclerview.model.ExpandableBean;
import fg.toutiao.view.ModeView;

public class Mode extends ExpandableBean<ModeView> {

    public String name;
    public List<ModeItem> modeItems = new ArrayList<>();


    @Override
    public List<? extends ExpandableBean> getExpandableItemList() {
        return modeItems;
    }

}
